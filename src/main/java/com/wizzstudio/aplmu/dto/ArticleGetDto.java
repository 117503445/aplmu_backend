package com.wizzstudio.aplmu.dto;

import com.wizzstudio.aplmu.entity.Article;
import com.wizzstudio.aplmu.util.SecurityUtil;

public class ArticleGetDto {
    private Integer id;
    private String title;
    private String content;
    private String author;
    private long createdTimeStamp;
    private int pageView;
    private String titleImage;

    public ArticleGetDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.author = SecurityUtil.FromIdGetUserName(article.getAuthorID());
        this.createdTimeStamp = article.getCreatedTimeStamp();
        this.pageView = article.getPageView();
        this.titleImage = article.getTitleImage();
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
