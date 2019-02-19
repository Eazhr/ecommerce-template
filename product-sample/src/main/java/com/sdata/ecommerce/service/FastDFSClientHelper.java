package com.sdata.ecommerce.service;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.domain.upload.FastFile;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.sdata.ecommerce.exception.EcommerceRuntimeException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.sdata.ecommerce.exception.ErrorCode.UPLOAD_FAILED;

/**
 * @author nedli
 */
@Service
public class FastDFSClientHelper {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //TODO: Move to configuration later.
    private final static String HTTP_PROTOCAL = "http://";
    private final static String RES_HOST = "192.168.101.67";
    private final static String STORAGE_PORT = "10080";


    @Autowired
    private FastFileStorageClient storageClient;

    public String uploadFile(MultipartFile file) {
        try {
            FastFile fastFile = new FastFile.Builder()
                    .withFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()))
                    .build();
            StorePath storePath = storageClient.uploadFile(fastFile);
            return getResAccessUrl(storePath);
        } catch (IOException e) {
            logger.warn("Upload FastDFS file failed. file = {}, message = {}", file.getOriginalFilename(), e.getMessage());
            throw new EcommerceRuntimeException(UPLOAD_FAILED);
        }

    }

    public byte[] downloadFile(String fileUrl) {
        StorePath storePath = StorePath.parseFromUrl(fileUrl);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), downloadByteArray);

        return bytes;
    }

    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = HTTP_PROTOCAL + RES_HOST
                + ":" + STORAGE_PORT + "/" + storePath.getFullPath();
        return fileUrl;
    }

    public void deleteFile(String fileUrl) {
        if (StringUtils.isNotBlank(fileUrl)) {
            try {
                StorePath storePath = StorePath.parseFromUrl(fileUrl);
                storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
            } catch (FdfsUnsupportStorePathException e) {
                logger.warn("Delete FastDFS file failed. url = {}, message = {}", fileUrl, e.getMessage());
            }
        }

    }
}
