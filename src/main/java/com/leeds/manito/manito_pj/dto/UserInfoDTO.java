package com.leeds.manito.manito_pj.dto;

public class UserInfoDTO {
    private String userId;
    private String name;
    private int manitoIdx;
    private String manitoTarget;
    private String nickname;
    private String gender;
    private String kakaoId;
    private String birthDate;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManitoIdx() {
        return this.manitoIdx;
    }

    public void setManitoIdx(int manitoIdx) {
        this.manitoIdx = manitoIdx;
    }

    public String getManitoTarget() {
        return this.manitoTarget;
    }

    public void setManito(String manitoTarget) {
        this.manitoTarget = manitoTarget;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getKakaoId() {
        return this.kakaoId;
    }

    public void setKakaoId(String kakaoId) {
        this.kakaoId = kakaoId;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
