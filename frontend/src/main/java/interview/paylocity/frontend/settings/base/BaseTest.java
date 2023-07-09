package interview.paylocity.frontend.settings.base;

import interview.paylocity.frontend.settings.dataholder.GeneralDataHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Locale;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BasePage.class)
public class BaseTest {

    // Initiation

    protected static final Logger log = LogManager.getLogger(BasePage.class);

    protected GeneralDataHolder generalDataHolder = GeneralDataHolder.getInstance();

    private final Random randomGenerator = new Random();

    // Random Methods

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

    protected int randomNumberGenerator(int randomNumberRange) {
        return randomGenerator.nextInt(randomNumberRange);
    }

}
