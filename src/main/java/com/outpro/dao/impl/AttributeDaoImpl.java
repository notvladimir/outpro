package com.outpro.dao.impl;

import com.outpro.dao.AttributeDao;
import com.outpro.dao.OptionDao;
import com.outpro.entity.AttributeEntity;
import com.outpro.entity.LabelEntity;
import com.outpro.entity.OptionEntity;
import com.outpro.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AttributeDaoImpl implements AttributeDao {

    @Value( "${file-location.attributes}" )
    private String fileLocationAttributes;

    private final OptionDao optionDao;

    @Override
    public List<AttributeEntity> getAllAttributes() {
        List<OptionEntity> allOptions = optionDao.getAllOptions();
        List<AttributeEntity> attributes = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(
                new FileReader(ResourceUtils.getFile(fileLocationAttributes)))) {
            String[] locales = CommonUtils.getLocales(br.readLine());
            while ((line = br.readLine()) != null) {
                AttributeEntity attrEntity = new AttributeEntity();
                attributes.add(attrEntity);
                List<LabelEntity> labels = new ArrayList<>();
                attrEntity.setLabels(labels);
                String[] columnsValues = line.split(";");
                attrEntity.setCode(columnsValues[0]);
                columnsValues = Arrays.copyOfRange(columnsValues, 1, columnsValues.length);
                for(int i = 0; i < columnsValues.length; i++) {
                    labels.add(new LabelEntity(locales[i], columnsValues[i].replaceAll("\"", "")));
                }
                List<OptionEntity> options = allOptions.stream()
                        .filter(o -> o.getAttributeCode().equals(attrEntity.getCode()))
                        .toList();
                attrEntity.setOptions(options);
            }

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return attributes;
    }
}
