package com.flora.chatter.Payload.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateProfileReq {
    private String firstName;

    private String lastName;

    @Email
    private String email;

    private String gender;

    private String profileImage;

    @Size(min = 10, max = 15)
    private String mobile;

    @NotBlank
    @Size(min = 5, message = "Password field should be more than 5 characters long")
    private String password;

    @Size(max = 30, message = "Password field should be more than 5 characters long")
    private String bio;
}
