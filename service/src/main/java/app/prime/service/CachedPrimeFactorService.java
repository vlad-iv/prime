package app.prime.service;

import app.prime.WithCache;
import app.prime.WithOutCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache prime factors calculation results.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Service
@WithCache
public class CachedPrimeFactorService implements PrimeFactorService {
    private final PrimeFactorService primeFactorService;
    private final Map<Long, List<Long>> cache;

    @Autowired
    @WithOutCache
    public CachedPrimeFactorService(PrimeFactorService primeFactorService) {
        this(primeFactorService, new ConcurrentHashMap<>());
    }

    CachedPrimeFactorService(PrimeFactorService primeFactorService, Map<Long, List<Long>> cache) {
        this.primeFactorService = primeFactorService;
        this.cache = cache;
    }

    /**
     * Expand a number into prime factors.
     */
    public List<Long> findPrimeFactors(long number) {
        if (cache.containsKey(number)) {
            return cache.get(number);
        }
        List<Long> factors = primeFactorService.findPrimeFactors(number);
        cache.put(number, factors);
        return factors;
    }
}
