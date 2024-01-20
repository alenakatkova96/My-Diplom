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

public class CreditTest {
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
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getValidDataWithApprovedCard();
        creditPage.inputData(cardInfo);
        creditPage.getSuccessNotification();
        var actual = DataBaseHelper.getStatusCreditRequest();
        assertEquals("APPROVED", actual);
    }

    @Test
    @DisplayName("Card with DECLINED status")
    void shouldDeclinePaymentByCard() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getValidDataWithDeclinedCard();
        creditPage.inputData(cardInfo);
        creditPage.getErrorNotification();
        var actual = DataBaseHelper.getStatusCreditRequest();
        assertEquals("DECLINED", actual);
    }

    @Test
    @DisplayName("Card with empty fields")
    void shouldFailValidationCardWithEmptyFields() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyFields();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }

    @Test
    @DisplayName("Card with empty number")
    void shouldFailValidationWithEmptyCardNumber() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyNumber();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with random number")
    void shouldDeclineTransactionCardWithRandomNumber() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithRandomNumber();
        creditPage.inputData(cardInfo);
        creditPage.getErrorNotification();
    }

    @Test
    @DisplayName("Card with 15 numbers")
    void shouldFailValidationWithCard15Numbers() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWith15();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with empty month")
    void shouldFailValidationCardWithEmptyMonth() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullMonth();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with two zero in month")
    void shouldFailValidationCardWithZeroInMonth() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithMonthWithTwoZero();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongDateFormat();
    }

    @Test
    @DisplayName("Card with month with one zero")
    void shouldFailValidationCardWith1NumberMonth() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithMonthWithZero();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with month number 13")
    void shouldFailValidationWithMonthAbove12() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWith13Month();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongDateFormat();
    }

    @Test
    @DisplayName("Card with empty year")
    void shouldFailValidationCardWithEmptyYear() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullYear();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with last year")
    void shouldFailValidationCardWithLastYear() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithLastYear();
        creditPage.inputData(cardInfo);
        creditPage.checkingCardEnded();
    }

    @Test
    @DisplayName("Card with year with one number")
    void shouldFailValidationCardWith1NumberYear() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWith1NumberYear();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with empty owner")
    void shouldFailValidationCardWithEmptyOwner() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullOwner();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }

    @Test
    @DisplayName("Card with owner with name with numbers")
    void shouldFailValidationCardWithOwnerWithNumbers() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerWithNumbers();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }


    @Test
    @DisplayName("Card with owner with name with cyrillic")
    void shouldFailValidationCardWithNameWithCyrillic() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerCyrillic();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with owner with name with special characters")
    void shouldFailValidationCardWithNameSpecialCharacters() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerSpecialCharacters();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with empty CVC")
    void shouldFailValidationCardWithEmptyCVC() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyCVC();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }

    @Test
    @DisplayName("Card with 2 numbers in CVC")
    void shouldFailValidationCardWith2NumbersInCVC() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithCVC2Numbers();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Card with 1 numbers in CVC")
    void shouldFailValidationCardWith1NumberInCVC() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithCVC1Numbers();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }


}
