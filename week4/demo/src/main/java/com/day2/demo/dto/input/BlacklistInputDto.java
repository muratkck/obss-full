package com.day2.demo.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class BlacklistInputDto implements Serializable {

    @NotNull(message = "Id of the user cannot be empty")
    private Long userId;

    @NotNull(message = "Id of the seller cannot be empty.")
    private Long sellerId;
}
