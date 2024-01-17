package com.outpro.util;

import com.outpro.dto.LabelDto;
import com.outpro.entity.LabelEntity;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

@UtilityClass
public class EntityDtoUtil {
    public LabelDto toDto(LabelEntity entity) {
        LabelDto dto = new LabelDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
