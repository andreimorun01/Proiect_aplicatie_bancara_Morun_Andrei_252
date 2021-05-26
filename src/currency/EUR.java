package currency;

public class EUR extends Currency {
    @Override
    public Float getExchangeRate(Currency currency) {
        if (currency instanceof RON) {
            return 4.86f;
        } else if (currency instanceof EUR) {
            return 1.00f;
        } else if (currency instanceof USD) {
            return 1.18f;
        } else {
            return 0.00f;
        }
    }

    @Override
    public String toString() {
        return "EUR";
    }
}
