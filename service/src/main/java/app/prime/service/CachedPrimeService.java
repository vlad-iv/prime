package app.prime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache prime calculation results.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Service
@WithCache
public class CachedPrimeService implements PrimeService {
    private final PrimeService primeService;
    private final Map<Long, Boolean> cache;

    @Autowired
    @WithOutCache
    public CachedPrimeService(PrimeService primeService) {
        this(primeService, new ConcurrentHashMap<>());
    }

    CachedPrimeService(PrimeService primeService, Map<Long, Boolean> cache) {
        this.primeService = primeService;
        this.cache = cache;
    }

    /**
     * Check a number is prime.
     */
    public boolean isPrime(final long number) {
        if (cache.containsKey(number)) {
            return cache.get(number);
        }
        boolean prime = primeService.isPrime(number);
        cache.put(number, prime);
        return prime;
    }
}
