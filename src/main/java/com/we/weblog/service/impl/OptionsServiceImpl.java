package com.we.weblog.service.impl;

import com.we.weblog.domain.Options;
import com.we.weblog.service.OptionsService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author tangwei
 * @date 2018/10/24 9:43
 */
@Service
public class OptionsServiceImpl implements OptionsService {
    @Override
    public void saveOption(String key, String value) {

    }

    @Override
    public void saveOptions(Map<String, String> options) {

    }

    @Override
    public Map<String, String> findAllOptions() {
        return null;
    }

    @Override
    public void removeOption(Options options) {

    }
}
