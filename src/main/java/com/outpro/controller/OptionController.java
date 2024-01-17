package com.outpro.controller;

import com.outpro.controller.doc.OptionApi;
import com.outpro.dto.OptionDto;
import com.outpro.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OptionController implements OptionApi {

    private final OptionService optionService;

    @Override
    public ResponseEntity<List<OptionDto>> getAllOptions(String locale) {
        List<OptionDto> options = locale == null
                ? optionService.getAllOptions()
                : optionService.getAllOptions(locale);
        return ResponseEntity.ok().body(options);
    }

    @Override
    public ResponseEntity<List<OptionDto>> getOptionsByAttribute(String attributeCode, String locale) {
        List<OptionDto> options = locale == null
                ? optionService.getOptionsByAttributeCode(attributeCode)
                : optionService.getOptionsByAttributeCode(attributeCode, locale);
        return ResponseEntity.ok().body(options);
    }

    @Override
    public List<String> getOptionsLocales() {
        return optionService.getOptionsLocales();
    }
}
