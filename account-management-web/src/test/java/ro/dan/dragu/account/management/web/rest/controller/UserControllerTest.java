package ro.dan.dragu.account.management.web.rest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;
import ro.dan.dragu.account.management.dal.entity.AccountRole;
import ro.dan.dragu.account.management.service.TransactionService;
import ro.dan.dragu.account.management.service.UserService;
import ro.dan.dragu.account.management.service.model.TransactionData;
import ro.dan.dragu.account.management.service.model.User;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private TransactionService transactionService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "testUser")
    public void testListTransactions() throws Exception {
        List<TransactionData> transactionData = new ArrayList<>();
        when(transactionService.getForLastTimeFrame("testUser", 1, ChronoUnit.DAYS)).thenReturn(transactionData);

        mockMvc.perform(get("/account/management/user/listTransactions").param("timeFrame", "1")
                .param("timeUnit", "DAYS"))
                .andExpect(status().isOk());

        verify(transactionService, times(1)).getForLastTimeFrame("testUser", 1, ChronoUnit.DAYS);

    }

    @Test
    @WithMockUser(username = "testUser")
    public void testListTransactionsInvalidTimeUnit() throws Exception {

        mockMvc.perform(get("/account/management/user/listTransactions").param("timeFrame", "1")
                .param("timeUnit", "MINUTES"))
                .andExpect(status().isBadRequest());


        verify(transactionService, times(0)).getForLastTimeFrame(any(), any(), any());

    }

    @Test
    public void testCreateAccount() throws Exception {
        mockMvc.perform(post("/account/management/user/create").content("{\"name\":  \"user\", \"fullName\" : \"User\", \"email\" : \"user@testmail.com\", \"password\" : \"password\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).create(userArgumentCaptor.capture());
        assertEquals("user", userArgumentCaptor.getValue()
                .getName());
        assertEquals("User", userArgumentCaptor.getValue()
                .getFullName());
        assertEquals("user@testmail.com", userArgumentCaptor.getValue()
                .getEmail());
        assertNotNull(userArgumentCaptor.getValue()
                .getPassword());
        assertEquals(AccountRole.ROLE_USER, userArgumentCaptor.getValue()
                .getRole());
        assertFalse(userArgumentCaptor.getValue()
                .getDisabled());

    }

    @Test
    public void testCreateAccountInvalidEmail() throws Exception {

        mockMvc.perform(post("/account/management/user/create").content("{\"name\":  \"user\", \"fullName\" : \"User\", \"email\" : \"testmail\", \"password\" : \"password\" }")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

        verify(userService, times(0)).create(any());

    }

    @Test
    @WithMockUser(username = "testUser")
    public void testDeleteAccount() throws Exception {
        mockMvc.perform(delete("/account/management/user/delete"))
                .andExpect(status().isOk());

        verify(userService, times(1)).disable(eq("testUser"));

    }
}
