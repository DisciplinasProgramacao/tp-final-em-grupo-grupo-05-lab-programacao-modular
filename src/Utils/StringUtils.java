package Utils;

import java.text.NumberFormat;

public class StringUtils {
    public static String formatarNumeroParaStringEmFormatoDeMoedaBrasileira(double numero) {
        return NumberFormat.getCurrencyInstance().format(numero);
    }
}