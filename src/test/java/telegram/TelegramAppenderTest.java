package telegram;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class TelegramAppenderTest {

    private final Logger logger = LoggerFactory.getLogger(TelegramAppenderTest.class);

//    @Test
    public void test() {
        logger.info("Test");
        logger.error("Error");
    }
}
