package app.prime.model;

import java.util.List;

/**
 * @author Vladimir Ivanov
 */
public class PrimeList {
    private List<Long> primes;

    public PrimeList() {
    }

    public PrimeList(final List<Long> primes) {
        this.primes = primes;
    }

    public List<Long> getPrimes() {
        return primes;
    }

    public void setPrimes(final List<Long> primes) {
        this.primes = primes;
    }
}
