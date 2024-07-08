package com.leeds.manito.manito_pj.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "manito_info")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManitoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manito_idx")
    private int manito_idx;

    @Column(name = "create_user")
    private String create_user;

    @Column(name = "show_yn")
    private String show_yn;

    @Column(name = "join_yn")
    private String join_yn;

    @Column(name = "mission_yn")
    private String mission_yn;

    @Column(name = "mission_time")
    private String mission_time;

    @Column(name = "start_date")
    private String start_date;

    @Column(name = "end_date")
    private String end_date;

    @Column(name = "created")
    private String created;

    @Column(name = "modified")
    private String modified;

    @Column(name = "deleted")
    private String deleted;

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

    public String getDeleted() {
        return this.deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}
