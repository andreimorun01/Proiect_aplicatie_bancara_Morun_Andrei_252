package currency;

public class USD extends Currency {
    @Override
    public Float getExchangeRate(Currency currency) {
        if (currency instanceof RON) {
            return 4.10f;
        } else if (currency instanceof EUR) {
            return 0.84f;
        } else if (currency instanceof USD) {
            return 1.00f;
        } else {
            return 0.00f;
        }
    }

    @Override
    public String toString() {
        return "USD";
    }
}
