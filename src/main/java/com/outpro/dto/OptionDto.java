package com.outpro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(name = "Option")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionDto {

    @Schema(requiredMode = REQUIRED, example = "9_10", description = "Option code")
    private String code;

    @Schema(requiredMode = REQUIRED, description = "Option labels")
    private List<LabelDto> labels;

    @Schema(requiredMode = REQUIRED, example = "shoesize", description = "Attribute code")
    private String attributeCode;

    @Schema(requiredMode = REQUIRED, example = "1", description = "Order number")
    private int sortOrder;
}
