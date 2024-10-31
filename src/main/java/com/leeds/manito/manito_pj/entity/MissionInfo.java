package com.leeds.manito.manito_pj.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mission_info")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MissionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_idx")
    private int missionIdx;

    @Column(name = "manito_idx")
    private int manitoIdx;

    @Column(name = "degree")
    private int degree;

    @Column(name = "create_user", length = 20)
    private String contactUser;

    @Column(name = "mission_time", columnDefinition = "TIMESTAMP")
    private String missionTime;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "created", columnDefinition = "TIMESTAMP")
    private String created;

    @Column(name = "modified", columnDefinition = "TIMESTAMP")
    private String modified;

    @Column(name = "deleted", columnDefinition = "TIMESTAMP")
    private String deleted;

}
