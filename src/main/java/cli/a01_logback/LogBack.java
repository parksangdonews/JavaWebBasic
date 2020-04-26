package cli.a01_logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBack {

    static Logger logger = LoggerFactory.getLogger(LogBack.class);

    public static void main(String[] args) {

        logger.info("Hello World.. Log blah Blah ~");

    }

}
