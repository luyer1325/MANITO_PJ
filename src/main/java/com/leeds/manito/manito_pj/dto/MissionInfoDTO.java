package com.leeds.manito.manito_pj.dto;
import java.util.List;

public class MissionInfoDTO {
    private int manito_idx;
    private int index;
    private int degree;
    private String contact_user;
    private String mission_time;
    private String title;
    private String content;
    private String created;
    private String modified;
    private String delete;
    private List<MissionInfoDTO> missionList;

    public int getManito_idx() {
        return this.manito_idx;
    }

    public void setManito_idx(int manito_idx) {
        this.manito_idx = manito_idx;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getDegree() {
        return this.degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getContact_user() {
        return this.contact_user;
    }

    public void setContact_user(String contact_user) {
        this.contact_user = contact_user;
    }

    public String getMission_time() {
        return this.mission_time;
    }

    public void setMission_time(String mission_time) {
        this.mission_time = mission_time;
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

    public String getDelete() {
        return this.delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public List<MissionInfoDTO> getList() {
        return this.missionList;
    }

    public void setMissionList(List<MissionInfoDTO> missionList) {
        this.missionList = missionList;
    }
}
