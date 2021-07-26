package org.reformer.proxydebug.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jcraft.jsch.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProxyEntity implements Serializable {

    private Long key;

    private Integer transPort;

    private Integer localPort;

    private String remoteHost;

    private Integer remotePort;

    private String remoteUser;

    private String remotePasswd;

    private String proxyHost;

    private Integer proxyPort;

    private String proxyUser;

    private String proxyPasswd;

    @JsonIgnore
    private Session session;
}
