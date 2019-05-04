package com.qtu404.neptune.server.converter;

import com.qtu404.neptune.api.response.user.UserMetaData;
import com.qtu404.neptune.common.constant.ConstantValues;
import com.qtu404.neptune.domain.enums.UserTypeEnum;
import com.qtu404.neptune.domain.model.User;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/4
 */
public class UserMetaDataConverter {
    public static UserMetaData model2Response(User user){
        if (Objects.isNull(user)){
            return null;
        }else {
            UserMetaData metaData= new UserMetaData();
            metaData.setUserId(user.getId());
            metaData.setLevel(user.getType());
            if (user.getType().equals(UserTypeEnum.SELLER.getCode())){
                if (!CollectionUtils.isEmpty(user.getExtra())){
                    metaData.setShopId(Long.valueOf(String.valueOf(user.getExtra().get(ConstantValues.SHOP_ID_KEY))));
                }
            }
            return metaData;
        }
    }
}
