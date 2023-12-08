package com.dmdev.mapper;

import com.dmdev.dto.CreateUserDto;
import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CreateUserMapperTest {

    private final CreateUserMapper mapper = CreateUserMapper.getInstance();

    @Test
    void map() {
        CreateUserDto dto = CreateUserDto.builder()
                .birthday("2020-01-01")
                .email("123@gmail.com")
                .gender(Gender.MALE.name())
                .name("Ivan")
                .password("123")
                .role(Role.USER.name())
                .build();

        User actualResult = mapper.map(dto);

        User expectedResult = User.builder()
                .birthday(LocalDate.of(2020, 1, 1))
                .email("123@gmail.com")
                .gender(Gender.MALE)
                .name("Ivan")
                .password("123")
                .role(Role.USER)
                .build();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}