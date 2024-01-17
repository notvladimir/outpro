package com.outpro.service;

import com.outpro.dto.AttributeDto;
import com.outpro.dto.AttributeFullDataDto;
import com.outpro.dto.OptionDto;

import java.util.List;
import java.util.Optional;

public interface AttributeService {

    List<AttributeDto> getAllAttributes();
    List<AttributeDto> getAllAttributes(String locale);
    Optional<AttributeFullDataDto> getAttributeByCode(String code);
    Optional<AttributeFullDataDto> getAttributeByCode(String code, String locale);
    List<OptionDto> getAttributeOptions(String attributeCode);
    List<OptionDto> getAttributeOptions(String attributeCode, String locale);
    List<String> getAttributesLocales();
}
