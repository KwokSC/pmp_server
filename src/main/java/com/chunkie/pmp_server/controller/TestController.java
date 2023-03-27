package com.chunkie.pmp_server.controller;

import com.amazonaws.services.s3.model.Bucket;
import com.chunkie.pmp_server.service.S3Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private S3Service s3Service;

    @RequestMapping("/listBuckets")
    public List<Bucket> listBuckets(){
        return s3Service.listBuckets();
    }
}
