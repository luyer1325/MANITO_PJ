package com.leeds.manito.manito_pj.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ManitoInfoDTO {
    private int manitoIdx;
    private String createUser;
    private String showYn;
    private String joinYn;
    private String missionYn;
    private String missionTime;
    private String startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;
    private String created;
    private String modified;
    private String deleted;

    public int getManitoIdx() {
        return this.manitoIdx;
    }

    public void setManitoIdx(int manitoIdx) {
        this.manitoIdx = manitoIdx;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getShowYn() {
        return this.showYn;
    }

    public void setShowYn(String showYn) {
        this.showYn = showYn;
    }

    public String getJoinYn() {
        return this.joinYn;
    }

    public void setJoinYn(String joinYn) {
        this.joinYn = joinYn;
    }

    public String getMissionYn() {
        return this.missionYn;
    }

    public void setMissionYn(String missionYn) {
        this.missionYn = missionYn;
    }

    public String getMissionTime() {
        return this.missionTime;
    }

    public void setMissionTime(String missionTime) {
        this.missionTime = missionTime;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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
