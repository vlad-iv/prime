package app.prime.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Cached service.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Component
@WithCache
class CachedService {
    AtomicLong count = new AtomicLong();

    public long countCall() {
        return count.incrementAndGet();
    }
}
