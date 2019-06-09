package com.qtu404.neptune.api.facade;

import com.qtu404.neptune.api.request.order.*;
import com.qtu404.neptune.api.response.order.OrderDetailResponse;
import com.qtu404.neptune.api.response.order.OrderThinResponse;
import com.qtu404.neptune.api.response.order.PaymentThinResponse;
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

    Response<Boolean> payOrder(OrderPayRequest request);

    Response<Paging<PaymentThinResponse>>  paymentPaging(PaymentPagingShopRequest request);

    Response<Boolean> cancel(OrderCancelRequest request);
}
