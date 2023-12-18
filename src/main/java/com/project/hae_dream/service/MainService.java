package com.project.hae_dream.service;

import com.project.hae_dream.entity.UserAccountEntity;
import com.project.hae_dream.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class MainService {
    private final UserAccountRepository userAccountRepository;

    public HashMap<String, String> searchUser(String userId) {
        HashMap<String, String> results = new HashMap<>();

        Optional<UserAccountEntity> byUserId = userAccountRepository.findByUserId(userId);

        UserAccountEntity userAccountEntity = byUserId.get();

        String userName = userAccountEntity.getUserName();

        LocalDate date1 = userAccountEntity.getCreatedAt().toLocalDate();
        LocalDate date2 = LocalDate.now();

        long days = DAYS.between(date1, date2);
        days += 1;

        results.put("userName", userName);
        results.put("days", String.valueOf(days));

        return results;
    }
}
