package app.prime.service;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Test cached prime favors service.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class CachedPrimeFactorServiceTest {
    private CachedPrimeFactorService primeFactorService;
    private Map<Long, List<Long>> cache;

    @Before
    public void setUp() {
        SimplePrimeFactorService primeFactorService = new SimplePrimeFactorService();
        primeFactorService.primeService = new SimplePrimeService();
        this.primeFactorService = new CachedPrimeFactorService(primeFactorService);
        cache = this.primeFactorService.getCache();
    }

    @Test
    public void testFindPrimeFactors() {
        checkPrimeFactors(-1, Collections.emptyList());
        checkPrimeFactors(0, Collections.emptyList());
        checkPrimeFactors(1, Collections.emptyList());
        checkPrimeFactors(2, singletonList(2L));
        checkPrimeFactors(3, singletonList(3L));
        checkPrimeFactors(4, asList(2L, 2L));
        checkPrimeFactors(5, singletonList(5L));
        checkPrimeFactors(6, asList(2L, 3L));
        checkPrimeFactors(7, singletonList(7L));
        checkPrimeFactors(8, asList(2L, 2L, 2L));
        checkPrimeFactors(9, asList(3L, 3L));
        checkPrimeFactors(10, asList(2L, 5L));
        checkPrimeFactors(10, asList(2L, 5L));
        checkPrimeFactors(9999998, asList(2L, 4999999L));
    }

    private void checkPrimeFactors(long n, List<Long> factors) {
        assertThat("Prime factor for " + n + " is " + factors, primeFactorService.findPrimeFactors(n), is(factors));
        assertTrue("Cache contain number " + n, cache.containsKey(n));
        assertThat("Valid prime factor cached result for " + n + " is " + factors, cache.get(n), is(factors));
    }
}
