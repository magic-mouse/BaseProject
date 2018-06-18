package com.github.dronezcc.riser.gui.domain;

public class SendValue {

    private String secret;
    private String response;

    public SendValue() {

    }

    public SendValue(String secret, String response) {
        this.secret = secret;
        this.response = response;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getSecret() {
        return secret;
    }

    public String getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "SendValue{" +
                "secret='" + secret + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
