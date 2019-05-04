package com.qtu404.neptune.util.oss;


import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Date;

@Component
public class OSSHandler {
    @Value("${qtu404.util.oss.accessKeyId}")
    private String accessKeySecret;

    @Value("${qtu404.util.oss.accessKeySecret}")
    private String accessKeyId;

    @Value("${qtu404.util.oss.endpoint}")
    private String endpoint = "http://oss-cn-qingdao.aliyuncs.com";

    @Value("${qtu404.util.oss.bucketName}")
    private String bucketName = "qtu-404";

    @Value("${qtu404.util.oss.website}")
    private String website = "http://static.qtu404.com/";

    @Value("${qtu404.util.oss.fileFolder}")
    private String fileFolder = "qh/qh-img/";


    public String upLoadImgToOSS(InputStream file, String fileName) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        fileName = new Date().getTime() + fileName;
        ossClient.putObject(bucketName, fileFolder + fileName, file);
        ossClient.shutdown();
        return website + fileFolder + fileName;
    }

}