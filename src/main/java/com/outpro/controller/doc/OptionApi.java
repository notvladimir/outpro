package com.outpro.controller.doc;

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

@Tag(name = "option")
@RequestMapping("v1/options")
public interface OptionApi {

    @Operation(summary = "Return all options", description = "Return array of all options")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Return array of options",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = OptionDto.class,
                                            type = "object"))))})
    @GetMapping
    ResponseEntity<List<OptionDto>> getAllOptions(@RequestParam(required = false) String locale);

    @Operation(summary = "Return options filter by attribute", description = "Return array of filtered options")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Return array of options",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = OptionDto.class,
                                            type = "object"))))})
    @GetMapping("attribute/{attributeCode}")
    ResponseEntity<List<OptionDto>> getOptionsByAttribute(@PathVariable String attributeCode,
                                                          @RequestParam(required = false) String locale);

    @Operation(summary = "Return options available locales", description = "Return options available locales")
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
    List<String> getOptionsLocales();
}
