package app.prime.controller;

import app.prime.Logged;
import app.prime.WithCache;
import app.prime.model.PrimeList;
import app.prime.model.PrimeResult;
import app.prime.service.PrimeFactorService;
import app.prime.service.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Раскладываем число на простые множители
 */
@RestController
@RequestMapping("/prime")
public class PrimeController {
    @Autowired
    @WithCache
    PrimeService primeService;
    @Autowired
    @WithCache
    PrimeFactorService primeFactorService;

    @Logged
    @RequestMapping(value = "/find/{number}", method = GET)
    public PrimeList findPrimes(@PathVariable("number") long number) {
        List<Long> primes = primeFactorService.findPrimeFactors(number);
        return new PrimeList(primes);
    }

    @Logged
    @RequestMapping(value = "/check/{number}", method = GET)
    public PrimeResult checkPrime(@PathVariable("number") long number) {
        boolean prime = primeService.isPrime(number);
        return new PrimeResult(prime);
    }
}
