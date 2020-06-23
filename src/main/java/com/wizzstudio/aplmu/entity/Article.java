package com.wizzstudio.aplmu.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private String content;
    private String author;
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CreatedDate
    private long createdTimeStamp;
    private int pageView;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public void IncPageView() {
        pageView++;
    }
}
