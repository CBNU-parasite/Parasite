package com.project.hae_dream.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class foodApi {
    public static void main(String[] args) throws IOException {
        // 1. URL을 만들기 위한 StringBuilder.
        StringBuilder urlBuilder = new StringBuilder("http://openapi.foodsafetykorea.go.kr/api/3efc0374fd47465dba7a/I2790/json/1/5"); /*URL*/
        // 2. 오픈 API의요청 규격에 맞는 파라미터 생성, 발급받은 인증키.
        urlBuilder.append("/DESC_KOR=" + "김치찌개"); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
        // 3. URL 객체 생성.
        URL url = new URL(urlBuilder.toString());
        // 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성.
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 5. 통신을 위한 메소드 SET.
        conn.setRequestMethod("GET");
        // 6. 통신을 위한 Content-type SET.
        conn.setRequestProperty("Content-type", "application/json");
        // 7. 통신 응답 코드 확인.
        System.out.println("Response code: " + conn.getResponseCode());
        // 8. 전달받은 데이터를 BufferedReader 객체로 저장.
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        // 10. 객체 해제.
        rd.close();
        conn.disconnect();
        // 11. 전달받은 데이터 확인.
        System.out.println(sb.toString());

        // Json 파일 데이터 읽기
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(sb.toString());

        JsonNode i2790Node = rootNode.get("I2790");

        JsonNode rowNode = i2790Node.get("row");

        for (JsonNode foodNode : rowNode) {
            // 각 음식에 대한 처리
            String foodName = foodNode.get("DESC_KOR").asText();
            String servingSize = foodNode.get("SERVING_SIZE").asText();
            // 필요한 정보를 가져와서 사용
            System.out.println("음식 이름: " + foodName + ", 서빙 사이즈: " + servingSize);
            // {"NUTR_CONT8":"2.76","NUTR_CONT9":"0.03","NUTR_CONT4":"15.01","NUTR_CONT5":"3.03","NUTR_CONT6":"1962.14","NUM":"1","NUTR_CONT7":"25.18","NUTR_CONT1":"243.03","NUTR_CONT2":"11.93","SUB_REF_NAME":"식약처(\u002712) 제1권","NUTR_CONT3":"15.07","RESEARCH_YEAR":"2019","MAKER_NAME":"","GROUP_NAME":"","SERVING_SIZE":"400","SERVING_UNIT":"g","SAMPLING_REGION_NAME":"전국(대표)","SAMPLING_MONTH_CD":"AVG","SAMPLING_MONTH_NAME":"평균","DESC_KOR":"김치찌개","SAMPLING_REGION_CD":"ZZ","FOOD_CD":"D000385"}
        }
    }
}
