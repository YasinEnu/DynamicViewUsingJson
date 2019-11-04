

package com.yasin.hosain.dynamicviewusingjson.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.yasin.hosain.dynamicviewusingjson.utils.Constants;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = Constants.JSON_KEY_TYPE, visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(name = Constants.TYPE_TEXT_VIEW, value = DYTextView.class),
        @JsonSubTypes.Type(name = Constants.TYPE_EDIT_TEXT, value = DYEditText.class),
        @JsonSubTypes.Type(name = Constants.TYPE_CHECKBOX, value = DYCheckbox.class),
        @JsonSubTypes.Type(name = Constants.TYPE_CHECKBOX_GROUP, value = DYCheckboxGroup.class),
        @JsonSubTypes.Type(name = Constants.TYPE_RADIO_GROUP, value = DYRadioGroup.class),
        @JsonSubTypes.Type(name = Constants.TYPE_DROP_DOWN_LIST, value = DYDropDownList.class),
        @JsonSubTypes.Type(name = Constants.TYPE_BUTTON, value = DYButton.class),
        @JsonSubTypes.Type(name = Constants.TYPE_IMAGE_VIEW, value = DYImageView.class)
})
public class DYView {
    @JsonProperty(Constants.JSON_KEY_TYPE)
    private String type;
    @JsonProperty(Constants.JSON_KEY_TAG)
    private String tag;
    @JsonProperty(Constants.JSON_KEY_GRAVITY)
    private String gravity;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }
}
