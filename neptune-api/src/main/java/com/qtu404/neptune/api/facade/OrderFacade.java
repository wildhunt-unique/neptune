package com.qtu404.neptune.api.facade;

import com.qtu404.neptune.api.request.OrderDetailRequest;
import com.qtu404.neptune.api.request.OrderUpdateRequest;
import com.qtu404.neptune.api.request.order.OrderCreateRequest;
import com.qtu404.neptune.api.response.order.OrderDetailResponse;
import com.qtu404.neptune.util.model.Response;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/18 下午6:44
 */
public interface OrderFacade {
    Response<Long> createOrder(OrderCreateRequest request);

    Response<Boolean> updateOrder(OrderUpdateRequest request);

    Response<OrderDetailResponse> getOrderDetail(OrderDetailRequest request);
}
