package org.reformer.proxydebug.controller;

import org.reformer.proxydebug.entity.ProxyEntity;
import org.reformer.proxydebug.service.IProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/proxy")
public class ProxyController {

    private static final String PROXY_FORM_PATH_NAME = "proxyForm";
    private static final String PROXY_LIST_PATH_NAME = "proxyList";
    private static final String REDIRECT_TO_PROXY_URL = "redirect:/proxy";

    @Autowired
    IProxyService iProxyService;

    @RequestMapping(method = RequestMethod.GET)
    public String getProxyList(ModelMap map){
        map.addAttribute("proxyList", iProxyService.findAll());
        return PROXY_LIST_PATH_NAME;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createProxyForm(ModelMap map) {
        map.addAttribute("proxy", new ProxyEntity(
                null,5005,5005,"192.168.3.100",
                22,"rfro","rfro!Q2w","192.168.10.202",
                1080,"","",null));
        map.addAttribute("action", "create");
        return PROXY_FORM_PATH_NAME;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postProxy(@ModelAttribute ProxyEntity proxyEntity) throws Exception {
        iProxyService.insertProxy(proxyEntity);
        return REDIRECT_TO_PROXY_URL;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProxy(@PathVariable Long id) throws Exception {
        iProxyService.delete(id);
        return REDIRECT_TO_PROXY_URL;
    }

}
