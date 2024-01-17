package com.outpro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeEntity {
    private String code;
    private List<LabelEntity> labels;
    private List<OptionEntity> options;

    public void addLabel(LabelEntity label) {
        if(labels == null) {
            labels = new ArrayList<>();
        }
        labels.add(label);
    }

    public void addOption(OptionEntity option) {
        if(option == null) {
            options = new ArrayList<>();
        }
        options.add(option);
    }
}
