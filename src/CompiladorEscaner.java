import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class CompiladorEscaner {

    public void iniciarEscaneo() {
        ArrayList<String> palabrasReservadas = new ArrayList<>(Arrays.asList(
                "main", "auto", "break", "case", "char", "const", "continue", "default", "do",
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

                    // 0. Filtro de espacios en blanco
                    if (Character.isWhitespace(caracterActual)) {
                        continue;
                    }

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
                        while (i < linea.length() && (Character.isLetterOrDigit(linea.charAt(i)) || linea.charAt(i) == '_')) {
                            palabraAcumulada += linea.charAt(i);
                            i++;
                        }

                        if (palabrasReservadas.contains(palabraAcumulada)) {
                            Token tokenReservado = new Token(palabraAcumulada.toUpperCase(), palabraAcumulada);
                            System.out.println(tokenReservado.toString());
                        } else {
                            Token tokenId = new Token("ID", palabraAcumulada);
                            System.out.println(tokenId.toString());
                        }
                        i--;
                    }

                    // 3. Operadores de Desplazamiento y Relacionales (<, <=, <<)
                    else if (caracterActual == '<') {
                        if (i + 1 < linea.length() && linea.charAt(i + 1) == '=') {
                            System.out.println(new Token("OP_REL", "<=").toString());
                            i++;
                        } else if (i + 1 < linea.length() && linea.charAt(i + 1) == '<') {
                            System.out.println(new Token("OP_BITS", "<<").toString());
                            i++;
                        } else {
                            System.out.println(new Token("OP_REL", "<").toString());
                        }
                    }

                    // 4. Operadores de Desplazamiento y Relacionales (>, >=, >>)
                    else if (caracterActual == '>') {
                        if (i + 1 < linea.length() && linea.charAt(i + 1) == '=') {
                            System.out.println(new Token("OP_REL", ">=").toString());
                            i++;
                        } else if (i + 1 < linea.length() && linea.charAt(i + 1) == '>') {
                            System.out.println(new Token("OP_BITS", ">>").toString());
                            i++;
                        } else {
                            System.out.println(new Token("OP_REL", ">").toString());
                        }
                    }

                    // 5. Asignación e Igualdad (=, ==)
                    else if (caracterActual == '=') {
                        if (i + 1 < linea.length() && linea.charAt(i + 1) == '=') {
                            System.out.println(new Token("OP_REL", "==").toString());
                            i++;
                        } else {
                            System.out.println(new Token("ASSIGN", "=").toString());
                        }
                    }

                    // 6. Negación y Desigualdad (!, !=)
                    else if (caracterActual == '!') {
                        if (i + 1 < linea.length() && linea.charAt(i + 1) == '=') {
                            System.out.println(new Token("OP_REL", "!=").toString());
                            i++;
                        } else {
                            System.out.println(new Token("OP_LOGIC", "!").toString());
                        }
                    }

                    // 7. AND Lógico y de Bits (&, &&)
                    else if (caracterActual == '&') {
                        if (i + 1 < linea.length() && linea.charAt(i + 1) == '&') {
                            System.out.println(new Token("OP_LOGIC", "&&").toString());
                            i++;
                        } else {
                            System.out.println(new Token("OP_BITS", "&").toString());
                        }
                    }

                    // 8. OR Lógico y de Bits (|, ||)
                    else if (caracterActual == '|') {
                        if (i + 1 < linea.length() && linea.charAt(i + 1) == '|') {
                            System.out.println(new Token("OP_LOGIC", "||").toString());
                            i++;
                        } else {
                            System.out.println(new Token("OP_BITS", "|").toString());
                        }
                    }

                    // 9. XOR y NOT de Bits (^, ~)
                    else if (caracterActual == '^') {
                        System.out.println(new Token("OP_BITS", "^").toString());
                    }
                    else if (caracterActual == '~') {
                        System.out.println(new Token("OP_BITS", "~").toString());
                    }

                    // 10. Operadores de Agrupación ([ ], ( ), { })
                    else if (caracterActual == '[') { System.out.println(new Token("LBRACKET", "[").toString()); }
                    else if (caracterActual == ']') { System.out.println(new Token("RBRACKET", "]").toString()); }
                    else if (caracterActual == '(') { System.out.println(new Token("LPAR", "(").toString()); }
                    else if (caracterActual == ')') { System.out.println(new Token("RPAR", ")").toString()); }
                    else if (caracterActual == '{') { System.out.println(new Token("LBRACE", "{").toString()); }
                    else if (caracterActual == '}') { System.out.println(new Token("RBRACE", "}").toString()); }

                    // 11. Otros Operadores (., ->, ,)
                    else if (caracterActual == '.') {
                        System.out.println(new Token("PUNTO", ".").toString());
                    }
                    else if (caracterActual == ',') {
                        System.out.println(new Token("COMA", ",").toString());
                    }
                    else if (caracterActual == '-') {
                        if (i + 1 < linea.length() && linea.charAt(i + 1) == '>') {
                            System.out.println(new Token("FLECHA", "->").toString());
                            i++;
                        } else {
                            System.out.println(new Token("MINUS", "-").toString());
                        }
                    }
                    else if (caracterActual == ';') {
                        System.out.println(new Token("SEMI", ";").toString());
                    }
                    else if (caracterActual == '+') {
                        System.out.println(new Token("PLUS", "+").toString());
                    }

                    // 12. Caracteres de Escape
                    else if (caracterActual == '\\') {
                        if (i + 1 < linea.length()) {
                            char siguiente = linea.charAt(i + 1);
                            if ("n0rt\\vf'\"a".indexOf(siguiente) != -1) {
                                System.out.println(new Token("ESCAPE", "\\" + siguiente).toString());
                                i++;
                            }
                        }
                    }

                    // 13. Cadenas de Texto (" ")
                    else if (caracterActual == '"') {
                        String cadena = "\"";
                        i++;
                        while (i < linea.length() && linea.charAt(i) != '"') {
                            cadena += linea.charAt(i);
                            i++;
                        }
                        if (i < linea.length()) cadena += '"';
                        System.out.println(new Token("STRING", cadena).toString());
                    }
                }
            }
            lector.close();
        } catch (Exception e) {
            System.out.println("Error: No se pudo abrir el archivo.");
        }
    }
}