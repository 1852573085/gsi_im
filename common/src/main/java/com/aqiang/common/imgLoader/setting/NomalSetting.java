package com.aqiang.common.imgLoader.setting;

import android.widget.ImageView;

public class NomalSetting extends ImgSetting {

    public NomalSetting(Builder builder) {
        this.imageView = builder.imageView;
        this.urlPath = builder.urlPath;
    }

    private static class Builder{
        private String urlPath;
        private ImageView imageView;

        public Builder setUrlPath(String urlPath){
            this.urlPath = urlPath;
            return this;
        }

        public Builder setImageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public NomalSetting build(){
            return new NomalSetting(this);
        }
    }
}
