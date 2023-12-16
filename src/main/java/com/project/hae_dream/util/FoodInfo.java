package com.project.hae_dream.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodInfo {
    private String foodName;
    // 열량
    private double NUTR_CONT1;
    // 탄수화물
    private double NUTR_CONT2;
    // 단백질
    private double NUTR_CONT3;
    // 지방
    private double NUTR_CONT4;
    // 당류
    private double NUTR_CONT5;
    // 나트륨
    private double NUTR_CONT6;
    // 콜레스테롤
    private double NUTR_CONT7;
    // 포화지방
    private double NUTR_CONT8;
    // 트랜스지방
    private double NUTR_CONT9;
}
