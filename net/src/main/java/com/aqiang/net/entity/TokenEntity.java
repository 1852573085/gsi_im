package com.aqiang.net.entity;

public class TokenEntity {

    /**
     * access_token : eYa8EJmFiX3t8XhR89JB4-83ZKJl0VnLnANV9rZZ1SgsfPP1r2B7Mndidc7JYGYXUV64yCbz5yllWuY_2_F82T3ERmlfVluOIJURxclX4sU4MqiACp8fMhXOlYOTROwNQc2xkiEzd_gFzPFvZuXoI-VG4OKKFh1hYV8gp_yyAiWIvtVt-5TxCYc6QwWNxhaaD-B-5JwxkrLtybpTJ9iJ2n5-G2yMZ8K9HVB_UY5qOVWbD1WCZa4UDhnzfRhn3ST3g-BjyGnXv_t71qR5JkT6VssvTXAn-p7zlop5MZSrf8M
     * token_type : bearer
     * expires_in : 86399
     */

    private String access_token;
    private String token_type;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
