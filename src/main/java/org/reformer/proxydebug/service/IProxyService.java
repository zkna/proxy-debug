package org.reformer.proxydebug.service;


import org.reformer.proxydebug.entity.ProxyEntity;

import java.util.List;

public interface IProxyService {

    List<ProxyEntity> findAll();

    ProxyEntity insertProxy(ProxyEntity proxyEntity) throws Exception;

    ProxyEntity delete(Long id) throws Exception;
}
