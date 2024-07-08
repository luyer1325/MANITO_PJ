package com.leeds.manito.manito_pj.dto;


public class ManitoInfoDTO {
    private int manito_idx;
    private String create_user;


    private String show_yn;
    private String join_yn;
    private String mission_yn;
    private String mission_time;
    private String start_date;
    private String end_date;
    private String created;
    private String modified;
    private String delete;

    public int getManito_idx() {
        return this.manito_idx;
    }

    public void setManito_idx(int manito_idx) {
        this.manito_idx = manito_idx;
    }

    public String getCreate_user() {
        return this.create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getShow_yn() {
        return this.show_yn;
    }

    public void setShow_yn(String show_yn) {
        this.show_yn = show_yn;
    }

    public String getJoin_yn() {
        return this.join_yn;
    }

    public void setJoin_yn(String join_yn) {
        this.join_yn = join_yn;
    }

    public String getMission_yn() {
        return this.mission_yn;
    }

    public void setMission_yn(String mission_yn) {
        this.mission_yn = mission_yn;
    }

    public String getMission_time() {
        return this.mission_time;
    }

    public void setMission_time(String mission_time) {
        this.mission_time = mission_time;
    }

    
    public String getStart_date() {
        return this.start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return this.end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
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

}
