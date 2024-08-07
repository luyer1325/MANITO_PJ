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
@Table(name = "user_info")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @Column(name="manito_idx")
    private int manitoIdx;

    @Id
    @Column(name="user_id", length= 20)
    private String userId;

    @Column(name="name", length = 10)
    private String name;

    @Column(name="manito_target", length = 10)
    private String manitoTarget;

    @Column(name="nickname", length = 10)
    private String nickname;

    @Column(name="gender", length = 2)
    private String gender;

    @Column(name="kakao_id", length = 10)
    private String kakaoId;

    @Column(name="birth_date", length = 10)
    private String birthDate;
}
