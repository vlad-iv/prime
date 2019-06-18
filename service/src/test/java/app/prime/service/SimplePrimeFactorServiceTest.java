package app.prime.service;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimplePrimeFactorServiceTest {
    SimplePrimeFactorService primeFactorService;

    @Before
    public void setUp() {
        primeFactorService = new SimplePrimeFactorService();
        primeFactorService.primeService = new SimplePrimeService();
    }

    @Test
    public void testFindPrimeFactors() {
        assertThat(primeFactorService.findPrimeFactors(-1), is(Collections.<Long>emptyList()));
        assertThat(primeFactorService.findPrimeFactors(0), is(Collections.<Long>emptyList()));
        assertThat(primeFactorService.findPrimeFactors(1), is(Collections.<Long>emptyList()));

        assertThat(primeFactorService.findPrimeFactors(2), is(singletonList(2L)));
        assertThat(primeFactorService.findPrimeFactors(3), is(singletonList(3L)));
        assertThat(primeFactorService.findPrimeFactors(4), is(asList(2L, 2L)));
        assertThat(primeFactorService.findPrimeFactors(5), is(singletonList(5L)));
        assertThat(primeFactorService.findPrimeFactors(6), is(asList(2L, 3L)));
        assertThat(primeFactorService.findPrimeFactors(7), is(singletonList(7L)));
        assertThat(primeFactorService.findPrimeFactors(8), is(asList(2L, 2L, 2L)));
        assertThat(primeFactorService.findPrimeFactors(9), is(asList(3L, 3L)));
        assertThat(primeFactorService.findPrimeFactors(10), is(asList(2L, 5L)));
        assertThat(primeFactorService.findPrimeFactors(99), is(asList(3L, 3L, 11L)));
    }
}
