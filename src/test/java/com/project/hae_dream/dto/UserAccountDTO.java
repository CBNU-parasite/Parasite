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
    private String userAge;
    private String userSex;

    public static UserAccountDTO toUserAccountDTO(UserAccountEntity userAccountEntity){
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setId(userAccountEntity.getId());
        userAccountDTO.setUserId(userAccountEntity.getUserId());
        userAccountDTO.setUserName(userAccountEntity.getUserName());
        userAccountDTO.setUserPassword(userAccountEntity.getUserPassword());
        userAccountDTO.setUserAge(userAccountEntity.getUserAge());
        userAccountDTO.setUserSex(userAccountEntity.getUserSex());
        return userAccountDTO;
    }
}
