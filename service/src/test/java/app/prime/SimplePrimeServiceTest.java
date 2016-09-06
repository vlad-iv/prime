package app.prime;

import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
        assertTrue(primeService.isPrime(11));
        assertTrue(primeService.isPrime(13));
        assertTrue(primeService.isPrime(17));
        assertTrue(primeService.isPrime(19));
        assertTrue(primeService.isPrime(23));

        assertFalse(primeService.isPrime(4));
        assertFalse(primeService.isPrime(6));
        assertFalse(primeService.isPrime(8));
        assertFalse(primeService.isPrime(9));
        assertFalse(primeService.isPrime(10));
        assertFalse(primeService.isPrime(12));
        assertFalse(primeService.isPrime(14));
        assertFalse(primeService.isPrime(15));
        assertFalse(primeService.isPrime(16));
        assertFalse(primeService.isPrime(18));
        assertFalse(primeService.isPrime(20));
        assertFalse(primeService.isPrime(21));
        assertFalse(primeService.isPrime(22));
    }

    @Test
    public void testFindPrimes() {
        assertThat(primeService.findPrimes(-1), is(Collections.<Long>emptyList()));
        assertThat(primeService.findPrimes(0), is(Collections.<Long>emptyList()));

        assertThat(primeService.findPrimes(1), is(singletonList(1L)));
        assertThat(primeService.findPrimes(2), is(asList(1L, 2L)));
        assertThat(primeService.findPrimes(3), is(asList(1L, 2L, 3L)));
        assertThat(primeService.findPrimes(4), is(asList(1L, 2L, 3L)));
        assertThat(primeService.findPrimes(5), is(asList(1L, 2L, 3L, 5L)));
        assertThat(primeService.findPrimes(6), is(asList(1L, 2L, 3L, 5L)));
        assertThat(primeService.findPrimes(7), is(asList(1L, 2L, 3L, 5L, 7L)));
        assertThat(primeService.findPrimes(8), is(asList(1L, 2L, 3L, 5L, 7L)));
        assertThat(primeService.findPrimes(9), is(asList(1L, 2L, 3L, 5L, 7L)));
        assertThat(primeService.findPrimes(10), is(asList(1L, 2L, 3L, 5L, 7L)));
        assertThat(primeService.findPrimes(11), is(asList(1L, 2L, 3L, 5L, 7L, 11L)));
        assertThat(primeService.findPrimes(12), is(asList(1L, 2L, 3L, 5L, 7L, 11L)));
        assertThat(primeService.findPrimes(13), is(asList(1L, 2L, 3L, 5L, 7L, 11L, 13L)));
        assertThat(primeService.findPrimes(14), is(asList(1L, 2L, 3L, 5L, 7L, 11L, 13L)));
        assertThat(primeService.findPrimes(15), is(asList(1L, 2L, 3L, 5L, 7L, 11L, 13L)));
        assertThat(primeService.findPrimes(16), is(asList(1L, 2L, 3L, 5L, 7L, 11L, 13L)));
        assertThat(primeService.findPrimes(17), is(asList(1L, 2L, 3L, 5L, 7L, 11L, 13L, 17L)));
        assertThat(primeService.findPrimes(18), is(asList(1L, 2L, 3L, 5L, 7L, 11L, 13L, 17L)));
        assertThat(primeService.findPrimes(19), is(asList(1L, 2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L)));
        assertThat(primeService.findPrimes(20), is(asList(1L, 2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L)));
        assertThat(primeService.findPrimes(21), is(asList(1L, 2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L)));
        assertThat(primeService.findPrimes(22), is(asList(1L, 2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L)));
        assertThat(primeService.findPrimes(23), is(asList(1L, 2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L)));

    }
}
