package com.aqiang.common.imgLoader.setting;

import android.widget.ImageView;

public class CircleSetting extends ImgSetting {
    private boolean isCircle;

    public boolean isCircle() {
        return isCircle;
    }

    public void setCircle(boolean circle) {
        isCircle = circle;
    }

    public CircleSetting(Builder builder) {
        this.isCircle = builder.isCircle;
        this.imageView = builder.imageView;
        this.urlPath = builder.urlPath;
    }

    public static Builder Builder(){
        return new Builder();
    }

    public static class Builder{
        private String urlPath;
        private ImageView imageView;
        private boolean isCircle;

        public Builder setUrlPath(String urlPath) {
            this.urlPath = urlPath;
            return this;
        }


        public Builder setImageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }


        public Builder setCircle(boolean circle) {
            isCircle = circle;
            return this;
        }

        public CircleSetting build(){
            return new CircleSetting(this);
        }
    }
}
