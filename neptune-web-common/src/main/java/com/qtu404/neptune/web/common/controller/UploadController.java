package com.qtu404.neptune.web.common.controller;

import com.google.common.base.Throwables;
import com.qtu404.neptune.util.model.MyJSON;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.util.model.exception.RestException;
import com.qtu404.neptune.web.common.util.NeptuneOSSUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/12
 */
@Api(value = "上传-通用接口", tags = "上传-通用接口")
@RestController
@RequestMapping("api/common/upload/")
@Slf4j
public class UploadController {
    private final NeptuneOSSUtil ossUtil;

    public UploadController(NeptuneOSSUtil ossUtil) {
        this.ossUtil = ossUtil;
    }

    @PostMapping("image")
    public Response<String> imageUpload(@RequestParam("file") MultipartFile file) {
        String rst;
        try {
            String fileName = new Date().getTime() + file.getOriginalFilename();
            log.info("execute.upload.image:fileName:{},fileType:{}", file.getOriginalFilename(), file.getContentType());
            rst = ossUtil.upLoadImgToOSS(file.getInputStream(), fileName);
        } catch (IOException e) {
            log.error("file.parsing.error.cause:{}", Throwables.getStackTraceAsString(e));
            throw new RestException("file.parsing.error");
        }
        return Response.success(rst);
    }
}
