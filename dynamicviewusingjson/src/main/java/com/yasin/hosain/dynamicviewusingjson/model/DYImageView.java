package com.yasin.hosain.dynamicviewusingjson.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yasin.hosain.dynamicviewusingjson.utils.Constants;

/**
 * Created by Yasin Hosain on 11/3/2019.
 * yasinenubd5@gmail.com
 */
public class DYImageView extends DYView {

    @JsonProperty(Constants.JSON_IMAGE_SOURCE)
    private String imageSource;

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }
}
