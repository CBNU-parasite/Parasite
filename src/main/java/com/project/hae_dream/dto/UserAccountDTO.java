package com.project.hae_dream.dto;

import com.project.hae_dream.entity.UserAccountEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserAccountDTO {
    private Long id;
    private String userPassword;
    private String userName;
    private String userId;
    private String userSex;
    private String userWeight;
    private String userTall;
    private LocalDateTime createdAt;

    public UserAccountDTO() {
        this.createdAt = LocalDateTime.now();
    }

    public static UserAccountDTO toUserAccountDTO(UserAccountEntity userAccountEntity){
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setId(userAccountEntity.getId());
        userAccountDTO.setUserId(userAccountEntity.getUserId());
        userAccountDTO.setUserName(userAccountEntity.getUserName());
        userAccountDTO.setUserPassword(userAccountEntity.getUserPassword());
        userAccountDTO.setUserSex(userAccountEntity.getUserSex());
        userAccountDTO.setUserTall(userAccountEntity.getUserTall());
        userAccountDTO.setUserWeight(userAccountEntity.getUserWeight());
        userAccountDTO.setCreatedAt(userAccountEntity.getCreatedAt());

        return userAccountDTO;
    }
}
