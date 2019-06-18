package app.prime.log;

import app.prime.Logged;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Service with logging method.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Component
public class LoggingService {
    AtomicInteger count = new AtomicInteger();

    @Logged
    public String getLog() {
        return "log - " + count.incrementAndGet();
    }

    public String getNonLog() {
        return "log - " + count.incrementAndGet();
    }
}
