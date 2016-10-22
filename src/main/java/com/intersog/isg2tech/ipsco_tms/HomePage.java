package com.intersog.isg2tech.ipsco_tms;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Dmytro on 22.10.2016.
 */
@Component
@Scope("request")
public class HomePage {
    private String customHomePage="custom             HomePage";

    public String getCustomHomePage() {
        return customHomePage;
    }

    public void setCustomHomePage(String customHomePage) {
        this.customHomePage = customHomePage;
    }
}
