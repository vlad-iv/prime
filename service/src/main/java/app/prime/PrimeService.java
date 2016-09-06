package app.prime;

import java.util.List;

/**
 * @author Vladimir Ivanov
 */
public interface PrimeService {
    List<Long> findPrimes(long number);
    boolean isPrime(long number);
}
