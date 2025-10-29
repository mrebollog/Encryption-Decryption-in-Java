package encryptdecrypt;

class ProcesadorFactory {
    public static Procesador crear(String algoritmo, boolean esCifrado) {
        String tipo = algoritmo.toLowerCase();

        if ("unicode".equals(tipo)) {
            return esCifrado ? new UnicodeEncoder() : new UnicodeDecoder();
        }
        return esCifrado ? new ShiftEncoder() : new ShiftDecoder();
    }
}
