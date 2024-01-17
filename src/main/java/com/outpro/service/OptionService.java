package com.outpro.service;

import com.outpro.dto.OptionDto;

import java.util.List;

public interface OptionService {
    List<OptionDto> getAllOptions();
    List<OptionDto> getAllOptions(String locale);
    List<OptionDto> getOptionsByAttributeCode(String attributeCode);
    List<OptionDto> getOptionsByAttributeCode(String attributeCode, String locale);
    List<String> getOptionsLocales();
}
