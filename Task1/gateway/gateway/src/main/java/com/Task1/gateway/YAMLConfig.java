package com.Task1.gateway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "config")
public class YAMLConfig {
    private List<Service> services;

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public YAMLConfig() {
    }

    public static class Service{
        private String method;
        private String url;

        private String status;
        private String type;

        public String getPostParam() {
            return postParam;
        }

        public void setPostParam(String postParam) {
            this.postParam = postParam;
        }

        private String postParam;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        private String body;

        public String getServerId() {
            return serverId;
        }

        public void setServerId(String serverId) {
            this.serverId = serverId;
        }

        private String serverId;
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Service() {
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
