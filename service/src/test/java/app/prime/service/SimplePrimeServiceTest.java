package app.prime.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimplePrimeServiceTest {
    PrimeService primeService;

    @Before
    public void setUp() {
        primeService = new SimplePrimeService();
    }

    @Test
    public void testIsPrime() {
        assertFalse(primeService.isPrime(-1));
        assertFalse(primeService.isPrime(0));

        assertTrue(primeService.isPrime(1));
        assertTrue(primeService.isPrime(2));
        assertTrue(primeService.isPrime(3));
        assertTrue(primeService.isPrime(5));
        assertTrue(primeService.isPrime(7));

        assertFalse(primeService.isPrime(4));
        assertFalse(primeService.isPrime(6));
        assertFalse(primeService.isPrime(8));
        assertFalse(primeService.isPrime(9));
        assertFalse(primeService.isPrime(10));
    }
}
