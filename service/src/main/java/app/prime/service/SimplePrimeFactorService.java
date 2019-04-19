package app.prime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Expand the number into prime factors by brute force.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Service
public class SimplePrimeFactorService implements PrimeFactorService {
    final PrimeService primeService;

    @Autowired
    public SimplePrimeFactorService(PrimeService primeService) {
        this.primeService = primeService;
    }

    /**
     * List prime factors.
     */
    @Cacheable
    public List<Long> findPrimeFactors(final long number) {

        final List<Long> primes = new ArrayList<>();

        long divide = number;
        for (long l = 2; l <= number; l++) {
            if (divide == 1) {
                break;
            }
            if (!primeService.isPrime(l)) {
                continue;
            }

            while (divide % l == 0) {
                divide /= l;
                primes.add(l);
            }
        }
        return primes;
    }
}
