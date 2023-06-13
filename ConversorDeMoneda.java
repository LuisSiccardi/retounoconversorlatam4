import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class ConversorDeMoneda {

    private static final Map<String, BigDecimal> COTACIONES = new HashMap<>();

    static {
    	COTACIONES.put("ARS", new BigDecimal("1"));
        COTACIONES.put("USD", new BigDecimal("0.00203"));
        COTACIONES.put("EUR", new BigDecimal("0.00023"));
        COTACIONES.put("GBP", new BigDecimal("0.00021"));
        COTACIONES.put("JPY", new BigDecimal("0.033"));
        COTACIONES.put("BRL", new BigDecimal("0.0012"));
    }

    public static void main(String[] args) {
        double cantidadDeDinero = obtenerCantidadDeDinero();

        String[] opciones = COTACIONES.keySet().toArray(new String[0]);
        String monedaSeleccionada = obtenerMonedaSeleccionada(opciones);

        BigDecimal resultado = convertirMoneda(cantidadDeDinero, monedaSeleccionada);

        mostrarResultado(resultado, monedaSeleccionada);
    }

    private static double obtenerCantidadDeDinero() {
        while (true) {
            try {
                String dinero = JOptionPane.showInputDialog("Ingrese la cantidad de dinero que desea convertir:");
                return Double.parseDouble(dinero);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido.");
            }
        }
    }

    private static String obtenerMonedaSeleccionada(String[] opciones) {
        return (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una opción de conversión",
                "Menu",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
    }

    private static BigDecimal convertirMoneda(double cantidadDeDinero, String monedaSeleccionada) {
        BigDecimal cotizacion = COTACIONES.get(monedaSeleccionada);
        return BigDecimal.valueOf(cantidadDeDinero).multiply(cotizacion).setScale(2, RoundingMode.HALF_EVEN);
    }

    private static void mostrarResultado(BigDecimal resultado, String monedaSeleccionada) {
        JOptionPane.showMessageDialog(
                null,
                "Tienes " + resultado + " " + monedaSeleccionada,
                "Resultado",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
