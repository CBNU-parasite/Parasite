package com.project.hae_dream.entity;

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
@Table(name = "user_account_table")
public class UserAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) private String userId;

    @Column private String userPassword;

    @Column private String userName;

    @Column private String userSex;

    @Column private String userWeight;

    @Column private String userTall;

    @Column private LocalDateTime createdAt;

    public UserAccountEntity() {
        this.createdAt = LocalDateTime.now();
    }


    public static UserAccountEntity toUserAccountEntity(UserAccountDTO userAccountDTO){
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setId(userAccountDTO.getId());
        userAccountEntity.setUserId(userAccountDTO.getUserId());
        userAccountEntity.setUserPassword(userAccountDTO.getUserPassword());
        userAccountEntity.setUserSex(userAccountDTO.getUserSex());
        userAccountEntity.setUserName(userAccountDTO.getUserName());
        userAccountEntity.setUserTall(userAccountDTO.getUserTall());
        userAccountEntity.setUserWeight(userAccountDTO.getUserWeight());
        userAccountEntity.setCreatedAt(userAccountDTO.getCreatedAt());

        return userAccountEntity;
    }

}
