package com.chunkie.pmp_server.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.chunkie.pmp_server.common.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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

    public boolean uploadFile(String key, MultipartFile file) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());
            PutObjectResult result = amazonS3.putObject(bucketName, key, file.getInputStream(), new ObjectMetadata());
            return result.getMetadata() != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteFile(String key) {
        amazonS3.deleteObject(bucketName, key);
    }

    public S3Object getFile(String key) {
        return amazonS3.getObject(bucketName, key);
    }

    public boolean uploadPhoto(MultipartFile photo, String userId) {
        String key = "profile/user_" + userId + "/photos/" + UUID.randomUUID() + getExtension(photo);
        return uploadFile(key, photo);
    }

    public List<Integer> uploadMultiplePhotos(List<MultipartFile> photos, String userId) {
        List<Integer> result = new ArrayList<>();
        for (MultipartFile photo : photos) {
            if (photo.getSize() != 0){
                String key = "profile/user_" + userId + "/photos/" + UUID.randomUUID() + getExtension(photo);
                result.add(uploadFile(key, photo) ? 1 : 0);
            }
            else result.add(0);
        }
        return result;
    }

    public List<String> getPhotos(String userId) {
        String folderName = "profile/user_" + userId + "/photos/";
        List<String> photos = new ArrayList<>();
        ObjectListing objectListing = amazonS3.listObjects(bucketName, folderName);
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            S3Object object = amazonS3.getObject(bucketName, objectSummary.getKey());
            photos.add(getS3Url(object));
        }
        return photos;
    }

    public List<Bucket> listBuckets() {
        return amazonS3.listBuckets();
    }

    private String getS3Url(S3Object s3Object) {
        Date expiration = new Date(System.currentTimeMillis() + (1000 * 60 * 60)); // 1 hour from now
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, s3Object.getKey())
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);
        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

    private String getExtension(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        String extension = null;
        if (originalName != null) {
            int dotIndex = originalName.lastIndexOf(".");
            if (dotIndex > 0) {
                extension = originalName.substring(dotIndex);
            }
        }
        return extension;
    }

}
