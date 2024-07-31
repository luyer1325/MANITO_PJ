package com.leeds.manito.manito_pj.dto;

public class MissionInfoDTO {
    private int manitoIdx;
    private int missionIdx;
    private int degree;
    private String contactUser;
    private String missionTime;
    private String title;
    private String content;
    private String created;
    private String modified;
    private String deleted;

    public int getManitoIdx() {
        return this.manitoIdx;
    }

    public void setManitoIdx(int manitoIdx) {
        this.manitoIdx = manitoIdx;
    }

    public int getMissionIdx() {
        return this.missionIdx;
    }

    public void setMissionIdx(int missionIdx) {
        this.missionIdx = missionIdx;
    }

    public int getDegree() {
        return this.degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getContactUser() {
        return this.contactUser;
    }

    public void setContactUser(String contactUser) {
        this.contactUser = contactUser;
    }

    public String getMissionTime() {
        return this.missionTime;
    }

    public void setMissionTime(String missionTime) {
        this.missionTime = missionTime;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return this.modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getDeleted() {
        return this.deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

}
