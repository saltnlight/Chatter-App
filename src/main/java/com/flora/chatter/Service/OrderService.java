package com.flora.chatter.Service;

import com.flora.chatter.Model.AppUser;
//import com.flora.chatter.Model.Order;
import com.flora.chatter.Payload.Request.OrderReq;

public interface OrderService {
//    Order createOrder(Long productID, AppUser user);

    Boolean deleteOrder(OrderReq orderReq, AppUser user);
}
