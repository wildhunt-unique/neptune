package com.qtu404.neptune.api.facade;

import com.qtu404.neptune.api.request.upload.FileUpLoadRequest;
import com.qtu404.neptune.util.model.Response;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/12
 */
@Deprecated
public interface UploadFacade {
    Response<String> upload(FileUpLoadRequest request);
}
