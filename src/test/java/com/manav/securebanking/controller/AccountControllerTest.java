package com.manav.securebanking.controller;

import com.manav.securebanking.service.AccountService;
import com.manav.securebanking.dto.AccountCreateRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.manav.securebanking.service.JwtService;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @MockitoBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtService jwtService;

    @Test
    void shouldCreateAccount() throws Exception {

        String requestBody = """
                {
                    "name": "Manav",
                    "accountType": "SAVINGS",
                    "customerId": 1
                }
                """;

        when(accountService.createAccount(any(AccountCreateRequest.class)))
                .thenReturn("Account created successfully");

        mockMvc.perform(
                post("/api/accounts")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(status().isCreated());
    }

    @Test
    void shouldRejectAccountWithName() throws Exception{

        String requestBody = """
            {
                "name": "A",
                "accountType": "SAVINGS",
                "customerId": 1
            }
            """;

        mockMvc.perform(
                post("/api/accounts")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(status().isBadRequest());
    }


    @Test
    void shouldRejectAccountWithShortName() throws Exception {
        String requestBody = """
            {
                "name": "A",
                "accountType": "SAVINGS",
                "customerId": 1
            }
            """;

        mockMvc.perform(
                post("/api/accounts")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(status().isBadRequest());
    }


    @Test
    void shouldRejectAccountWithBlankName() throws Exception {
        String requestBody = """
            {
                "name": "   ",
                "accountType": "SAVINGS",
                "customerId": 1
            }
            """;

        mockMvc.perform(
                post("/api/accounts")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(status().isBadRequest());
    }


    @Test
    void shouldRejectAccountWithBlankAccountType() throws Exception {
        String requestBody = """
            {
                "name": "Manav",
                "accountType": "   ",
                "customerId": 1
            }
            """;

        mockMvc.perform(
                post("/api/accounts")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(status().isBadRequest());
    }



}
