package app.prime.service;

import app.prime.Logged;
import app.prime.WithOutCache;
import org.springframework.stereotype.Service;

/**
 * Check number is prime.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Service
@WithOutCache
public class SimplePrimeService implements PrimeService {
    /**
     * Check a number is prime.
     */
    @Logged
    public boolean isPrime(final long number) {
        for (long l = 2; l < number; l++) {
            if (number % l == 0) {
                return false;
            }
        }
        return number > 0;
    }
}
