package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.qtu404.neptune.api.facade.OrderLineFacade;
import com.qtu404.neptune.domain.service.OrderLineReadService;
import com.qtu404.neptune.domain.service.OrderLineWriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/20 下午5:20
 */
@Component
@Service(interfaceClass = OrderLineFacade.class)
@Slf4j
public class OrderLineFacadeImpl implements OrderLineFacade {

    @Autowired
    public OrderLineFacadeImpl() {
    }
}
