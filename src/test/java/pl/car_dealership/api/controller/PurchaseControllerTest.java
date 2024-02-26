package pl.car_dealership.api.controller;

import lombok.AllArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import pl.car_dealership.api.dto.CarPurchaseDTO;
import pl.car_dealership.api.dto.mapper.CarMapper;
import pl.car_dealership.api.dto.mapper.CarPurchaseMapper;
import pl.car_dealership.business.CarPurchaseService;
import pl.car_dealership.domain.Invoice;

import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = PurchaseController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PurchaseControllerTest {

    private MockMvc mockMvc;

    @MockBean
    CarPurchaseService carPurchaseService;
    @MockBean
    CarPurchaseMapper carPurchaseMapper;
    @MockBean
    CarMapper carMapper;

    @Test
    void carPurchaseWorksCorrectly() throws Exception {
        // given
        LinkedMultiValueMap<String, String> parameteres = new LinkedMultiValueMap<>();
        CarPurchaseDTO.buildDefaultData().asMap().forEach(parameteres::add);

        Invoice expectedInvoice = Invoice.builder().invoiceNumber("test").build();
        when(carPurchaseService.purchase(Mockito.any())).thenReturn(expectedInvoice);;
        // when, then
        mockMvc.perform(post(PurchaseController.PURCHASE).params(parameteres))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("invoiceNumber"))
                .andExpect(model().attributeExists("customerName"))
                .andExpect(model().attributeExists("customerSurname"))
                .andExpect(view().name("car_purchase_done"));
    }

    @Test
    void thatEmailValidationWorksCorrectly() throws Exception {
        // given
        String badEmail = "badEmail";
        LinkedMultiValueMap<String, String> parameteres = new LinkedMultiValueMap<>();
        Map<String, String> parameteresMap = CarPurchaseDTO.buildDefaultData().asMap();
        parameteresMap.put("customerEmail", badEmail);
        parameteresMap.forEach(parameteres::add);

        // when
        // then
        mockMvc.perform(post(PurchaseController.PURCHASE).params(parameteres))
                .andExpect(status().isBadRequest())
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", Matchers.containsString(badEmail)))
                .andExpect(view().name("error"));
    }

    @ParameterizedTest
    @MethodSource
    void thatPhoneValidationWorksCorrectly(Boolean correctPhone, String phone) throws Exception {
        // given
        LinkedMultiValueMap<String, String> parameteres = new LinkedMultiValueMap<>();
        Map<String, String> parameteresMap = CarPurchaseDTO.buildDefaultData().asMap();
        parameteresMap.put("customerPhone", phone);
        parameteresMap.forEach(parameteres::add);

        // when, then
        if (correctPhone) {
            Invoice expectedInvoice = Invoice.builder().invoiceNumber("test").build();
            when(carPurchaseService.purchase(Mockito.any())).thenReturn(expectedInvoice);;
            mockMvc.perform(post(PurchaseController.PURCHASE).params(parameteres))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("invoiceNumber"))
                    .andExpect(model().attributeExists("customerName"))
                    .andExpect(model().attributeExists("customerSurname"))
                    .andExpect(view().name("car_purchase_done"));
        } else {
            mockMvc.perform(post(PurchaseController.PURCHASE).params(parameteres))
                    .andExpect(status().isBadRequest())
                    .andExpect(model().attributeExists("errorMessage"))
                    .andExpect(model().attribute("errorMessage", Matchers.containsString(phone)))
                    .andExpect(view().name("error"));
        }
    }

    public static Stream<Arguments> thatPhoneValidationWorksCorrectly() {
        return Stream.of(
                Arguments.of(false, "12345678"),
                Arguments.of(false, "1234567890"),
                Arguments.of(false, " "),
                Arguments.of(false, ""),
                Arguments.of(false, "()"),
                Arguments.of(false, "+"),
                Arguments.of(false, "() + ()"),
                Arguments.of(false, "1"),
                Arguments.of(false, " 1"),
                Arguments.of(false, "48 (12) 504 203 260"),
                Arguments.of(false, "48 (12) 504-203-260"),
                Arguments.of(false, "48(12)504203260"),
                Arguments.of(false, "+ 48 504 203 260"),
                Arguments.of(true, "+48 504 203 260")
        );
    }

}
