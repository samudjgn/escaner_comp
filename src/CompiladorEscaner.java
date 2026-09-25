import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class CompiladorEscaner {

    public void iniciarEscaneo() {
        // Diccionario de palabras reservadas
        ArrayList<String> palabrasReservadas = new ArrayList<>(Arrays.asList(
                "auto", "break", "case", "char", "const", "continue", "default", "do",
                "double", "else", "enum", "extern", "float", "for", "goto", "if",
                "int", "long", "register", "return", "short", "signed", "sizeof", "static",
                "struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while"
        ));

        try {
            File miArchivo = new File("prueba.c");
            Scanner lector = new Scanner(miArchivo);

            while (lector.hasNextLine()) {
                String linea = lector.nextLine();

                for (int i = 0; i < linea.length(); i++) {
                    char caracterActual = linea.charAt(i);

                    // 1. Lógica para NÚMEROS
                    if (Character.isDigit(caracterActual)) {
                        String numeroAcumulado = "";
                        while (i < linea.length() && Character.isDigit(linea.charAt(i))) {
                            numeroAcumulado += linea.charAt(i);
                            i++;
                        }
                        Token tokenNumero = new Token("INT_NUM", numeroAcumulado);
                        System.out.println(tokenNumero.toString());
                        i--;
                    }
                    // 2. Lógica para LETRAS (Palabras reservadas e Identificadores)
                    else if (Character.isLetter(caracterActual)) {
                        String palabraAcumulada = "";
                        while (i < linea.length() && Character.isLetterOrDigit(linea.charAt(i))) {
                            palabraAcumulada += linea.charAt(i);
                            i++;
                        }

                        // Filtro de categorización
                        if (palabrasReservadas.contains(palabraAcumulada)) {
                            Token tokenReservado = new Token(palabraAcumulada.toUpperCase(), palabraAcumulada);
                            System.out.println(tokenReservado.toString());
                        } else {
                            Token tokenId = new Token("ID", palabraAcumulada);
                            System.out.println(tokenId.toString());
                        }
                        i--;
                    }

                    else if (caracterActual == '<') {

                        //¿Hay un siguiente carácter? Y si lo hay, ¿es un '='?
                        if (i + 1 < linea.length() && linea.charAt(i + 1) == '=') {
                            Token tokenMenorIgual = new Token("LE", "<=");
                            System.out.println(tokenMenorIgual.toString());

                            i++; //Avanzamos el índice para saltarnos el '=' porque ya lo leímos
                        } else {
                            // Si no era un '=', entonces era un '<' solitario
                            Token tokenMenor = new Token("LT", "<");
                            System.out.println(tokenMenor.toString());
                        }
                    }
                }
            }
            lector.close();
        } catch (Exception e) {
            System.out.println("Error: No se pudo abrir el archivo.");
        }
    }
}