package com.lee.helper.recycler.beans;

import com.lee.helper.recycler.model.IDevItemModel;

public class CheckItem implements IDevItemModel
{
    private String title;
    private boolean isChecked;

    public CheckItem(String title , boolean isChecked){
        this.title = title;
        this.isChecked = isChecked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
