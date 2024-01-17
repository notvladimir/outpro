package com.outpro.test.service;

import com.outpro.dto.OptionDto;
import com.outpro.service.OptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OptionServiceTest {

    @Autowired
    private OptionService optionService;

    @Test
    void shouldReturnAllOptions() {
        assertThat(optionService.getAllOptions())
                .isNotEmpty()
                .hasSize(1285)
                .filteredOn(attr -> attr.getCode().equals("small_25_mm"))
                .isNotEmpty()
                .first()
                .extracting(OptionDto::getLabels)
                .asList()
                .isNotEmpty()
                .hasSize(16)
                .first()
                .extracting("locale", "value")
                .doesNotContainNull()
                .containsExactly("es_ES", "Small (25 mm)");
    }

}
