package com.project.hae_dream;

import org.assertj.core.api.Assertions;
import org.example.CorrectFixedPasswordGenerator;
import com.project.hae_dream.garbage.PasswordGenerator;
import org.example.WrongFixedPasswordGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @DisplayName("패스워드 초기화한다.")
    @Test
    void passwordTest() {
        // given
        User user = new User();

        // when
        user.initPassword((PasswordGenerator) new CorrectFixedPasswordGenerator());

        // then
        Assertions.assertThat(user.getPassword()).isNotNull();
    }

    @DisplayName("패스워드가 요구사항에 부합되지 않아 초괴화가 되지 않는다.")
    @Test
    void passwordTest2() {
        // given
        User user = new User();

        // when
        user.initPassword((PasswordGenerator) new WrongFixedPasswordGenerator());

        // then
        Assertions.assertThat(user.getPassword()).isNull();
    }
}