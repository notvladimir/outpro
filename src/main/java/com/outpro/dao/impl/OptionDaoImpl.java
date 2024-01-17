package com.outpro.dao.impl;

import com.outpro.dao.OptionDao;
import com.outpro.entity.LabelEntity;
import com.outpro.entity.OptionEntity;
import com.outpro.util.CommonUtils;
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
public class OptionDaoImpl implements OptionDao {

    @Value( "${file-location.options}" )
    private String fileLocationOptions;

    @Override
    public List<OptionEntity> getAllOptions() {
        List<OptionEntity> options = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(
                new FileReader(ResourceUtils.getFile(fileLocationOptions)))) {
            String[] locales = CommonUtils.getLocales(br.readLine());
            while ((line = br.readLine()) != null) {
                OptionEntity optionEntity = new OptionEntity();
                options.add(optionEntity);
                final String[] columnsValues = line.split(";");
                optionEntity.setCode(columnsValues[0]);
                optionEntity.setAttributeCode(columnsValues[columnsValues.length - 2]);
                optionEntity.setSortOrder(Integer.parseInt(columnsValues[columnsValues.length - 1]));
                String[] columnsLabelsValues = Arrays.copyOfRange(columnsValues, 1, columnsValues.length - 3);
                for(int i = 0; i < columnsLabelsValues.length; i++) {
                    optionEntity.addLabel(new LabelEntity(locales[i], columnsLabelsValues[i].replaceAll("\"", "")));
                }
            }

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return options;
    }
}
