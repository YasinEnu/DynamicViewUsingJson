
package com.yasin.hosain.dynamicviewusingjson.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yasin.hosain.dynamicviewusingjson.utils.Constants;

public class DYTextView extends DYView {
    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    @JsonProperty(Constants.JSON_KEY_TEXT)
    private String text;

    @JsonProperty(Constants.JSON_KEY_TEXT_SIZE)
    private int textSize;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
