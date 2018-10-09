package com.we.weblog.intercaptor;

import com.baomidou.kisso.SSOConfig;

public class WebKissoConfigurer {

    private SSOConfig ssoConfig;

    public void initKisso() {
        SSOConfig.init(this.getSsoConfig());
    }

    public SSOConfig getSsoConfig() {
        return ssoConfig;
    }

    public void setSsoConfig(SSOConfig ssoConfig) {
        this.ssoConfig = ssoConfig;
    }
}
