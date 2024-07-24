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
@Table(name = "manito_info")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManitoInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manito_idx")
    private int manito_idx;

    @Column(name = "create_user",length=20)
    private String create_user;

    @Column(name = "show_yn",length=2)
    private String show_yn;

    @Column(name = "join_yn",length=2)
    private String join_yn;

    @Column(name = "mission_yn",length=2)
    private String mission_yn;

    @Column(name = "mission_time", columnDefinition = "TIMESTAMP")
    private String mission_time;

    @Column(name = "start_date", columnDefinition = "TIMESTAMP")
    private String start_date;

    @Column(name = "end_date", columnDefinition = "TIMESTAMP")
    private String end_date;

    @Column(name = "created", columnDefinition = "TIMESTAMP")
    private String created;

    @Column(name = "modified", columnDefinition = "TIMESTAMP")
    private String modified;

    @Column(name = "deleted", columnDefinition = "TIMESTAMP")
    private String deleted;
}
