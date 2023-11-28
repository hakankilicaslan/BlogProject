package com.hakan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserSaveRequestDto {
    Long id;
    String name;
    String surname;
    String email;
    String password;
}
