package com.project.hae_dream.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class FoodApi {

    public JsonNode getFoodJson(String food) throws IOException, InterruptedException {
        String urlBuilder = "http://openapi.foodsafetykorea.go.kr/api/3efc0374fd47465dba7a/I2790/json/1/5" + "/DESC_KOR=" + food;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlBuilder))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readTree(response.body());
    }

    public List<FoodInfo> JsonToFoodInfo(JsonNode jsonData) throws IOException, InterruptedException {

        JsonNode i2790Node = jsonData.get("I2790");
        JsonNode rowNode = i2790Node.get("row");

        if (rowNode == null) {
            return null;
        }

        ArrayList<FoodInfo> results = new ArrayList<>();

        // {"NUTR_CONT8":"2.76","NUTR_CONT9":"0.03","NUTR_CONT4":"15.01","NUTR_CONT5":"3.03","NUTR_CONT6":"1962.14","NUM":"1","NUTR_CONT7":"25.18","NUTR_CONT1":"243.03","NUTR_CONT2":"11.93","SUB_REF_NAME":"식약처(\u002712) 제1권","NUTR_CONT3":"15.07","RESEARCH_YEAR":"2019","MAKER_NAME":"","GROUP_NAME":"","SERVING_SIZE":"400","SERVING_UNIT":"g","SAMPLING_REGION_NAME":"전국(대표)","SAMPLING_MONTH_CD":"AVG","SAMPLING_MONTH_NAME":"평균","DESC_KOR":"김치찌개","SAMPLING_REGION_CD":"ZZ","FOOD_CD":"D000385"}
        for (JsonNode foodNode : rowNode) {
            FoodInfo foodInfo = new FoodInfo();
            foodInfo.setFoodName(foodNode.get("DESC_KOR").asText());
            foodInfo.setNUTR_CONT1(foodNode.get("NUTR_CONT1").asDouble());
            foodInfo.setNUTR_CONT2(foodNode.get("NUTR_CONT2").asDouble());
            foodInfo.setNUTR_CONT3(foodNode.get("NUTR_CONT3").asDouble());
            foodInfo.setNUTR_CONT4(foodNode.get("NUTR_CONT4").asDouble());
            foodInfo.setNUTR_CONT5(foodNode.get("NUTR_CONT5").asDouble());
            foodInfo.setNUTR_CONT6(foodNode.get("NUTR_CONT6").asDouble());
            foodInfo.setNUTR_CONT7(foodNode.get("NUTR_CONT7").asDouble());
            foodInfo.setNUTR_CONT8(foodNode.get("NUTR_CONT8").asDouble());
            foodInfo.setNUTR_CONT9(foodNode.get("NUTR_CONT9").asDouble());

            results.add(foodInfo);
        }

        return results;
    }
}
