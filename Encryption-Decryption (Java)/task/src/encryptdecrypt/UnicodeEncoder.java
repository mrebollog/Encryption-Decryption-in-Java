package encryptdecrypt;

class UnicodeEncoder implements Procesador {
    @Override
    public String procesar(String mensaje, int shift) {
        StringBuilder resultado = new StringBuilder();
        for (char c : mensaje.toCharArray()) {
            resultado.append((char) (c + shift));
        }
        return resultado.toString();
    }
}
