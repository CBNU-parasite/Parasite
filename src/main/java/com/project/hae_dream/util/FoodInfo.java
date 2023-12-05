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
}
