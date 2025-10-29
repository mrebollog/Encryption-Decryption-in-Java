package encryptdecrypt;

class ShiftEncoder implements Procesador {
    @Override
    public String procesar(String mensaje, int shift) {
        StringBuilder resultado = new StringBuilder();
        for (char c : mensaje.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char nuevo = (char) ((c - base + shift) % 26 + base);
                resultado.append(nuevo);
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }
}
