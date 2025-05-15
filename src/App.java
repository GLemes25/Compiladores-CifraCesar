
public class App {
    public static void main(String[] args) {

        Mecanismo mec = new Mecanismo();

        String entradaTexto = "src/texto/entradaTexto.txt";
        String saidaCripto = "src/texto/saidaCripto.txt";
        mec.executarCripto(entradaTexto, saidaCripto);

        String saidaDecripto = "src/texto/saidaDecripto.txt";
        mec.executarDecripto(saidaCripto, saidaDecripto);

    }
}
