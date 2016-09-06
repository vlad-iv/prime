package app.prime.service;

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
    public List<Long> findPrimeDivides(final long number) {

        final List<Long> primes = new ArrayList<Long>();

        long divide = number;
        for (long l = 2; l <= number; l++) {
            if (!isPrime(l)) {
                continue;
            }

            while (divide % l == 0) {
                divide /= l;
                primes.add(l);
            }
            if (divide == 1) {
                break;
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
