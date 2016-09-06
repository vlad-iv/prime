package app.prime;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Раскладываем число на простые множители
 * Простая реализация перебором
 */
@Service
public class SimplePrimeService implements PrimeService {
    /**
     * Список простых множителей
     */
    public List<Long> findPrimes(final long number) {

        final List<Long> primes = new ArrayList<Long>();

        for (long l = 1; l <= number; l++) {
            if (isPrime(l)) {
                primes.add(l);
            }
        }
        return primes;
    }

    /**
     * Проверяем на простое число
     */
    public boolean isPrime(final long number) {
        for (long l = 2; l < number; l++) {
            if (number % l == 0) {
                return false;
            }
        }
        return number > 0;
    }
}
