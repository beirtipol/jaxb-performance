package com.beirtipol.fix.repository;

import fixrepository.Field;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(locations = "./application-test.properties")
@EnableConfigurationProperties(FIXRepositoryConfig.class)
@ContextConfiguration(classes = {FIXRepositoryHelper.class})
public class FIXRepositoryHelperTest {

    @Autowired
    private FIXRepositoryHelper helper;

    @Test
    public void loadField35() {
        Optional<Field> field = helper.loadFieldInfo(35);
        assertTrue(field.isPresent());
    }
}
