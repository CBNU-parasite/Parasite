package com.project.hae_dream.service;

import com.project.hae_dream.dto.AnalyzeDTO;
import com.project.hae_dream.entity.AnalyzeEntity;
import com.project.hae_dream.entity.UserAccountEntity;
import com.project.hae_dream.repository.AnalyzeRepository;
import com.project.hae_dream.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class AnalyzeService {
    private final AnalyzeRepository analyzeRepository;
    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public List<AnalyzeDTO> findFoodLogsByUserNameAndToday(String userId){
        Optional<UserAccountEntity> userAccountEntity = userAccountRepository.findByUserName(userId);
        if (userAccountEntity.isPresent()) {
            UserAccountEntity user = userAccountEntity.get();
            Long getUserId = user.getId();
            LocalDateTime today = LocalDateTime.now();
            return  analyzeRepository.findByUserAccountEntity_IdAndCreatedAtBefore(getUserId,today)
                    .stream()
                    .map(AnalyzeDTO::toAnalyzeDTO)
                    .collect(Collectors.toList());
        }

        return null;
    }

    public void saveFood(AnalyzeDTO dto){
        AnalyzeEntity analyzeEntity = AnalyzeEntity.toAnalyzeEntity(dto);
        analyzeRepository.save(analyzeEntity);
    }

}
