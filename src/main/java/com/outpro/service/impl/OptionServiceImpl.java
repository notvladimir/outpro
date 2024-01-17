package com.outpro.service.impl;

import com.outpro.dao.OptionDao;
import com.outpro.dto.LabelDto;
import com.outpro.dto.OptionDto;
import com.outpro.service.OptionService;
import com.outpro.util.CommonUtils;
import com.outpro.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionDao optionDao;

    @Override
    public List<OptionDto> getAllOptions() {
        return optionDao.getAllOptions()
                .stream()
                .map(optEnt -> {
                    var dto = new OptionDto();
                    dto.setCode(optEnt.getCode());
                    dto.setAttributeCode(optEnt.getAttributeCode());
                    dto.setSortOrder(optEnt.getSortOrder());
                    List<LabelDto> labels = optEnt.getLabels()
                            .stream()
                            .map(EntityDtoUtil::toDto)
                            .toList();
                    dto.setLabels(labels);
                    return dto;
                })
                .toList();
    }

    @Override
    public List<OptionDto> getAllOptions(String locale) {
        return getAllOptions().stream()
                .peek(o -> CommonUtils.filterLabelsByLocale(o, locale))
                .toList();
    }

    @Override
    public List<OptionDto> getOptionsByAttributeCode(String attributeCode) {
        return getAllOptions().stream()
                .filter(o -> o.getAttributeCode().equals(attributeCode))
                .toList();
    }

    @Override
    public List<OptionDto> getOptionsByAttributeCode(String attributeCode, String locale) {
        return getAllOptions().stream()
                .filter(o -> o.getAttributeCode().equals(attributeCode))
                .peek(o -> CommonUtils.filterLabelsByLocale(o, locale))
                .toList();
    }

    @Override
    public List<String> getOptionsLocales() {
        return getAllOptions().stream()
                .map(a -> a.getLabels().stream()
                        .map(LabelDto::getLocale)
                        .toList()
                ).findFirst()
                .orElse(new ArrayList<String>());
    }
}
