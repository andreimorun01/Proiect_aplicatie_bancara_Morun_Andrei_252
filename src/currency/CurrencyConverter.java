package currency;

import files.CSVUtils;

public class CurrencyConverter {
    /**
     * Function that calculates the exchange rate for two currencies
     * @param oldCurrency represents the original currency
     * @param newCurrency represents the wanted currency
     * @return the exchange rate
     */
    public static Float calculateExchangeRate(Currency oldCurrency, Currency newCurrency) {
        CSVUtils.getInstance().appendToCSV("exchange_rate", Thread.currentThread().getName());
        return oldCurrency.getExchangeRate(newCurrency);
    }
}
