package app.prime.service;

import java.util.List;

/**
 * @author Vladimir Ivanov
 */
public interface PrimeService {
    List<Long> findPrimeDivides(long number);
    boolean isPrime(long number);
}
