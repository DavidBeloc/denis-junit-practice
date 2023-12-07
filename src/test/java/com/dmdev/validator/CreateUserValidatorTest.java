package com.dmdev.validator;

import com.dmdev.dto.CreateUserDto;
import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateUserValidatorTest {

    private final CreateUserValidator validator = CreateUserValidator.getInstance();

    @Test
    void shouldPassValidation() {
        CreateUserDto dto = CreateUserDto.builder()
                .birthday("2020-01-01")
                .email("123@gmail.com")
                .gender(Gender.MALE.name())
                .name("Ivan")
                .password("123")
                .role(Role.USER.name())
                .build();

        ValidationResult actualResult = validator.validate(dto);

        assertTrue(actualResult.isValid());
    }

    @Test
    void invalidBirthday() {
        CreateUserDto dto = CreateUserDto.builder()
                .birthday("2020-01-01 12:31")
                .email("123@gmail.com")
                .gender(Gender.MALE.name())
                .name("Ivan")
                .password("123")
                .role(Role.USER.name())
                .build();

        ValidationResult actualResult = validator.validate(dto);

        assertThat(actualResult.getErrors().get(0).getCode()).isEqualTo("invalid.birthday");
    }

    @Test
    void invalidGender() {
        CreateUserDto dto = CreateUserDto.builder()
                .birthday("2020-01-01")
                .email("123@gmail.com")
                .gender("fake")
                .name("Ivan")
                .password("123")
                .role(Role.USER.name())
                .build();

        ValidationResult actualResult = validator.validate(dto);

        assertThat(actualResult.getErrors()).hasSize(1);
        assertThat(actualResult.getErrors().get(0).getCode()).isEqualTo("invalid.gender");
    }

    @Test
    void invalidRole() {
        CreateUserDto dto = CreateUserDto.builder()
                .birthday("2020-01-01")
                .email("123@gmail.com")
                .gender(Gender.MALE.name())
                .name("Ivan")
                .password("123")
                .role("fake")
                .build();

        ValidationResult actualResult = validator.validate(dto);

        assertThat(actualResult.getErrors()).hasSize(1);
        assertThat(actualResult.getErrors().get(0).getCode()).isEqualTo("invalid.role");
    }

    @Test
    void invalidRoleGenderBirthday() {
        CreateUserDto dto = CreateUserDto.builder()
                .birthday("2020-01-01 12:31")
                .email("123@gmail.com")
                .gender("fake")
                .name("Ivan")
                .password("123")
                .role("fake")
                .build();

        ValidationResult actualResult = validator.validate(dto);

        assertThat(actualResult.getErrors()).hasSize(3);
        List<String> listCodes = actualResult.getErrors().stream()
                .map(Error::getCode)
                .toList();
        assertThat(listCodes).contains("invalid.role", "invalid.gender", "invalid.birthday");
    }
}