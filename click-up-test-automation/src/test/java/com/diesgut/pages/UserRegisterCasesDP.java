package com.diesgut.pages;

import com.diesgut.domain.UserRegister;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.InputStream;
import java.util.List;

public class UserRegisterCasesDP {

    @DataProvider(name = "userRegisterInvalidData")
    public Object[][] getUserRegisterData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        InputStream is = getClass().getClassLoader().getResourceAsStream("user_register_invalid_cases.json");
        List<UserRegister> cases = mapper.readValue(is, new TypeReference<List<UserRegister>>() {});

        Object[][] data = new Object[cases.size()][1];
        for (int i = 0; i < cases.size(); i++) {
            data[i][0] = cases.get(i);
        }

        return data;
    }

}
