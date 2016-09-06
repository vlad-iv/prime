package app.prime;

import app.prime.service.PrimeService;
import app.prime.service.SimplePrimeService;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
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

    @Test
    public void testFindPrimes() {
        assertThat(primeService.findPrimeDivides(-1), is(Collections.<Long>emptyList()));
        assertThat(primeService.findPrimeDivides(0), is(Collections.<Long>emptyList()));
        assertThat(primeService.findPrimeDivides(1), is(Collections.<Long>emptyList()));

        assertThat(primeService.findPrimeDivides(2), is(singletonList(2L)));
        assertThat(primeService.findPrimeDivides(3), is(singletonList(3L)));
        assertThat(primeService.findPrimeDivides(4), is(asList(2L, 2L)));
        assertThat(primeService.findPrimeDivides(5), is(singletonList(5L)));
        assertThat(primeService.findPrimeDivides(6), is(asList(2L, 3L)));
        assertThat(primeService.findPrimeDivides(7), is(singletonList(7L)));
        assertThat(primeService.findPrimeDivides(8), is(asList(2L, 2L, 2L)));
        assertThat(primeService.findPrimeDivides(9), is(asList(3L, 3L)));
        assertThat(primeService.findPrimeDivides(10), is(asList(2L, 5L)));

    }
}
