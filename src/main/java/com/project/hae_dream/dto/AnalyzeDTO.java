package com.project.hae_dream.dto;

import com.project.hae_dream.entity.AnalyzeEntity;
import com.project.hae_dream.entity.UserAccountEntity;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AnalyzeDTO {
    private Long id;
    private UserAccountDTO userAccountDTO;
    private String foodName;
    private Double kcal;
    private Double carbohydrate;
    private Double protein;
    private Double fat;
    private Double sugars;
    private Double sodium;
    private Double cholesterol;
    private Double saturatedFat;
    private Double transFat;
    private LocalDateTime createdAt;

    public static AnalyzeDTO toAnalyzeDTO(AnalyzeEntity analyzeEntity){
        AnalyzeDTO analyzeDTO = new AnalyzeDTO();
        analyzeDTO.setId(analyzeEntity.getId());
        UserAccountDTO dto = UserAccountDTO.toUserAccountDTO(analyzeEntity.getUserAccountEntity());
        analyzeDTO.setUserAccountDTO(dto);
        analyzeDTO.setFoodName(analyzeEntity.getFoodName());
        analyzeDTO.setKcal(analyzeEntity.getKcal());
        analyzeDTO.setCarbohydrate(analyzeEntity.getCarbohydrate());
        analyzeDTO.setProtein(analyzeEntity.getProtein());
        analyzeDTO.setFat(analyzeEntity.getFat());
        analyzeDTO.setSugars(analyzeEntity.getSugars());
        analyzeDTO.setSodium(analyzeEntity.getSodium());
        analyzeDTO.setCholesterol(analyzeEntity.getCholesterol());
        analyzeDTO.setSaturatedFat(analyzeEntity.getSaturatedFat());
        analyzeDTO.setTransFat(analyzeEntity.getTransFat());
        analyzeDTO.setCreatedAt(analyzeEntity.getCreatedAt());

        return analyzeDTO;
    }
}
