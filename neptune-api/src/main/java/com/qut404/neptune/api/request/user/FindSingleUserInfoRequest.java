package com.qut404.neptune.api.request.user;

import com.qtu404.neptune.util.model.AbstractRequest;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.web.client.RestClientException;

import java.io.Serializable;


@ApiModel("用户信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FindSingleUserInfoRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 9213294151679558866L;
    private Long userId;

    @Override
    public void checkParam() {
        super.checkParam();
        if (this.userId == null) throw new RestClientException("user.id.not.be.null");
    }
}
