package com.eco.monitor.file.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.redshift.model.BucketNotFoundException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Aws S3 Integration Service.
 *
 * @author Vladyslav Hnes
 */
@Service
public class S3IntegrationService {

    private static final Logger LOG = LoggerFactory.getLogger(S3IntegrationService.class);

    @Autowired
    private AmazonS3 clientS3;

    /**
     * Uploads file to S3.
     *
     * @param fileName   file name
     * @param bucketName s3 bucket name
     * @param file       {@link File} file to upload
     * @return {@code true} if file was uploaded, otherwise {@code false}
     */
    public boolean putFile(String fileName, String bucketName, File file) {

        PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file);
        try {
            clientS3.putObject(request);
        } catch (AmazonClientException e) {
            LOG.warn("File uploading failed: {}", e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Downloads file from S3.
     *
     * @param bucketName {@link String}  s3 bucket name
     * @param key        {@link String}         unique file key
     * @return bytes of file to be downloaded
     * @throws FileNotFoundException when file not found
     */
    public byte[] getFile(String bucketName, String key) throws FileNotFoundException {
        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                S3Object s3Object = clientS3.getObject(new GetObjectRequest(bucketName, key))
        ) {
            IOUtils.copy(s3Object.getObjectContent(), byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException | AmazonS3Exception e) {
            LOG.error("Error during file downloading from S3: {}", e.getMessage());
            throw new FileNotFoundException();
        }
    }

    /**
     * Removes file from S3.
     *
     * @param bucketName {@link String} s3 bucket name
     * @param key        {@link String} unique file key
     * @return {@code true} if file was removed, otherwise {@code false}
     */
    public boolean removeFile(String bucketName, String key) {
        try {
            clientS3.deleteObject(bucketName, key);
        } catch (AmazonClientException e) {
            LOG.warn("File was not removed, because of: {}", e.getMessage());
            return false;
        }
        return true;
    }
}
