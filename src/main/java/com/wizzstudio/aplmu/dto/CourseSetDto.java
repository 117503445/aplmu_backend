package com.wizzstudio.aplmu.dto;

import com.wizzstudio.aplmu.entity.CourseEntity;

public class CourseSetDto {
    private String name;//课程名
    private String createTime;
    private String summary;
    private String imageUrl;
    private String videoUrl;
    private double price;
    private Integer ChildCourseNum;//有多少集
    private String author;
    private double duration;//时长

    public CourseEntity toEntity() {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(name);
        courseEntity.setCreateTime(createTime);
        courseEntity.setSummary(summary);
        courseEntity.setImageUrl(imageUrl);
        courseEntity.setVideoUrl(videoUrl);
        courseEntity.setPrice(price);
        courseEntity.setChildCourseNum(ChildCourseNum);
        courseEntity.setDuration(duration);
        return courseEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getChildCourseNum() {
        return ChildCourseNum;
    }

    public void setChildCourseNum(Integer childCourseNum) {
        ChildCourseNum = childCourseNum;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
