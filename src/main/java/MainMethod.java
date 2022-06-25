import myPack.Currency;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class MainMethod {
    static int i = 0;
    private static final DecimalFormat format = new DecimalFormat("0.00");


    public static void main(String[] args) {
        String s = "hello";
        try {
            double d = Double.parseDouble(String.valueOf(s));
            System.out.println(d);
        } catch (NumberFormatException e) {
            System.out.println("enter number please");
        }
    }


    public static String dollarToSom(List<Currency> currencies, double sum) {
        double changedSum = 0;

        for (Currency currency : currencies) {
            if (currency.getCcy().equalsIgnoreCase("usd")) {
                changedSum = sum * Double.parseDouble(currency.getRate());

                format.setRoundingMode(RoundingMode.UP);
                return String.valueOf(format.format(changedSum));
            }
        }
        return "";
    }

    public static String somToDollar(List<Currency> currencies, double sum) {
        double changedSum = 0;

        for (Currency currency : currencies) {
            if (currency.getCcy().equalsIgnoreCase("usd")) {
                changedSum = sum / Double.parseDouble(currency.getRate());

                return String.valueOf(changedSum);
            }
        }
        return "";
    }

    public static String euroToSom(List<Currency> currencies, double sum) {
        double changedSum = 0;

        for (Currency currency : currencies) {
            if (currency.getCcy().equalsIgnoreCase("eur")) {

                changedSum = sum * Double.parseDouble(currency.getRate());

                format.setRoundingMode(RoundingMode.UP);
                return String.valueOf(format.format(changedSum));
            }
        }
        return "";
    }

    public static String somToEuro(List<Currency> currencies, double sum) {
        double changedSum = 0;

        for (Currency currency : currencies) {
            if (currency.getCcy().equalsIgnoreCase("eur")) {
                changedSum = sum / Double.parseDouble(currency.getRate());
                return String.valueOf(changedSum);
            }
        }
        return "";
    }

    public static String yuanToSom(List<Currency> currencies, double sum) {
        double changedSum = 0;

        for (Currency currency : currencies) {
            if (currency.getCcy().equalsIgnoreCase("cny")) {
                changedSum = sum * Double.parseDouble(currency.getRate());

                format.setRoundingMode(RoundingMode.UP);
                return String.valueOf(format.format(changedSum));
            }
        }
        return "";
    }

    public static String somToYuan(List<Currency> currencies, double sum) {
        double changedSum = 0;

        for (Currency currency : currencies) {
            if (currency.getCcy().equalsIgnoreCase("cny")) {
                changedSum = sum / Double.parseDouble(currency.getRate());
                return String.valueOf(changedSum);
            }
        }
        return "";
    }


}