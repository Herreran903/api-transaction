package com.api_transaction.transaction.infra.supply.in;

import com.api_transaction.transaction.app.supply.dto.SupplyRequest;
import com.api_transaction.transaction.app.supply.handler.ISupplyHandler;
import com.api_transaction.transaction.domain.supply.exception.SupplyExceptionMessage;
import com.api_transaction.transaction.domain.util.GlobalExceptionMessage;
import com.api_transaction.transaction.infra.security.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.api_transaction.transaction.domain.util.GlobalConstants.HEADER_STRING;
import static com.api_transaction.transaction.utils.TestConstants.VALID_TOKEN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SupplyController.class)
@AutoConfigureMockMvc(addFilters=false)
class SupplyControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private ISupplyHandler supplyHandler;

    @Test
    void shouldReturnCreatedWhenSupplyIsSuccessfullyCreated() throws Exception {

        String requestBody = "{\"product\":\"1\", \"amount\":\"10\"}";

        mvc.perform(post("/supply/add")
                        .header(HEADER_STRING, VALID_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk());

        verify(supplyHandler, times(1)).createSupply(any(SupplyRequest.class),anyString());
    }

    @Test
    void shouldReturnBadRequestIfJsonNoValid() throws Exception {
        String expectedMessage = GlobalExceptionMessage.INVALID_JSON;

        mvc.perform(post("/supply/add")
                        .header(HEADER_STRING, VALID_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"product\":,}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(expectedMessage));
    }

    @Test
    void shouldReturnBadRequestForNullProductId() throws Exception{
        String expectedMessage = GlobalExceptionMessage.INVALID_OBJECT;
        String expectedErrorMessage = SupplyExceptionMessage.EMPTY_PRODUCT;

        String requestBody = "{\"amount\":\"10\"}";

        mvc.perform(post("/supply/add")
                        .header(HEADER_STRING, VALID_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(expectedMessage))
                .andExpect(jsonPath("$.errors[0].field").value("product"))
                .andExpect(jsonPath("$.errors[0].message").value(expectedErrorMessage));
    }

    @Test
    void shouldReturnBadRequestForInvalidAmount() throws Exception{
        String expectedMessage = GlobalExceptionMessage.INVALID_OBJECT;
        String expectedErrorMessage = SupplyExceptionMessage.MIN_AMOUNT;

        String requestBody = "{\"product\":\"1\", \"amount\":\"0\"}";

        mvc.perform(post("/supply/add")
                        .header(HEADER_STRING, VALID_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(expectedMessage))
                .andExpect(jsonPath("$.errors[0].field").value("amount"))
                .andExpect(jsonPath("$.errors[0].message").value(expectedErrorMessage));
    }

}