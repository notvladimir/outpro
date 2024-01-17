package com.outpro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;


@Schema(name = "Label")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabelDto {

    @Schema(requiredMode = REQUIRED, example = "en_GB")
    private String locale;

    @Schema(requiredMode = NOT_REQUIRED, example = "Number in Package from Supplier")
    private String value;
}
