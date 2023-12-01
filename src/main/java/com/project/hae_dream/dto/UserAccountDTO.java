package com.project.hae_dream.dto;

import com.project.hae_dream.entity.UserAccountEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class UserAccountDTO {
    private Long id;
    private String userPassword;
    private String userName;
    private String userId;
    private String userSex;
    private String userWeight;
    private String userTall;


    public static UserAccountDTO toUserAccountDTO(UserAccountEntity userAccountEntity){
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setId(userAccountEntity.getId());
        userAccountDTO.setUserId(userAccountEntity.getUserId());
        userAccountDTO.setUserName(userAccountEntity.getUserName());
        userAccountDTO.setUserPassword(userAccountEntity.getUserPassword());
        userAccountDTO.setUserSex(userAccountEntity.getUserSex());
        userAccountDTO.setUserTall(userAccountEntity.getUserTall());
        userAccountDTO.setUserWeight(userAccountEntity.getUserWeight());
        return userAccountDTO;
    }
}
