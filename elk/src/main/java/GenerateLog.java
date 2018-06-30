import org.apache.log4j.Logger;

public class GenerateLog {
    private static final Logger logger = Logger.getLogger(GenerateLog.class);

    public static void main(String[] args) {
        logger.debug("debug 1");
        logger.debug("debug 1");
        logger.debug("debug 2");
        logger.warn("warn1");
        logger.warn("warn2");
        String s = "7ab x/6";
        try {
            Integer.parseInt(s);
        } catch (Exception ex) {
            logger.error("toStringfail", ex);
        }
    }
}
