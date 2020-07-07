package com.wizzstudio.aplmu.dto;

import com.wizzstudio.aplmu.entity.ArticleEntity;
import com.wizzstudio.aplmu.util.SecurityUtil;

public class ArticleGetDto {
    private Integer id;
    private String title;
    private String content;
    private String author;
    private long createdTimeStamp;
    private int pageView;
    private String titleImage;

    public ArticleGetDto(ArticleEntity articleEntity) {
        this.id = articleEntity.getId();
        this.title = articleEntity.getTitle();
        this.content = articleEntity.getContent();
        this.author = SecurityUtil.FromIdGetUserName(articleEntity.getAuthorID());
        this.createdTimeStamp = articleEntity.getCreatedTimeStamp();
        this.pageView = articleEntity.getPageView();
        this.titleImage = articleEntity.getTitleImage();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(long createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public int getPageView() {
        return pageView;
    }

    public void setPageView(int pageView) {
        this.pageView = pageView;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
