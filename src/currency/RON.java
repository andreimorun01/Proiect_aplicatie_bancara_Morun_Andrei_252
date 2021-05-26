package currency;

public class RON extends Currency {
    @Override
    public Float getExchangeRate(Currency currency) {
        if (currency instanceof RON) {
            return 1.00f;
        } else if (currency instanceof EUR) {
            return 0.21f;
        } else if (currency instanceof USD) {
            return 0.24f;
        } else {
            return 0.00f;
        }
    }

    @Override
    public String toString() {
        return "RON";
    }
}
