package test.api;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.gson.Gson;
import data.ApiHelper;
import data.DataBaseHelper;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static data.DataBaseHelper.cleanDataBase;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayApiTest {
    private static DataHelper.CardInfo cardInfo;
    private static final Gson gson = new Gson();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    public void teardrop() {
        cleanDataBase();
    }


    //Отправка POST-запроса с валидными данными и номером карты со статусом "APPROVED".

    @Test
    void shouldRespondWithStatusCode200WithApprovedCard() {
        cardInfo = DataHelper.getValidDataWithApprovedCard();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 200, "/api/v1/pay");
        val actual = DataBaseHelper.getStatusPaymentRequest();
        assertEquals("APPROVED", actual);
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(1, countOrder);
    }

    //Отправка POST-запроса с валидными данными и номером карты со статусом "DECLINED".
    @Test
    void shouldRespondWithStatusCode400WithDeclinedCard() {
        cardInfo = DataHelper.getValidDataWithDeclinedCard();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 400, "/api/v1/pay");
        val actual = DataBaseHelper.getStatusPaymentRequest();
        assertEquals("DECLINED", actual);
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(0, countOrder);
    }


    //Негативные сценарии:
    //Отправка пустого POST-запроса.
    @Test
    void shouldRespondWithStatus400IfAllFieldAreEmpty() {
        cardInfo = DataHelper.getCardInfoWithEmptyFields();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 400, "/api/v1/pay");
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(0, countOrder);
    }

    //Отправка POST-запроса с валидными данными без номера карты.
    @Test
    void shouldRespondWithStatus400IfFieldNumberIsEmpty() {
        cardInfo = DataHelper.getCardInfoWithEmptyNumber();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 400, "/api/v1/pay");
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(0, countOrder);
    }

    //Отправка POST-запроса с валидными данными без указания месяца.
    @Test
    void shouldRespondWithStatus400IfFieldMonthIsEmpty() {
        cardInfo = DataHelper.getCardInfoWithNullMonth();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 400, "/api/v1/pay");
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(0, countOrder);
    }

    //Отправка POST- запроса с валидными данными без указания года.
    @Test
    void shouldRespondWithStatus400IfFieldYearIsEmpty() {
        cardInfo = DataHelper.getCardInfoWithNullYear();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 400, "/api/v1/pay");
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(0, countOrder);
    }

    //Отправка POST- запроса с валидными данными без указания владельца карты.
    @Test
    void shouldRespondWithStatus400IfFieldOwnerIsEmpty() {
        cardInfo = DataHelper.getCardInfoWithNullOwner();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 400, "/api/v1/pay");
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(0, countOrder);
    }

    //Отправка POST- запроса с валидными данными без указания CVC/CVV.
    @Test
    void shouldRespondWithStatus400IfFieldCVCIsEmpty() {
        cardInfo = DataHelper.getCardInfoWithEmptyCVC();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 400, "/api/v1/pay");
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(0, countOrder);
    }
}
