package com.yasin.hosain.dynamicviewusingjson.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yasin.hosain.dynamicviewusingjson.utils.Constants;

import java.util.List;

public class JSONFeed {

    @JsonProperty(Constants.JSON_KEY_VIEWS)
    private List<DYView> views;

    public JSONFeed() {
    }

    public JSONFeed(List<DYView> views) {
        this.views = views;
    }

    public List<DYView> getViews() {
        return views;
    }

    public void setViews(List<DYView> views) {
        this.views = views;
    }
}
