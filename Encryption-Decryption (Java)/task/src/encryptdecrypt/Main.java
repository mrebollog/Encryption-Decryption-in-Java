package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Argumentos informacion = new Argumentos();
        parsearArgumentos(args, informacion);

        String mensaje = obtenerMensaje(informacion.data, informacion.inputFile);
        Procesador procesador = ProcesadorFactory.crear(informacion.algoritmo, informacion.mode.equals("enc"));
        String resultado = procesador.procesar(mensaje, informacion.key);
        escribirResultado(resultado, informacion.outputFile);

    }

    private static void parsearArgumentos(String[] args, Argumentos argumentos) {
        for (int i = 0; i < args.length - 1; i++) {
            switch (args[i]) {
                case "-mode": argumentos.mode = args[i + 1]; break;
                case "-key": argumentos.key = Integer.parseInt(args[i + 1]); break;
                case "-data": argumentos.data = args[i + 1]; break;
                case "-in": argumentos.inputFile = args[i + 1]; break;
                case "-out": argumentos.outputFile = args[i + 1]; break;
                case "-alg": argumentos.algoritmo = args[i + 1]; break;
            }
        }
    }

    private static String obtenerMensaje(String data, String fileInput) {
        if (!data.isEmpty()) {
            return data;
        }

        if (!fileInput.isEmpty()) {
            try (Scanner scanner = new Scanner(new File(fileInput))) {
                if (scanner.hasNextLine()) {
                    return scanner.nextLine();
                }
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        }

        return "";
    }

    private static void escribirResultado(String resultado, String fileOutput) {
        if (!fileOutput.isEmpty()) {
            try (FileWriter writer = new FileWriter(fileOutput)) {
                writer.write(resultado);
            } catch (IOException e) {
                System.out.println("Error al escribir el archivo: " + e.getMessage());
            }
        } else {
            System.out.println(resultado);
        }
    }

    private static class Argumentos {
        public String mode = "enc";
        public int key = 0;
        public String data = "";
        public String inputFile = "";
        public String outputFile = "";
        public String algoritmo = "shift";
    }
}

