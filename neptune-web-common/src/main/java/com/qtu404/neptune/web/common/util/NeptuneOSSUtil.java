package com.qtu404.neptune.web.common.util;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Date;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/12
 */
@Component
public class NeptuneOSSUtil {
    @Value("${qtu404.util.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${qtu404.util.oss.accessKeyId}")
    private String accessKeyId;

    private String endpoint = "http://oss-cn-qingdao.aliyuncs.com";

    private String bucketName = "qtu-404";

    private String website = "http://static.qtu404.com/";

    private String fileFolder = "qh/qh-img/";


    public String upLoadImgToOSS(InputStream file, String fileName) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        fileName = new Date().getTime() + fileName;
        ossClient.putObject(bucketName, fileFolder + fileName, file);
        ossClient.shutdown();
        return website + fileFolder + fileName;
    }
}
