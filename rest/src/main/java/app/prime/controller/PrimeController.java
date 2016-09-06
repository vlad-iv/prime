package app.prime.controller;

import app.prime.model.PrimeList;
import app.prime.model.PrimeResult;
import app.prime.service.PrimeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Раскладываем число на простые множители
 */
@RestController
@RequestMapping("/prime")
public class PrimeController {
    @Autowired
    PrimeService primeService;

    @RequestMapping("/find/{number}")
    public PrimeList findPrimes(@PathVariable("number") long number) {
        List<Long> primes = primeService.findPrimeDivides(number);
        return new PrimeList(primes);
    }

    @RequestMapping("/check/{number}")
    public PrimeResult checkPrime(@PathVariable("number") long number) {
        boolean prime = primeService.isPrime(number);
        return new PrimeResult(prime);
    }
}
