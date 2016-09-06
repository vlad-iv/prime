package app.prime.model;

import java.util.List;

/**
 * @author Vladimir Ivanov
 */
public class PrimeResult {
    private boolean result;

    public PrimeResult(final boolean result) {
        this.result = result;
    }
    public boolean isResult() {
        return result;
    }
    public void setResult(final boolean result) {
        this.result = result;
    }
}
