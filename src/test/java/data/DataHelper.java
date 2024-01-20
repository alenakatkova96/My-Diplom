package data;

import com.github.javafaker.Faker;
import lombok.Value;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static Faker fakerEn = new Faker(new Locale("en"));
    private static Faker fakerRu = new Faker(new Locale("ru"));

    private DataHelper() {
    }

    public static @NotNull String getLastYear(int minusOneYear) {
        return LocalDate.now().minusYears(minusOneYear).format(DateTimeFormatter.ofPattern("yy"));
    }


    public static @NotNull String generateHolderName() {
        return fakerEn.name().lastName() + " " + fakerEn.name().firstName();
    }

    public static @NotNull String generateMonth(int month) {
        return LocalDate.now().plusMonths(month).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static @NotNull String generateYear(int year) {
        return LocalDate.now().plusYears(year).format(DateTimeFormatter.ofPattern("yy"));
    }


    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    @Contract(pure = true)
    public static @NotNull String getApprovedCardNumber() {
        return ("4444 4444 4444 4441");
    }

    @Contract(pure = true)
    public static @NotNull String getDeclinedCardNumber() {
        return ("4444 4444 4444 4442");
    }

    @Contract(" -> new")
    public static @NotNull CardInfo getValidDataWithApprovedCard() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    @Contract(" -> new")
    public static @NotNull CardInfo getValidDataWithDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWithEmptyFields() {
        return new CardInfo(
                null,
                null,
                null,
                null,
                null);
    }

    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWithEmptyNumber() {
        return new CardInfo(null,
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }


    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWithRandomNumber() {
        return new CardInfo(fakerEn.numerify("#### #### #### ####"),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }


    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWith15() {
        return new CardInfo(fakerEn.numerify("#### #### #### ###"),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }


    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWithNullMonth() {
        return new CardInfo(getApprovedCardNumber(),
                null,
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWithMonthWithTwoZero() {
        return new CardInfo(getApprovedCardNumber(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("00")),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWithMonthWithZero() {
        return new CardInfo(getApprovedCardNumber(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("0")),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }


    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWith13Month() {
        return new CardInfo(getApprovedCardNumber(),
                "13",
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }


    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWithNullYear() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                null,
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWithLastYear() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                getLastYear(1),
                generateHolderName(),
                fakerEn.numerify("###"));
    }


    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWith1NumberYear() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                fakerEn.numerify("#"),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWithNullOwner() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                null,
                fakerEn.numerify("###"));
    }


    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWithOwnerWithNumbers() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                "2342432",
                fakerEn.numerify("###"));
    }

    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWithOwnerCyrillic() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                fakerRu.name().firstName() + " " + fakerRu.name().lastName(),
                fakerEn.numerify("###"));
    }

    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWithOwnerSpecialCharacters() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                ")(*&^%$#@!@",
                fakerEn.numerify("###"));
    }

    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWithEmptyCVC() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                null);
    }


    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWithCVC2Numbers() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("##"));
    }

    @Contract(" -> new")
    public static @NotNull CardInfo getCardInfoWithCVC1Numbers() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("#"));
    }

}
