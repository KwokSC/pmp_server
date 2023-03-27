package com.chunkie.pmp_server.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.chunkie.pmp_server.common.Constants;
import com.chunkie.pmp_server.config.AWSConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class S3Service {

    @Resource
    private AmazonS3 amazonS3;

    @Value(Constants.AWS.BUCKET)
    private String bucketName;

    public void uploadFile(String key, InputStream inputStream) {
        amazonS3.putObject(bucketName, key, inputStream, new ObjectMetadata());
    }

    public void deleteFile(String key) {
        amazonS3.deleteObject(bucketName, key);
    }

    public S3Object getFile(String key) {
        return amazonS3.getObject(bucketName, key);
    }

    public void uploadPhotos(List<MultipartFile> photos, String userId) throws IOException {
        for (MultipartFile photo : photos) {
            String key = "profile/" + userId + "/photos/" + UUID.randomUUID().toString();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(photo.getContentType());
            metadata.setContentLength(photo.getSize());
            amazonS3.putObject(bucketName, key, photo.getInputStream(), metadata);
        }
    }

    public List<String> getPhotos(String userId) {
        String folderName = "profile/" + userId + "/photos/";
        System.out.println(bucketName);
        List<String> photos = new ArrayList<>();

        ObjectListing objectListing = amazonS3.listObjects(bucketName, folderName);
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            S3Object object = amazonS3.getObject(bucketName, objectSummary.getKey());
            photos.add(getPresignedUrl(object));
        }

        return photos;
    }

    public String getPresignedUrl(S3Object s3Object) {
        Date expiration = new Date(System.currentTimeMillis() + (1000 * 60 * 60)); // 1 hour from now
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, s3Object.getKey())
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);
        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

    public List<Bucket> listBuckets(){
        return amazonS3.listBuckets();
    }
}
