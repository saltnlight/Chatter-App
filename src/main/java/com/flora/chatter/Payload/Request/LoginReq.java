package com.flora.chatter.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq {
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
