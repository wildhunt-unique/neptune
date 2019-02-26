package com.qut404.neptune.api.request.user;

import com.qtu404.neptune.util.model.AbstractRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.client.RestClientException;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindSingleUserInfoRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 9213294151679558866L;
    private Long userId;

    @Override
    public void checkParam() {
        super.checkParam();
        if (this.userId == null) throw new RestClientException("user.id.not.be.null");
    }
}
