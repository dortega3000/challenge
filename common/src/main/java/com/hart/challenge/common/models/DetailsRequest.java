package com.hart.challenge.common.models;

/**
 * Created by dortega on 2/15/16.
 */
public class DetailsRequest {
    public String title;
    public String exchange;


    public DetailsRequest(String title, String exchange) {
        this.title = title;
        this.exchange = exchange;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
