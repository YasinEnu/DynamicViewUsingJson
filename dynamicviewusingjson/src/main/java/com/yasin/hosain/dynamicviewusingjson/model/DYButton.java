package com.yasin.hosain.dynamicviewusingjson.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yasin.hosain.dynamicviewusingjson.utils.Constants;

/**
 * Created by Yasin Hosain on 11/3/2019.
 * yasinenubd5@gmail.com
 */
public class DYButton extends DYView {

    @JsonProperty(Constants.JSON_KEY_NAME)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
