package app.prime.service;

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
public class SimplePrimeService implements PrimeService {
    /**
     * List pime factors.
     */
    @Cacheable
    public List<Long> findPrimeDivides(final long number) {

        final List<Long> primes = new ArrayList<>();

        long divide = number;
        for (long l = 2; l <= number; l++) {
            if (divide == 1) {
                break;
            }
            if (!isPrime(l)) {
                continue;
            }

            while (divide % l == 0) {
                divide /= l;
                primes.add(l);
            }
        }
        return primes;
    }

    /**
     * Check a number is prime.
     */
    @Cacheable
    public boolean isPrime(final long number) {
        for (long l = 2; l < number; l++) {
            if (number % l == 0) {
                return false;
            }
        }
        return number > 0;
    }
}
