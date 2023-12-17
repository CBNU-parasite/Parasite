package com.project.hae_dream.service;

import com.project.hae_dream.dto.UserAccountDTO;
import com.project.hae_dream.entity.UserAccountEntity;
import com.project.hae_dream.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    public void save(UserAccountDTO userAccountDTO){
        UserAccountEntity userAccountEntity = UserAccountEntity.toUserAccountEntity(userAccountDTO);
        userAccountRepository.save(userAccountEntity);
    }
    public UserAccountDTO login(UserAccountDTO userAccountDTO){
        /*
             1. 회원이 입력한 아이디로 DB에서 조회를 함
             2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단.
         */
        Optional<UserAccountEntity> byUserId = userAccountRepository.findByUserId(userAccountDTO.getUserId()); // 받아옴
        if(byUserId.isPresent()){
            // 조회 결과가 있다.(해당 아이디를 가진 회원 정보가 있다)
            UserAccountEntity userAccountEntity = byUserId.get();
            if(userAccountEntity.getUserPassword().equals(userAccountDTO.getUserPassword())){
                // 비밀번호 일치.
                // entity -> dto 변환 후 리턴
                UserAccountDTO dto = UserAccountDTO.toUserAccountDTO(userAccountEntity);// 엔티티 하나를 dto 로 변환
                return dto;
            }else{
                // 비밀번호 불일치(로그인 실패)
                return null;
            }
        }
        else{
            // 조회 결과가 없다. (해당 아이디를 가진 회원이 없다)
            return null;
        }
    }
    public List<UserAccountDTO> findAll(){ // service 의 findAll 함수
        List<UserAccountEntity> userAccountEntityList = userAccountRepository.findAll();
        List<UserAccountDTO> userAccountDTOList = new ArrayList<>();
        for(UserAccountEntity userAccountEntity: userAccountEntityList){// 엔티티가 여러개 담긴 객체를 디티오가 여러개 담긴 객체로 변환.
            userAccountDTOList.add(UserAccountDTO.toUserAccountDTO(userAccountEntity));
//            UserAccountDTO userAccountDTO = UserAccountDTO.toUserAccountDTO(userAccountEntity);
//            userAccountDTOList.add(userAccountDTO);
        }
        return userAccountDTOList;
    }

    public HashMap<String, String> searchUser(String userId) {
        HashMap<String, String> results = new HashMap<>();

        Optional<UserAccountEntity> byUserId = userAccountRepository.findByUserId(userId);

        UserAccountEntity userAccountEntity = byUserId.get();

        String userName = userAccountEntity.getUserName();
        String userSex = userAccountEntity.getUserSex();
        String userTall = userAccountEntity.getUserTall();
        String userWeight = userAccountEntity.getUserWeight();

        results.put("userName", userName);
        results.put("userSex",userSex);
        results.put("userTall",userTall);
        results.put("userWeight",userWeight);

        return results;
    }

}
