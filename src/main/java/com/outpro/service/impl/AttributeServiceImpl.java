package com.outpro.service.impl;

import com.outpro.dao.AttributeDao;
import com.outpro.dto.AttributeDto;
import com.outpro.dto.AttributeFullDataDto;
import com.outpro.dto.LabelDto;
import com.outpro.dto.OptionDto;
import com.outpro.service.AttributeService;
import com.outpro.service.OptionService;
import com.outpro.util.CommonUtils;
import com.outpro.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {

    private final OptionService optionService;
    private final AttributeDao attributeDao;

    @Override
    public List<AttributeDto> getAllAttributes() {
        return attributeDao.getAllAttributes()
                .stream()
                .map(attrEnt -> {
                    var dto = new AttributeDto();
                    dto.setCode(attrEnt.getCode());
                    List<LabelDto> labels = attrEnt.getLabels()
                            .stream()
                            .map(EntityDtoUtil::toDto)
                            .toList();
                    dto.setLabels(labels);
                    return dto;
                })
                .toList();
    }

    @Override
    public List<AttributeDto> getAllAttributes(String locale) {
        return getAllAttributes().stream()
                .peek(a -> CommonUtils.filterLabelsByLocale(a, locale))
                .toList();
    }

    @Override
    public Optional<AttributeFullDataDto> getAttributeByCode(String code) {
        return getAllAttributes().stream()
                .filter(a -> a.getCode().equals(code))
                .findFirst()
                .map(attrDto -> {
                    var attrFullDataDto = new AttributeFullDataDto();
                    BeanUtils.copyProperties(attrDto, attrFullDataDto);
                    List<OptionDto> options = optionService.getOptionsByAttributeCode(code);
                    attrFullDataDto.setOptions(options);
                    return  attrFullDataDto;
                });
    }

    @Override
    public Optional<AttributeFullDataDto> getAttributeByCode(String code, String locale) {
        return getAllAttributes().stream()
                .filter(a -> a.getCode().equals(code))
                .peek(a -> CommonUtils.filterLabelsByLocale(a, locale))
                .findFirst()
                .map(attrDto -> {
                    var attrFullDataDto = new AttributeFullDataDto();
                    BeanUtils.copyProperties(attrDto, attrFullDataDto);
                    List<OptionDto> options = optionService.getOptionsByAttributeCode(code, locale);
                    attrFullDataDto.setOptions(options);
                    return  attrFullDataDto;
                });
    }

    @Override
    public List<OptionDto> getAttributeOptions(String attributeCode) {
        return optionService.getOptionsByAttributeCode(attributeCode);
    }

    @Override
    public List<OptionDto> getAttributeOptions(String attributeCode, String locale) {
        return optionService.getOptionsByAttributeCode(attributeCode, locale);
    }

    @Override
    public List<String> getAttributesLocales() {
        return getAllAttributes().stream()
                .map(a -> a.getLabels().stream()
                        .map(LabelDto::getLocale)
                        .toList()
                ).findFirst()
                .orElse(new ArrayList<String>());
    }
}
