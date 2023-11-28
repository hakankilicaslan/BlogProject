package com.hakan.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRegisterRequestDto {
    @Email(message = "Lütfen uygun bir email adresi giriniz...")
    private String email;
    private String name;
    private String surname;
    private String password;
    private String rePassword;
}
