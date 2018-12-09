package com.we.weblog.domain.modal;

import com.baomidou.kisso.SSOConfig;

public class SsoConfig {

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
