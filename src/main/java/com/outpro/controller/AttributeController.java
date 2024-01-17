package com.outpro.controller;

import com.outpro.controller.doc.AttributeApi;
import com.outpro.dto.AttributeDto;
import com.outpro.dto.AttributeFullDataDto;
import com.outpro.dto.OptionDto;
import com.outpro.service.AttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AttributeController implements AttributeApi {

    private final AttributeService attributeService;

    @Override
    public ResponseEntity<List<AttributeDto>> getAllAttributes(String locale) {
        List<AttributeDto> attributes = locale == null
                ? attributeService.getAllAttributes()
                : attributeService.getAllAttributes(locale);
        return ResponseEntity.ok().body(attributes);
    }

    @Override
    public ResponseEntity<AttributeFullDataDto> getAllAttributes(String code, String locale) {
        Optional<AttributeFullDataDto> attribute = locale == null
                ? attributeService.getAttributeByCode(code)
                : attributeService.getAttributeByCode(code, locale);
        return attribute.map(attrDto -> ResponseEntity.ok().body(attrDto))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Override
    public ResponseEntity<List<OptionDto>> getAttributeOptions(String attributeCode, String locale) {
        List<OptionDto> attributeOptions = locale == null
                ? attributeService.getAttributeOptions(attributeCode)
                : attributeService.getAttributeOptions(attributeCode, locale);
        return ResponseEntity.ok().body(attributeOptions);
    }

    @Override
    public List<String> getAttributeLocaleNames() {
        return attributeService.getAttributesLocales();
    }
}
