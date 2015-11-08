package com.yoghourt.wolfcoder.shrinkmenu.entity;

import android.widget.ImageView;

/**
 * Description:图片位置数据
 * User: Dream_Coder(chenchen_839@126.com)
 * Date: 2015-11-05
 * Time: 12:40
 */
public class ImageLocationEntity {
    private int left;
    private int top;
    private int right;
    private int bottom;
    private ImageView imageView;

    public ImageLocationEntity(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
