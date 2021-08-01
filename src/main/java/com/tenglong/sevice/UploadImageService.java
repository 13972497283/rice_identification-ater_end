package com.tenglong.sevice;

import com.tenglong.config.oos.CloudStorageConfig;

import java.io.FileInputStream;

public abstract class UploadImageService {

    protected CloudStorageConfig config;

    public abstract String uploadQNImg(FileInputStream file, String path);

}