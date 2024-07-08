package com.leeds.manito.manito_pj.dto;

public class UserInfoDTO {
    private String user_id;
    private String name;
    private String nickname;
    private String gender;
    private String kakao_id;
    private String birth_date;

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getKakao_id() {
        return this.kakao_id;
    }

    public void setKakao_id(String kakao_id) {
        this.kakao_id = kakao_id;
    }

    public String getBirth_date() {
        return this.birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

}
