package com.we.weblog.service.options;

import com.we.weblog.BaseTest;
import com.we.weblog.domain.Options;
import com.we.weblog.service.OptionsService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author Clay
 * @date 2019/3/28 15:10
 */
public class OptionsServiceTest extends BaseTest {


    @Resource
    private OptionsService optionsService;

    @Test
    public void saveOption() {
        optionsService.saveOption("","");
    }

    @Test
    public void saveOptions() {
        optionsService.saveOptions(new HashMap<>());

    }

    @Test
    public void findAllOptions() {
        optionsService.findAllOptions();

    }

    @Test
    public void removeOption() {
        optionsService.removeOption(new Options());

    }
}