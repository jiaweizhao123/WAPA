package com.sazkuyo.ultrawallpaper2.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class token_msg {
    private String token;
    private String src;
    private String sm_src;

    public token_msg() {
    }

    public token_msg(String token, String src, String sm_src) {
        this.token = token;
        this.src = src;
        this.sm_src = sm_src;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSm_src() {
        return sm_src;
    }

    public void setSm_src(String sm_src) {
        this.sm_src = sm_src;
    }
}
