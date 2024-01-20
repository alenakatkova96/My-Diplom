package test.ui;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataBaseHelper;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.DashboardPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayTest {
    DashboardPage page = open("http://localhost:8080/", DashboardPage.class);


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }


    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Card with APPROVED status")
    void shouldSuccessPaymentByCard() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getValidDataWithApprovedCard();
        paymentPage.inputData(cardInfo);
        paymentPage.getSuccessNotification();
        var actual = DataBaseHelper.getStatusPaymentRequest();
        assertEquals("APPROVED", actual);
    }

    @Test
    @DisplayName("Card with DECLINED status")
    void shouldDeclinePaymentByCard() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getValidDataWithDeclinedCard();
        paymentPage.inputData(cardInfo);
        paymentPage.getErrorNotification();
        var actual = DataBaseHelper.getStatusPaymentRequest();
        assertEquals("DECLINED", actual);
    }

    @Test
    @DisplayName("Card with empty fields")
    void shouldFailValidationCardWithEmptyFields() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyFields();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingEmptyField();
    }

    @Test
    @DisplayName("Card with empty number")
    void shouldFailValidationWithEmptyCardNumber() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyNumber();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with random number")
    void shouldDeclineTransactionCardWithRandomNumber() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithRandomNumber();
        paymentPage.inputData(cardInfo);
        paymentPage.getErrorNotification();
    }

    @Test
    @DisplayName("Card with 15 numbers")
    void shouldFailValidationWithCard15Numbers() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWith15();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with empty month")
    void shouldFailValidationCardWithEmptyMonth() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullMonth();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with two zero in month")
    void shouldFailValidationCardWithZeroInMonth() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithMonthWithTwoZero();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongDateFormat();
    }

    @Test
    @DisplayName("Card with one zero in month")
    void shouldFailValidationCardWith1NumberMonth() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithMonthWithZero();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with month number 13")
    void shouldFailValidationWithMonthAbove12() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWith13Month();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongDateFormat();
    }

    @Test
    @DisplayName("Card with empty year")
    void shouldFailValidationCardWithEmptyYear() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullYear();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with last year")
    void shouldFailValidationCardWithLastYear() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithLastYear();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingCardEnded();
    }

    @Test
    @DisplayName("Card with year with one number")
    void shouldFailValidationCardWith1NumberYear() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWith1NumberYear();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with empty owner")
    void shouldFailValidationCardWithEmptyOwner() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullOwner();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingEmptyField();
    }

    @Test
    @DisplayName("Card with owner with name with numbers")
    void shouldFailValidationCardWithOwnerWithNumbers() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerWithNumbers();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with owner with name with cyrillic")
    void shouldFailValidationCardWithNameWithCyrillic() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerCyrillic();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }


    @Test
    @DisplayName("Card with owner with name with special characters")
    void shouldFailValidationCardWithNameSpecialCharacters() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerSpecialCharacters();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with empty CVC")
    void shouldFailValidationCardWithEmptyCVC() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyCVC();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingEmptyField();
    }


    @Test
    @DisplayName("Card with 2 numbers in CVC")
    void shouldFailValidationCardWith2NumbersInCVC() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithCVC2Numbers();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }


    @Test
    @DisplayName("Card with 1 numbers in CVC")
    void shouldFailValidationCardWith1NumbersInCVC() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithCVC1Numbers();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();


    }
}
