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
public class OptionEntity {
    private String code;
    private List<LabelEntity> labels;
    private String attributeCode;
    private int sortOrder;

    public void addLabel(LabelEntity label) {
        if(labels == null) {
            labels = new ArrayList<>();
        }
        labels.add(label);
    }
}
