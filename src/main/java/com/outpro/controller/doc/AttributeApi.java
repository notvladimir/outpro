package com.outpro.controller.doc;


import com.outpro.dto.AttributeDto;
import com.outpro.dto.AttributeFullDataDto;
import com.outpro.dto.OptionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "attribute")
@RequestMapping("v1/attributes")
public interface AttributeApi {

    @Operation(summary = "Return all attributes", description = "Return array of all attributes")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Return array of attributes",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = AttributeDto.class,
                                            type = "object"))))})
    @GetMapping
    ResponseEntity<List<AttributeDto>> getAllAttributes(@RequestParam(required = false) String locale);

    @Operation(summary = "Return attribute with options", description = "Return attribute with options")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Return array of options",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = AttributeFullDataDto.class,
                                    type = "object")))})
    @GetMapping("{code}")
    ResponseEntity<AttributeFullDataDto> getAllAttributes(@PathVariable String code,
                                                          @RequestParam(required = false) String locale);

    @Operation(summary = "Return all attribute options", description = "Return attribute options")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Return attribute options",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = OptionDto.class,
                                            type = "object"))))})
    @GetMapping("{attributeCode}/options")
    ResponseEntity<List<OptionDto>> getAttributeOptions(@PathVariable String attributeCode,
                                                        @RequestParam(required = false) String locale);

    @Operation(summary = "Return attribute available locales", description = "Return attribute available locales")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Return array locales names",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            type = "string"))))})

    @GetMapping("locales")
    List<String> getAttributeLocaleNames();
}
