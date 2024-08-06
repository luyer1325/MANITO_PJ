package com.leeds.manito.manito_pj.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.leeds.manito.manito_pj.dto.UserInfoDTO;

import jakarta.servlet.http.HttpSession;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;

@Service
public class KakaoService {

    @Value("${kakao_api_key}")
    private String apiKey;

    @Value("${kakao_redirect_uri}")
    private String redirectUri;
    
    @Value("${kakao_js_key}")
    private String jsKey;

    public void getSettings(Model model){
        model.addAttribute("kakao_api_key", apiKey);
        model.addAttribute("kakao_redirect_uri", redirectUri);
        model.addAttribute("kakao_js_key",jsKey);
        System.out.println(apiKey);
    }
    
    public String getAccessToken(String code){
        String accessToken = "";
        String reqUrl = "https://kauth.kakao.com/oauth/token";
        try{
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 카카오에서의 요청 데이터
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=").append(apiKey);
            sb.append("&redirect_uri=").append(redirectUri);
            sb.append("&code=").append(code);

            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            System.out.println("[KakaoApi.getAccessToken] responseCode = "+ responseCode);

            BufferedReader br;
            if (responseCode >= 200 && responseCode < 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line = "";
            StringBuilder responseSb = new StringBuilder();
            while((line = br.readLine()) != null){
                responseSb.append(line);
            }
            String result = responseSb.toString();
            System.out.println("responseBody = "+ result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            accessToken = element.getAsJsonObject().get("access_token").getAsString();
            //refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
            //model.addAttribute("at",accessToken);
            //model.addAttribute("rUrl","/thym-invite2.do");

            br.close();
            bw.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return accessToken;
    }

    public UserInfoDTO getUserInfo(HttpSession session,Model model,String accessToken,UserInfoDTO userInfoDTO){
        String reqUrl = "https://kapi.kakao.com/v2/user/me";
        try{
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setDoOutput(true);

            int responseCode = conn.getResponseCode();
            System.out.println("[KakaoApi.getUserInfo] responseCode : "+  responseCode);

            BufferedReader br;
            if (responseCode >= 200 && responseCode <= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line = "";
            StringBuilder responseSb = new StringBuilder();
            while((line = br.readLine()) != null){
                responseSb.append(line);
            }
            String result = responseSb.toString();
            System.out.println("responseBody ="+ result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
            String id = element.getAsJsonObject().get("id").getAsString();

            userInfoDTO.setNickname(nickname);
            userInfoDTO.setUserId(email);
            userInfoDTO.setKakaoId(id);
            model.addAttribute("at", accessToken);
            session.setAttribute("email", email);
            session.setAttribute("at", accessToken);
            session.setAttribute("kakaoId", id);
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return userInfoDTO;
    }

    public void kakaoLogout(Model model){
        String accessToken = (String)model.getAttribute("at");
        String rUrl = "https://kapi.kakao.com/v1/user/logout";
        try{
            URL url = new URL(rUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 카카오에서의 요청 데이터
            conn.setRequestProperty("Authorization", "Bearer "+accessToken);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            int responseCode = conn.getResponseCode();
            System.out.println("[kakaoApi.kakaologout]responseCode = "+responseCode);

            BufferedReader br;
            if(responseCode>=200 && responseCode<=300){
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }else{
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line = "";
            StringBuilder responseSB = new StringBuilder();

            while((line = br.readLine()) != null){
                responseSB.append(line);
            }
            String result = responseSB.toString();
            System.out.println("[kakao.kakaoLougout] responseBody = "+ result);

            //model.addAttribute("rUrl","/invite.do");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
