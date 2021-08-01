package com.tenglong.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class PathConfig {

    @Value("${path.upload.temp}")
    private String uploadTempPath;

    @Bean("uploadDirPath")
    String getUploadDirPath(){
        return uploadTempPath;
    }
}
