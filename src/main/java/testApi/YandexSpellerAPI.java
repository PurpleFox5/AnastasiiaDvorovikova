package testApi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matcher;
import testApi.YandexSpellerConstants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.lessThan;
import static testApi.YandexSpellerConstants.*;

public class YandexSpellerAPI {

    private static final String YANDEX_SPELLER_API_URI = "https://speller.yandex.net/services/spellservice.json/checkTexts";
    private HashMap<String, String> params = new HashMap<>();
    private List<String> texts = new ArrayList<>();
    private Method method = Method.GET;

    private YandexSpellerAPI() {
    }

    public static ApiBuilder with() {
        YandexSpellerAPI api = new YandexSpellerAPI();
        return new ApiBuilder(api);
    }

    public static class ApiBuilder {
        YandexSpellerAPI spellerApi;

        private ApiBuilder(YandexSpellerAPI gcApi) {
            spellerApi = gcApi;
        }

        ApiBuilder text(String... texts) {
            spellerApi.texts.addAll(Arrays.asList(texts));
            return this;
        }

        ApiBuilder text(List<String> texts) {
            spellerApi.texts.addAll(texts);
            return this;
        }

        ApiBuilder options(Options options) {
            spellerApi.params.put(PARAM_OPTIONS, options.toString());
            return this;
        }

        ApiBuilder format(Format format) {
            spellerApi.params.put(PARAM_FORMAT, format.toString());
            return this;
        }

        ApiBuilder language(Language language) {
            spellerApi.params.put(PARAM_LANG, language.langCode());
            return this;
        }

        ApiBuilder httpMethod(Method method) {
            spellerApi.method = method;
            return this;
        }

        Response callApi() {
            return RestAssured.with()
                    .queryParam(PARAM_TEXT, spellerApi.texts)
                    .queryParams(spellerApi.params)
                    .log().all()
                    .request(spellerApi.method, YANDEX_SPELLER_API_URI)
                    .prettyPeek();
        }
    }

    static ResponseSpecification successResponse() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectContentType(ContentType.JSON)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(20000L))
                .build();
    }

    static ResponseSpecification successResponse(Matcher<?> matcher) {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectContentType(ContentType.JSON)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(20000L))
                .expectBody(matcher)
                .build();
    }

    static ResponseSpecification responseWithMistakes(int status, StatusLine statusLine, InvalidParameter message) {
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .expectStatusLine(statusLine.toString())
                .expectHeader("Connection", "keep-alive")
                .expectHeader("SpellerService", message.toString())
                .expectResponseTime(lessThan(20000L))
                .build();
    }

    static List<List<YandexSpellerAnswer>> getYandexSpellerAnswers(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<List<List<YandexSpellerAnswer>>>() {
        }.getType());
    }

}
