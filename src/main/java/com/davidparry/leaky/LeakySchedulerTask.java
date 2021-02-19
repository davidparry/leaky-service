package com.davidparry.leaky;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class LeakySchedulerTask {
    private static final Logger log = LoggerFactory.getLogger(LeakySchedulerTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final LeakyConfig leakyConfig;

    private final LeakyBean leakyBean;

    @Value("${app.throw}")
    private boolean stackTrace;

    public LeakySchedulerTask() {
        this.leakyConfig = new LeakyConfig();
        this.leakyBean = new LeakyBean(createALongString(99900000));
    }

    private static String createALongString(int size) {
        int leftLimit = 97;
        int rightLimit = 122;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Scheduled(fixedRate = 500)
    public void reportCurrentTime() {
        log.info("Adding another Leak at time  {}", dateFormat.format(new Date()));
        this.leakyConfig.setLeak(new LeakyKey(createALongString(7000), createALongString(990000)), leakyBean);
        if (stackTrace) {
            try {
                throwException();
            } catch (Exception e) {
                log.error("ReportCurrentTime logging of the caught exception.", e);
            }
            stackTrace = false;
        }
    }

    public void throwException() throws Exception {
        Integer[] values = {1, 2, 3};
        try {
            if (values[3] == 3) {
                // the above will throw IndexOutOfBoundsException bounds
                log.error("should not reach this point");
            }
        } catch (IndexOutOfBoundsException er) {
            log.info("throwException method logging the exception at info level and wrapping " +
                    "the exception and throwing the exception .", er);
            ApplicationException another = new ApplicationException("Second level of Application Wrapping Exception"
                    , new Exception("A on purpose error to show the stack trace as an example", er));
            throw new ApplicationException("First level of Application Wrapping Exception", another);
        }
    }

}
