import java.io.File;
import java.util.Scanner;

public class CompiladorEscaner {


    public void iniciarEscaneo() {
    try {

        File miArchivo = new File("prueba.c");

        Scanner lector = new Scanner(miArchivo);

        // Motor central (el ciclo while)
        while (lector.hasNextLine()) {
            String linea = lector.nextLine();

            for (int i = 0; i < linea.length(); i++) {
                char caracterActual = linea.charAt(i);
                if (Character.isDigit(caracterActual)) {
                    String numeroAcumulado = ""; //

                    while (i < linea.length() && Character.isDigit(linea.charAt(i))) {
                        numeroAcumulado += linea.charAt(i); //
                        i++;
                    }

                    Token tokenNumero = new Token("INT_NUM", numeroAcumulado);
                    System.out.println(tokenNumero.toString());

                    i--;
                }
            }
        }

        lector.close();

    } catch (Exception e) {
        System.out.println("Error: No se pudo encontrar o abrir el archivo prueba.c");
    }
}
}