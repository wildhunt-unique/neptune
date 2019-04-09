package com.qtu404.neptune.api.facade;

import com.qtu404.neptune.api.request.order.OrderDetailRequest;
import com.qtu404.neptune.api.request.order.OrderPagingRequest;
import com.qtu404.neptune.api.request.order.OrderUpdateRequest;
import com.qtu404.neptune.api.request.order.OrderCreateRequest;
import com.qtu404.neptune.api.response.order.OrderDetailResponse;
import com.qtu404.neptune.api.response.order.OrderThinResponse;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/18 下午6:44
 */
public interface OrderFacade {
    Response<Long> createOrder(OrderCreateRequest request);

    Response<Boolean> updateOrder(OrderUpdateRequest request);

    Response<OrderDetailResponse> getOrderDetail(OrderDetailRequest request);

    Response<Paging<OrderThinResponse>> paging(OrderPagingRequest request);
}
