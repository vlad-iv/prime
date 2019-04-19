package app.prime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Expand the number into prime factors by brute force.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Service
@WithOutCache
public class SimplePrimeFactorService implements PrimeFactorService {
    final PrimeService primeService;

    @Autowired
    @WithCache
    public SimplePrimeFactorService(PrimeService primeService) {
        this.primeService = primeService;
    }

    /**
     * List prime factors.
     */
    public List<Long> findPrimeFactors(final long number) {

        final List<Long> primes = new ArrayList<>();

        long divide = number;
        for (long factor = 2; factor <= number; factor++) {
            if (divide == 1) {
                break;
            }
            if (!primeService.isPrime(factor)) {
                continue;
            }
            if (primeService.isPrime(divide)) {
                primes.add(divide);
                break;
            }

            while (divide % factor == 0) {
                divide /= factor;
                primes.add(factor);
            }
        }
        return primes;
    }
}
