
public class CifraCesar {
    private int chave;

    public CifraCesar(int chave) {
        this.chave = chave;
    }

    public String criptografar(String texto) {
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char codificado = (char) ((c - base + chave) % 26 + base);
                resultado.append(codificado);
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    public String descriptografar(String texto) {
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char decodificado = (char) ((c - base - chave + 26) % 26 + base);
                resultado.append(decodificado);
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }
}