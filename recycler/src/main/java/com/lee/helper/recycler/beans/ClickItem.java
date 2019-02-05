package com.lee.helper.recycler.beans;

import com.lee.helper.recycler.model.IDevItemModel;

public class ClickItem implements IDevItemModel
{
    private String title;
    private String subTitle;
    private boolean isShowMore;

    public ClickItem(String title,String subTitle, boolean isShowMore){
        this.title = title;
        this.subTitle = subTitle;
        this.isShowMore = isShowMore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isShowMore() {
        return isShowMore;
    }

    public void setShowMore(boolean showMore) {
        isShowMore = showMore;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
