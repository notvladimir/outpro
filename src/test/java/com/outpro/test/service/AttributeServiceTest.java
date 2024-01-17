package com.outpro.test.service;

import com.outpro.dto.AttributeDto;
import com.outpro.service.AttributeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AttributeServiceTest {

    @Autowired
    private AttributeService attributeService;

    @Test
    void shouldReturnAllAttributes() {
        assertThat(attributeService.getAllAttributes())
                .isNotEmpty()
                .hasSize(18)
                .filteredOn(attr -> attr.getCode().equals("size"))
                .isNotEmpty()
                .first()
                .extracting(AttributeDto::getLabels)
                .asList()
                .isNotEmpty()
                .hasSize(17)
                .first()
                .extracting("locale", "value")
                .doesNotContainNull()
                .containsExactly("it_IT", "Size");
    }
}
