package org.example;
import java.io.IOException;
import java.text.DecimalFormat;

public class Calculator{
    public static String[] calculations(String text, String amountValue,String crypto) throws IOException {

        double mc = CryptoValueFetcher.getCryptoValue(crypto);
        DecimalFormat decimalFormat = new DecimalFormat("#.#####");
        int index = text.indexOf("%");
        String remover = text.substring(0, index);
        Double numeroFinal = Double.parseDouble(remover)/100;
        int amountValue2 = Integer.parseInt(amountValue);
        Double ganhoAnual = numeroFinal * amountValue2;
        String weeklyvalue = decimalFormat.format(ganhoAnual/52);
        String annualValue = decimalFormat.format(ganhoAnual);
        String annualADA = decimalFormat.format(ganhoAnual/mc);
        String mValue = String.valueOf(mc)+ " $";

        return new String[]{annualValue, weeklyvalue, annualADA, mValue};
    }
}
