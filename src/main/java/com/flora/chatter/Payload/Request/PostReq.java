package com.flora.chatter.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostReq {
    private Long id;
    @NotBlank
    private String body;
    @NotBlank
    private String img;

    @NotBlank
    private Boolean isPersonal=false;

    private Double price;

    private String description;

    @NotBlank
    private Boolean isProduct=false;
}
