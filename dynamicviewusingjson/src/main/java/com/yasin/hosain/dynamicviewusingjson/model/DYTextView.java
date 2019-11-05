
package com.yasin.hosain.dynamicviewusingjson.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.yasin.hosain.dynamicviewusingjson.utils.Constants;



@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = Constants.JSON_KEY_TYPE, visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(name = Constants.TYPE_EDIT_TEXT, value = DYEditText.class)
})

public class DYTextView extends DYView {

    @JsonProperty(Constants.JSON_KEY_TEXT)
    private String text;
    @JsonProperty(Constants.JSON_KEY_TEXT_SIZE)
    private int textSize;
    @JsonProperty(Constants.JSON_KEY_TEXT_STYLE)
    private String textStyle;
    @JsonProperty(Constants.JSON_KEY_HINT)
    private String hint;
    @JsonProperty(Constants.JSON_KEY_SINGLE_LINE)
    private boolean singleLine;


    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(String textStyle) {
        this.textStyle = textStyle;
    }

    public String  getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public boolean isSingleLine() {
        return singleLine;
    }

    public void setSingleLine(boolean singleLine) {
        this.singleLine = singleLine;
    }
}
