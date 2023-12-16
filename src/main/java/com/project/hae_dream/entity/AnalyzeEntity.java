package com.project.hae_dream.entity;

import com.project.hae_dream.dto.AnalyzeDTO;
import com.project.hae_dream.dto.UserAccountDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name="user_analyze_info")
public class AnalyzeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(name = "user_id") private UserAccountEntity userAccountEntity;

    @Column private String foodName;

    @Column private Double kcal;

    @Column private Double carbohydrate;

    @Column private Double protein;

    @Column private Double fat;

    @Column private Double sugars;

    @Column private Double sodium;

    @Column private Double cholesterol;

    @Column private Double saturatedFat;

    @Column private Double transFat;

    @Column private LocalDateTime createdAt;

    public AnalyzeEntity() {
        this.createdAt = LocalDateTime.now();
    }

    public static AnalyzeEntity toAnalyzeEntity(AnalyzeDTO analyzeDTO){
        AnalyzeEntity analyzeEntity = new AnalyzeEntity();
        analyzeEntity.setId(analyzeDTO.getId());
        UserAccountEntity userAccountEntity = UserAccountEntity.toUserAccountEntity(analyzeDTO.getUserAccountDTO());
        analyzeEntity.setUserAccountEntity(userAccountEntity);
        analyzeEntity.setFoodName(analyzeDTO.getFoodName());
        analyzeEntity.setKcal(analyzeDTO.getKcal());
        analyzeEntity.setCarbohydrate(analyzeDTO.getCarbohydrate());
        analyzeEntity.setProtein(analyzeDTO.getProtein());
        analyzeEntity.setFat(analyzeDTO.getFat());
        analyzeEntity.setSugars(analyzeDTO.getSugars());
        analyzeEntity.setSodium(analyzeDTO.getSodium());
        analyzeEntity.setCholesterol(analyzeDTO.getCholesterol());
        analyzeEntity.setSaturatedFat(analyzeDTO.getSaturatedFat());
        analyzeEntity.setTransFat(analyzeDTO.getTransFat());
        analyzeEntity.setCreatedAt(analyzeDTO.getCreatedAt());

        return analyzeEntity;
    }
}
