package app.prime.service;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cached prime service.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class CachedPrimeServiceTest {
    private CachedPrimeService primeService;
    private ConcurrentHashMap<Long, Boolean> cache;

    @Before
    public void setUp() {
        cache = new ConcurrentHashMap<>();
        primeService = new CachedPrimeService(new SimplePrimeService(), cache);
    }

    @Test
    public void testIsPrime() {

        checkNotPrime(-1);
        checkNotPrime(0);
        checkNotPrime(4);
        checkNotPrime(10);
        checkNotPrime(10);

        checkPrime(1);
        checkPrime(2);
        checkPrime(3);
        checkPrime(5);
        checkPrime(7);
        checkPrime(7);
    }

    private void checkNotPrime(long n) {
        assertFalse("Not prime number " + n, primeService.isPrime(n));
        assertTrue("Cache contain number " + n, cache.containsKey(n));
        assertFalse("Valid not prime cached result for " + n, cache.get(n));
    }

    private void checkPrime(long n) {
        assertTrue("Prime number " + n, primeService.isPrime(n));
        assertTrue("Cache contain number " + n, cache.containsKey(n));
        assertTrue("Valid prime cached result for " + n, cache.get(n));
    }
}
