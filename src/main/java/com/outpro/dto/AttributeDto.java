package com.outpro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(name = "Attribute")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeDto {
    @Schema(requiredMode = REQUIRED, example = "helmetsize", description = "Attribute code")
    private String code;

    @Schema(requiredMode = REQUIRED, description = "Attribute labels")
    private List<LabelDto> labels;
}
