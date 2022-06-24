package Utils;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtils {
    public static String formatarNumeroParaStringEmFormatoDeMoedaBrasileira(double numero) {
        return NumberFormat.getCurrencyInstance().format(numero);
    }

    public static String formatarDataParaDdMmAAAA(LocalDateTime data) {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        return data.format(formatador);
    }
}