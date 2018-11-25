package testApi;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.http.Method.*;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
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
                        .callApi());
        assertThat("Expected number of answers is wrong.", answers.get(0).size(), equalTo(0));
    }

    @Test
    public void unknownWord() {
        YandexSpellerAPI.with()
                .language(EN)
                .text(REQUISITE.word, MOTHER.word)
                .callApi()
                .then()
                .specification(successResponse(Matchers.allOf(
                        Matchers.stringContainsInOrder(Arrays.asList(REQUISITE.word, REQUISITE.s, MOTHER.word, MOTHER.s)),
                        Matchers.containsString(ERROR_UNKNOWN_WORD.toString()))));
    }

    @Test
    public void wordWithUppercaseLetters() {
        List<List<YandexSpellerAnswer>> answers = YandexSpellerAPI.getYandexSpellerAnswers(
        YandexSpellerAPI.with()
                .language(EN)
                .text(DIFFERENT_LETTERS.word)
                .callApi());
        assertThat("Expected number of answers is wrong.", answers.get(0).size(), not(0));
        assertThat("Expected error \"Incorrect use of uppercase and lowercase letters.\"",
                "\"code\":" + answers.get(0).get(0).getCode(), equalTo(ERROR_CAPITALIZATION.toString()));
        assertThat("Expected word is wrong", answers.get(0).get(0).getWord(), equalTo(DIFFERENT_LETTERS.word));
        assertThat("Expected result is wrong", answers.get(0).get(0).getS(), equalTo(DIFFERENT_LETTERS.s));
    }

    @Test
    public void ignoreUppercaseLetters() {
        List<List<YandexSpellerAnswer>> answers = YandexSpellerAPI.getYandexSpellerAnswers(
                YandexSpellerAPI.with()
                        .language(EN)
                        .text(DIFFERENT_LETTERS.word)
                        .options(IGNORE_CAPITALIZATION)
                        .callApi());
        assertThat("Expected number of answers is wrong.", answers.get(0).size(), equalTo(0));
    }

    @Test
    public void repeatedWord() {
        List<List<YandexSpellerAnswer>> answers = YandexSpellerAPI.getYandexSpellerAnswers(
                YandexSpellerAPI.with()
                        .text(REPEAT.word, REPEAT.word)
                        .options(FIND_REPEAT_WORDS)
                        .callApi());
        assertThat("Expected number of answers is wrong.", answers.get(0).size(), not(0));
        assertThat("Expected error \"Repeat words.\"", "\"code\":" + answers.get(0).get(0).getCode(),
                equalTo(ERROR_REPEAT_WORD.toString()));
        assertThat("Expected word is wrong", answers.get(0).get(0).getWord(), equalTo(REPEAT.word));
        assertThat("Expected result is wrong", answers.get(0).get(0).getS(), equalTo(REPEAT.s));
    }

    @Test
    public void wordWithDigits() {
        List<List<YandexSpellerAnswer>> answers = YandexSpellerAPI.getYandexSpellerAnswers(
        YandexSpellerAPI.with()
                .language(EN)
                .text(WITH_DIGITS.word)
                .callApi());
        assertThat("Expected number of answers is wrong.", answers.get(0).size(), not(0));
        assertThat("Expected error \"Words are not in the dictionary.\"", "\"code\":" + answers.get(0).get(0).getCode(),
                equalTo(ERROR_UNKNOWN_WORD.toString()));
        assertThat("Expected word is wrong", answers.get(0).get(0).getWord(), equalTo(WITH_DIGITS.word));
        assertThat("Expected result is wrong", answers.get(0).get(0).getS().get(0), equalTo(WITH_DIGITS.s));
    }

    @Test
    public void ignoreDigit() {
        List<List<YandexSpellerAnswer>> answers = YandexSpellerAPI.getYandexSpellerAnswers(
                YandexSpellerAPI.with()
                        .text(WORD_WITH_LEADING_DIGITS.word)
                        .options(IGNORE_DIGITS)
                        .callApi());
        assertThat("Expected number of answers is wrong.", answers.get(0).size(), equalTo(0));
    }

    @Test
    public void checkTwoTimes() {
        List<List<YandexSpellerAnswer>> firstList = YandexSpellerAPI.getYandexSpellerAnswers(
                YandexSpellerAPI.with()
                        .text(MOTHER.word)
                        .callApi());
        System.out.println(repeat("=", 50));
        List<String> correctWord = firstList.get(0).get(0).s;

        YandexSpellerAPI.with()
                .text(correctWord)
                .callApi()
                .then()
                .specification(successResponse());
    }

    @Test
    public void splitPhrase() {
        YandexSpellerAPI.with()
                .language(EN)
                .text(SPLIT_PHRASE.word)
                .callApi()
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
                .callApi()
                .then()
                .specification(responseWithMistakes(SC_BAD_REQUEST, SL_BAD_REQUEST, LANG));
    }

    @Test
    public void wrongFormat() {
        YandexSpellerAPI.with()
                .text(REQUISITE.word)
                .language(EN)
                .format(WRONG_FORMAT)
                .callApi()
                .then()
                .specification(responseWithMistakes(SC_BAD_REQUEST, SL_BAD_REQUEST, FORMAT));
    }

    @Test
    public void tryNotAllowedMethods() {
        YandexSpellerAPI.with()
                .text(REQUISITE.word)
                .httpMethod(PATCH)
                .callApi()
                .then()
                .specification(responseWithMistakes(SC_METHOD_NOT_ALLOWED, SL_METHOD_NOT_ALLOWED, METHOD));
        System.out.println(repeat("=", 50));

        YandexSpellerAPI.with()
                .text(REQUISITE.word)
                .httpMethod(PUT)
                .callApi()
                .then()
                .specification(responseWithMistakes(SC_METHOD_NOT_ALLOWED, SL_METHOD_NOT_ALLOWED, METHOD));
        System.out.println(repeat("=", 50));

        YandexSpellerAPI.with()
                .text(REQUISITE.word)
                .httpMethod(DELETE)
                .callApi()
                .then()
                .specification(responseWithMistakes(SC_METHOD_NOT_ALLOWED, SL_METHOD_NOT_ALLOWED, METHOD));
    }
}