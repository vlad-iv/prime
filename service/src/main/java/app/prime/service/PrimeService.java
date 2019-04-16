package app.prime.service;

import java.util.List;

/**
 * Expand a number into prime factors.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public interface PrimeService {
    List<Long> findPrimeDivides(long number);

    boolean isPrime(long number);
}
