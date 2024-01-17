package com.outpro.util;

import com.outpro.dto.AttributeDto;
import com.outpro.dto.OptionDto;
import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class CommonUtils {
    public String[] getLocales(String line) {
        String[] split = line.split(";");
        String[] locales = Arrays.copyOfRange(split, 1, split.length);
        return Arrays.stream(locales)
                .map(l -> l.replaceAll("label-", ""))
                .toArray(String[]::new);
    }

    public void filterLabelsByLocale(AttributeDto attrDto, String locale) {
        attrDto.setLabels(
                attrDto.getLabels().stream()
                        .filter(l -> l.getLocale().equals(locale))
                        .toList());
    }

    public void filterLabelsByLocale(OptionDto optDto, String locale) {
        optDto.setLabels(
                optDto.getLabels().stream()
                        .filter(l -> l.getLocale().equals(locale))
                        .toList());
    }
}
