package testApi;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.repeat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static testApi.YandexSpellerAPI.responseWithMistakes;
import static testApi.YandexSpellerAPI.successResponse;
import static testApi.YandexSpellerConstants.ErrorCodes.*;
import static testApi.YandexSpellerConstants.Examples.*;
import static testApi.YandexSpellerConstants.Format.WRONG_FORMAT;
import static testApi.YandexSpellerConstants.InvalidParameter.*;
import static testApi.YandexSpellerConstants.Language.EN;
import static testApi.YandexSpellerConstants.Language.WRONG_LANGUAGE;
import static testApi.YandexSpellerConstants.Options.*;
import static testApi.YandexSpellerConstants.StatusLine.SL_BAD_REQUEST;
import static testApi.YandexSpellerConstants.StatusLine.SL_METHOD_NOT_ALLOWED;

public class TestYandexSpeller {

    @Test
    public void withoutMistakes() {
        List<List<YandexSpellerAnswer>> answers = YandexSpellerAPI.getYandexSpellerAnswers(
                YandexSpellerAPI.with()
                        .text(CORRECT_WORD.word)
                        .callGetApi());
        assertThat("Expected number of answers is wrong.", answers.get(0).size(), equalTo(0));
    }

    @Test
    public void unknownWord() {
        YandexSpellerAPI.with()
                .language(EN)
                .text(REQUISITE.word, MOTHER.word)
                .callGetApi()
                .then()
                .specification(successResponse(Matchers.allOf(
                        Matchers.stringContainsInOrder(Arrays.asList(REQUISITE.word, REQUISITE.s, MOTHER.word, MOTHER.s)),
                        Matchers.containsString(ERROR_UNKNOWN_WORD.toString()))));
    }

    @Test
    public void wordWithUppercaseLetters() {
        YandexSpellerAPI.with()
                .language(EN)
                .text(DIFFERENT_LETTERS.word)
                .callGetApi()
                .then()
                .specification(successResponse(Matchers.allOf(Matchers.containsString(ERROR_CAPITALIZATION.toString()))));
    }

    @Test
    public void ignoreUppercaseLetters() {
        List<List<YandexSpellerAnswer>> answers = YandexSpellerAPI.getYandexSpellerAnswers(
                YandexSpellerAPI.with()
                        .language(EN)
                        .text(DIFFERENT_LETTERS.word)
                        .options(IGNORE_CAPITALIZATION.toString())
                        .callGetApi());
        assertThat("Expected number of answers is wrong.", answers.get(0).size(), equalTo(0));
    }

    @Test
    public void repeatedWord() {
        YandexSpellerAPI.with()
                .text(REPEAT.word, REPEAT.word)
                .options(FIND_REPEAT_WORDS.toString())
                .callGetApi()
                .then()
                .specification(successResponse(Matchers.allOf(Matchers.containsString(ERROR_REPEAT_WORD.toString()))));
    }

    @Test
    public void wordWithDigits() {
        YandexSpellerAPI.with()
                .language(EN)
                .text(WITH_DIGITS.word)
                .callGetApi()
                .then()
                .specification(successResponse(Matchers.allOf(
                        Matchers.stringContainsInOrder(Arrays.asList(WITH_DIGITS.word, WITH_DIGITS.s)),
                        Matchers.containsString(ERROR_UNKNOWN_WORD.toString()))));
    }

    @Test
    public void ignoreDigit() {
        List<List<YandexSpellerAnswer>> answers = YandexSpellerAPI.getYandexSpellerAnswers(
                YandexSpellerAPI.with()
                        .text(WORD_WITH_LEADING_DIGITS.word)
                        .options(IGNORE_DIGITS.toString())
                        .callGetApi());
        assertThat("Expected number of answers is wrong.", answers.get(0).size(), equalTo(0));
    }

    @Test
    public void checkTwoTimes() {
        List<List<YandexSpellerAnswer>> firstList = YandexSpellerAPI.getYandexSpellerAnswers(
                YandexSpellerAPI.with()
                        .text(MOTHER.word)
                        .callGetApi());
        System.out.println(repeat("=", 50));
        List<String> correctWord = firstList.get(0).get(0).s;

        YandexSpellerAPI.with()
                .text(correctWord)
                .callGetApi()
                .then()
                .specification(successResponse());
    }

    @Test
    public void splitPhrase() {
        YandexSpellerAPI.with()
                .language(EN)
                .text(SPLIT_PHRASE.word)
                .callGetApi()
                .then()
                .specification(successResponse(Matchers.allOf(
                        Matchers.stringContainsInOrder(Arrays.asList(SPLIT_PHRASE.word, SPLIT_PHRASE.s)),
                        Matchers.containsString(ERROR_UNKNOWN_WORD.toString()))));
    }

    @Test
    public void wrongLanguage() {
        YandexSpellerAPI.with()
                .language(WRONG_LANGUAGE)
                .text(REQUISITE.word)
                .callGetApi()
                .then()
                .specification(responseWithMistakes(HttpStatus.SC_BAD_REQUEST, SL_BAD_REQUEST.toString(), LANG.toString()));
    }

    @Test
    public void wrongFormat() {
        YandexSpellerAPI.with()
                .text(REQUISITE.word)
                .language(EN)
                .format(WRONG_FORMAT)
                .callGetApi()
                .then()
                .specification(responseWithMistakes(HttpStatus.SC_BAD_REQUEST, SL_BAD_REQUEST.toString(), FORMAT.toString()));
    }

    @Test
    public void tryNotAllowedMethods() {
        YandexSpellerAPI.with()
                .text(REQUISITE.word)
                .callPatchApi()
                .then()
                .specification(responseWithMistakes(HttpStatus.SC_METHOD_NOT_ALLOWED, SL_METHOD_NOT_ALLOWED.toString(),
                        METHOD.toString()));
        System.out.println(repeat("=", 50));

        YandexSpellerAPI.with()
                .text(REQUISITE.word)
                .callPutApi()
                .then()
                .specification(responseWithMistakes(HttpStatus.SC_METHOD_NOT_ALLOWED, SL_METHOD_NOT_ALLOWED.toString(),
                        METHOD.toString()));
        System.out.println(repeat("=", 50));

        YandexSpellerAPI.with()
                .text(REQUISITE.word)
                .callDeleteApi()
                .then()
                .specification(responseWithMistakes(HttpStatus.SC_METHOD_NOT_ALLOWED, SL_METHOD_NOT_ALLOWED.toString(),
                        METHOD.toString()));
    }
}