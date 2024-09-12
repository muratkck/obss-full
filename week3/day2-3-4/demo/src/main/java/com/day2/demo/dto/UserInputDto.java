package com.day2.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInputDto implements Serializable {

    @NotNull
    @Size(min = 5, max = 20, message = "Kullanıcı adınız min 5 maks 20 olmalıdır!")
    private String username;

    @NotNull
    @Size(min=8, max = 20)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$"
            , message = "Şifreniz özel olmalıdır!")
    private String password;

    @NotNull
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    @Size(min = 2, max = 20)
    private String surname;
}
