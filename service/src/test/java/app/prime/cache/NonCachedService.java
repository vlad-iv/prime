package app.prime.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Non cached service.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Component
class NonCachedService {
    AtomicLong count = new AtomicLong();

    public long countCall() {
        return count.incrementAndGet();
    }
}
