package org.reformer.proxydebug.service.impl;

import cn.hutool.core.date.DateUtil;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.ProxySOCKS5;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import org.reformer.proxydebug.entity.ProxyEntity;
import org.reformer.proxydebug.service.IProxyService;
import org.reformer.proxydebug.util.GsonUtil;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class IProxyServiceImpl implements IProxyService {

    private static HashMap<Long, ProxyEntity> sessionHashMap = new HashMap<>();

    @Override
    public List<ProxyEntity> findAll() {
        return new ArrayList(sessionHashMap.values());
    }

    @Override
    public ProxyEntity insertProxy(ProxyEntity proxyEntity) throws Exception {
        proxyEntity.setKey(sessionHashMap.size()+1L);

        JSch jsch = new JSch();
        Session session = jsch.getSession(proxyEntity.getRemoteUser(), proxyEntity.getRemoteHost(), proxyEntity.getRemotePort());
        session.setProxy(new ProxySOCKS5(proxyEntity.getProxyHost(), proxyEntity.getProxyPort()));
        session.setUserInfo(new MyUserInfo(proxyEntity.getRemotePasswd()));
        session.connect();
        session.setPortForwardingL("0.0.0.0",proxyEntity.getLocalPort(), proxyEntity.getRemoteHost(), proxyEntity.getTransPort());
        proxyEntity.setSession(session);
        if (session.isConnected() == true){
            sessionHashMap.put(proxyEntity.getKey(), proxyEntity);
            return proxyEntity;
        }else{
            throw new Exception(DateUtil.now()+": 建立代理失败："+ GsonUtil.jsonFormatter(GsonUtil.bean2Json(proxyEntity)));
        }
    }

    @Override
    public ProxyEntity delete(Long id) throws Exception {
        sessionHashMap.get(id).getSession().disconnect();
        if (sessionHashMap.get(id).getSession().isConnected() == false){
            return sessionHashMap.remove(id);
        }else{
            throw new Exception(DateUtil.now()+": 销毁代理失败："+ GsonUtil.jsonFormatter(GsonUtil.bean2Json(sessionHashMap.get(id))));
        }


    }

    public class MyUserInfo implements UserInfo {
        String passwd = "reformer!Q2w";

        public MyUserInfo(String passwd) {
            this.passwd = passwd;
        }

        @Override
        public String getPassword() {
            return passwd;
        }

        @Override
        public boolean promptYesNo(String str) {
            return true;
        }

        @Override
        public String getPassphrase() {
            return null;
        }

        @Override
        public boolean promptPassphrase(String message) {
            return true;
        }

        @Override
        public boolean promptPassword(String message) {
            return true;
        }

        @Override
        public void showMessage(String message) {
            JOptionPane.showMessageDialog(null, message);
        }
    }

}
