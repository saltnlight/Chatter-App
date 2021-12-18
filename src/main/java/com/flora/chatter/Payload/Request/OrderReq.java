package com.flora.chatter.Payload.Request;

import com.flora.chatter.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReq {
    private Long id;

    private Product product;
}
