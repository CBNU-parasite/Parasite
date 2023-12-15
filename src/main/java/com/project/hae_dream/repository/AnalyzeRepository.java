package com.project.hae_dream.repository;

import com.project.hae_dream.entity.AnalyzeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface AnalyzeRepository extends JpaRepository<AnalyzeEntity, Long> {
    List<AnalyzeEntity> findByUserAccountEntity_IdAndCreatedAtBefore(Long userId, LocalDateTime createdAt);

    List<AnalyzeEntity> findByUserAccountEntity_Id(Long getUserId);

    void deleteByFoodName(String foodName);
}
