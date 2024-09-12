package com.day2.demo.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class FavoriteInputDto implements Serializable {

    @NotNull
    private Long userId;

    @NotNull
    private Long productId;

}
