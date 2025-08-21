package com.diesgut.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegister {
    private String testId;

    private String fullName;
    private String professionalEmail;
    private String professionalEmailToConfirm;
    private String password;
    private String passwordToConfirm;

    private String expectedFullNameError;
    private String expectedEmailError;
    private String expectedPasswordError;
    private String expectedEmailConfirmError;
    private String expectedPasswordConfirmError;

    private boolean shouldSucceed;
    private String testCaseDescription;
}
