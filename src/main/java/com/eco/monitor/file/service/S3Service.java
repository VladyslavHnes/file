package com.eco.monitor.file.service;

import com.eco.monitor.file.config.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * S3 file service.
 *
 * @author Vladyslav Hnes
 */
@Service
public class S3Service {

    @Autowired
    private S3IntegrationService fileIntegrationService;

    @Autowired
    private ConfigurationManager configurationManager;

    public String upload(String name, File file) throws Exception {
        boolean result = fileIntegrationService.putFile(name, configurationManager.getBucketName(), file);
        if (!result) {
            throw new Exception();
        }
        return configurationManager.getServiceEndpoint() + "/monitor-app-images/" + name;
    }

//    /**
//     * Removes file.
//     *
//     * @param fileId {@link UUID} file id
//     * @throws S3Exception           when removing fails
//     * @throws FileNotFoundException when file not found
//     */
//    public void remove(UUID fileId) throws S3Exception, FileNotFoundException {
//        var file = fileRepository.findByFileIdAndRemovedAtIsNull(fileId)
//                .orElseThrow(FileNotFoundException::new);
//        boolean removed = fileIntegrationService.removeFile(configurationManager.getBucketName(), file.getFileId().toString());
//        if (removed) {
//            file.setRemovedAt(nowLocalDateTime());
//            fileRepository.save(file);
//        } else {
//            throw new S3Exception();
//        }
//    }
}
