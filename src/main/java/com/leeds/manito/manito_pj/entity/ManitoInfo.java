package com.leeds.manito.manito_pj.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "manito_info")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManitoInfo {

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

    @Temporal(TemporalType.TIMESTAMP)
    private String mission_time;

    @Temporal(TemporalType.TIMESTAMP)
    private String start_date;

    @Temporal(TemporalType.TIMESTAMP)
    private String end_date;

    @Temporal(TemporalType.TIMESTAMP)
    private String created;

    @Temporal(TemporalType.TIMESTAMP)
    private String modified;

    @Temporal(TemporalType.TIMESTAMP)
    private String deleted;
}
