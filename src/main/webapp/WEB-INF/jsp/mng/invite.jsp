<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <title>마니또 멤버 초대</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!--제이쿼리 임포트-->
    <script type="text/javascript">
        $(document).ready(function(){
            $('#mission_time').hide();
            console.log("jQuery 시작");
            $('#confirm').click(function(){ // 버튼 클릭시 카카오 API 연결하여 친구에게 메세지 보내기
                $("#form").attr("action", "/ljh2.do").submit();
            })
            $('input[name="mission_yn"]').change(function(){ // 미션사용여부에 따라 미션 주기 설정 보여주는 기능
                var m_val = $('input[name="mission_yn"]:checked').val();
                alert(m_val);
                if(m_val=="Y"){
                    $('#mission_time').show();
                }else if(m_val=="N"){
                    $('#mission_time').hide();
                }
            })
        });
        
    </script>
</head>
<!--
- 인원 공개여부 설정
- 관리자 참가여부 설정
- 미션사용여부 설정
- 마니또 종료시점 설정
- 미션주기 설정
- 마니또 인원 초대하기
-->
<body>
    <div>
        
    <form:form modelAttribute="roomInfoDTO" method="get">
    <table>
        <tbody>
        <div>
        <tr>
            <th>인원 공개여부 설정</th>
            <td>
                <form:radiobutton path="show_yn" value="Y" label="Yes"/>
                <form:radiobutton path="show_yn" value="N" label="No"/>
            </td>
        </tr>
        <tr>
            <th>관리자 참가여부 설정</th>
            <td>
                <form:radiobutton path="join_yn" value="Y" label="Yes"/>
                <form:radiobutton path="join_yn" value="N" label="No"/>
            </td>
        </tr>
        <tr>
            <th>미션사용여부 설정</th>
            <td>
                <form:radiobutton path="mission_yn" value="Y" label="Yes"/>
                <form:radiobutton path="mission_yn" value="N" label="No"/>
            </td>
        </tr>
        <tr id="mission_time">
            <th>미션주기 설정</th>
            <td><form:input path="mission_time"/>시간</td>
        </tr>
        <tr>
            <th>마니또 종료시점 설정</th>
            <td>
                달력 구현<!-- date.format 입력 -->
            </td>
        </tr>
        
        <tr>
            <button id="confirm">확인</button>
        </tr>
        </div>   
        </tbody> 
    </table>
    </form:form>
    <div>
</body>
</html>