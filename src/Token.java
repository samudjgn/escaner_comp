public class Token {
    private String tipo;
    private String valor;

    // Constructor
    public Token(String tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    // Getters
    public String getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    // Sobreescribimos el método toString para que imprima exactamente como pide el PDF
    @Override
    public String toString() {
        return "Token: " + tipo + " \"" + valor + "\"";
    }
}