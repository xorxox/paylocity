package interview.paylocity.backend.settings.connection;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Component
public class RestClient {

    public RequestSpecification prepareRequestWithBody(String url, String path, String jsonBody, String accessToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", accessToken);

        return given()
                .baseUri(url)
                .basePath(path)
                .headers(headers)
                .body(jsonBody)
                .config(config());
    }

    public RequestSpecification prepareRequestOnlyHeader(String url, String path, String accessToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", accessToken);

        return given()
                .baseUri(url)
                .basePath(path)
                .headers(headers)
                .config(config());
    }

    private RestAssuredConfig config() {
        return RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 300000)
                        .setParam("http.socket.timeout", 300000)
                        .setParam("http.connection-manager.timeout", 300000));

    }
}
