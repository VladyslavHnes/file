package com.eco.monitor.file.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Configuration Manager.
 *
 * @author Vladyslav Hnes
 */
@Component
@Validated
@ConfigurationProperties
public class ConfigurationManager {

    @NotNull
    private AwsConfig awsConfig;

    public String getAccessKey() {
        return awsConfig.getAccessKey();
    }

    public String getSecretKey() {
        return awsConfig.getSecretKey();
    }

    public String getServiceEndpoint() {
        return awsConfig.getServiceEndpoint();
    }

    public String getRegionName() {
        return awsConfig.getRegionName();
    }

    public String getBucketName() {
        return awsConfig.getBucketName();
    }

    public void setAwsConfig(AwsConfig awsConfig) {
        this.awsConfig = awsConfig;
    }

    /**
     * AWS Configuration values.
     *
     * @author Vladyslav Hnes
     */
    private static class AwsConfig {

        @NotEmpty
        private String accessKey;

        @NotEmpty
        private String secretKey;

        @NotEmpty
        private String serviceEndpoint;

        @NotEmpty
        private String regionName;

        @NotEmpty
        private String bucketName;

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getServiceEndpoint() {
            return serviceEndpoint;
        }

        public void setServiceEndpoint(String serviceEndpoint) {
            this.serviceEndpoint = serviceEndpoint;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public String getBucketName() {
            return bucketName;
        }

        public void setBucketName(String bucketName) {
            this.bucketName = bucketName;
        }
    }
}
