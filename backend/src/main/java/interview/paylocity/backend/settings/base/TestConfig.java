package interview.paylocity.backend.settings.base;

import interview.paylocity.backend.settings.connection.RestClient;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import interview.paylocity.backend.settings.dataholder.GeneralDataHolder;

import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static interview.paylocity.backend.settings.constants.Constants.NOT_USED;
import static interview.paylocity.backend.settings.constants.Constants.RANDOM;

@ContextConfiguration(classes = TestConfig.class)
@ComponentScan(value = {"interview.paylocity.backend.general", "interview.paylocity.backend.settings", "interview.paylocity.*"})
public class TestConfig {

    // Initiation

    protected static final Logger log = LogManager.getLogger(TestConfig.class);

    protected GeneralDataHolder generalDataHolder = GeneralDataHolder.getInstance();

    protected RestClient restClient = new RestClient();

    // Json Builders

    protected JSONObject prepareJsonObjectField(JSONObject jsonObject, String fieldKey, String fieldValue) {
        switch (fieldValue) {
            case RANDOM -> jsonObject.put(fieldKey, randomString(8));
            case NOT_USED -> log.debug("Field key " + fieldKey + " is not used.");
            default -> jsonObject.put(fieldKey, fieldValue);
        }

        return jsonObject;
    }

    protected JSONObject prepareIntegerJsonObjectField(JSONObject jsonObject, String fieldKey, String fieldValue) {
        if (!NOT_USED.equals(fieldValue) && !"null".equals(fieldValue)) {
            assert fieldValue != null;
            jsonObject.put(fieldKey, Integer.parseInt(fieldValue));
        }

        return jsonObject;
    }

    // Response Verifier

    public void responseReturnedCorrectHTTPCode(Response response, int expectedResponseCode) {
        try {
            assertEquals(expectedResponseCode, response.getStatusCode());
        } catch (AssertionError assertionError) {
            log.error(response.getBody().prettyPrint());
            log.error(response.getHeaders().toString());
            log.error(String.valueOf(assertionError));
            fail();
        }
    }

    // Random Generated values

    protected String randomString(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase(Locale.ROOT);
        String digits = "0123456789";
        String alphanumeric = upper + lower + digits;

        char[] symbols = alphanumeric.toCharArray();
        char[] buf = new char[length];

        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[randomGenerator.nextInt(symbols.length)];
        return new String(buf);
    }

    // Auxiliary Methods

    private final Random randomGenerator = new Random();
}
