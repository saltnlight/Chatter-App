package com.flora.chatter.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "This field id required")
    private String firstName;

    @NotBlank(message = "This field is required")
    private String lastName;

    @NotBlank(message = "This field is required")
    @Email
    private String email;

    private String gender;

    private String profileImage;

    @NotBlank(message = "This field is required")
    @Size(min = 10, max = 15)
    private String mobile;

    @NotBlank
    @Size(min = 5, message = "Password field should be more than 5 characters long")
    private String password;

    @Size(max = 30, message = "Password field should be more than 5 characters long")
    private String bio;
}
