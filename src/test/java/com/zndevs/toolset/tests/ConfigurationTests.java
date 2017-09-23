package com.zndevs.toolset.tests;

import com.zndevs.toolset.ToolSetConfiguration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;

/**
 * Tests the Configuration class
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConfigurationTests {

    private ToolSetConfiguration configuration;

    // ToolSetConfiguration tests
    @Before
    public void initToolSetConfiguration() {
        // Create files
        File actualFile = new File(".\\testing-directory\\TOOLSET_CONFIGURATION_TEST_FILE.yml");
        actualFile.getParentFile().mkdirs();
        try {
            actualFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        configuration = new ToolSetConfiguration(new File(".\\testing-directory"));
    }

    @Test
    public void t1_loadFileTest() {
        // Doesn't actually test anything, just sets up for next test
        try {
            configuration.setFileName("TOOLSET_CONFIGURATION_TEST_FILE.yml");
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t2_checkFileNameTest() {
        Assert.assertEquals("The file name should be set.", "TOOLSET_CONFIGURATION_TEST_FILE.yml", configuration.getFileName());
        Assert.assertEquals("The directory name should be set.", configuration.getFilePath(), new File("testing-directory"));
    }

    @Test
    public void t3_setDataInFile() {
        // Doesn't actually test anything, just sets up for next test
        configuration.set("test.value", 10);
        try {
            // Then save the file
            configuration.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t4_getDataInFile() {
        // Reload
        try {
            configuration.setFileName("TOOLSET_CONFIGURATION_TEST_FILE.yml");
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("The value should have been saved.", configuration.getInt("test.value"), 10);
    }

}
