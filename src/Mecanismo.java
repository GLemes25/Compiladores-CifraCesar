
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Mecanismo {

    private BufferedReader reader;
    private ArrayList<String> arq;
    private int chave;

    public void executarCripto(String arquivoTexto, String arquivoCripto) {
        LerArquivo(arquivoTexto);
        criptografarArquivo(arquivoTexto, arquivoCripto);

    }

    public void executarDecripto(String entradaCripto, String saidaDecripto) {
        LerArquivo(entradaCripto);
        descriptografarArquivo(entradaCripto, saidaDecripto);

    }

    public void LerArquivo(String caminhoArquivo) {
        System.out.println("----------------------------------------");
        System.out.println("##### Carregar Arquivo De Entrada #####");
        this.CarregarBufferPrimario(caminhoArquivo);
    }

    private void CarregarBufferPrimario(String caminhoArquivo) {
        this.reader = null;
        try {
            this.reader = new BufferedReader(new FileReader(caminhoArquivo));
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        ProcessarBufferPrimario();
    }

    public void ProcessarBufferPrimario() {
        this.arq = new ArrayList<>();
        try {
            String linha;
            while ((linha = this.reader.readLine()) != null) {
                arq.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo: " + e.getMessage());
                }
            }
        }
        ImprimirArquivo();
    }

    public void ImprimirArquivo() {
        System.out.println("----------------------------------------");
        System.out.println("##### Conteúdo do Arquivo: #####");
        for (String texto : this.arq) {
            System.out.println(texto);
        }
        System.out.println("----------------------------------------");
    }

    public void EscreverArquivo(String caminhoArquivo, ArrayList<String> conteudo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (String linha : conteudo) {
                writer.write(linha);
                writer.newLine();
            }
            System.out.println("Arquivo salvo com sucesso: " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public void criptografarArquivo(String arquivoEntrada, String arquivoSaida) {

        chave = Integer
                .parseInt(System.console().readLine("Digite a chave de criptografia (número de posições): "));
        CifraCesar cifra = new CifraCesar(chave);

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoEntrada))) {
            StringBuilder texto = new StringBuilder();
            int caractere;

            while ((caractere = leitor.read()) != -1) {
                texto.append((char) caractere);
            }

            String textoCriptografado = cifra.criptografar(texto.toString());

            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoSaida))) {
                escritor.write(textoCriptografado);
            }

            System.out.println("Arquivo criptografado com sucesso: " + arquivoSaida);

        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }

    public void descriptografarArquivo(String arquivoEntrada, String arquivoSaida) {

        CifraCesar cifra = new CifraCesar(-chave);

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoEntrada))) {
            StringBuilder texto = new StringBuilder();
            int caractere;

            while ((caractere = leitor.read()) != -1) {
                texto.append((char) caractere);
            }

            String textoCriptografado = cifra.criptografar(texto.toString());

            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoSaida))) {
                escritor.write(textoCriptografado);
            }

            System.out.println("Arquivo criptografado com sucesso: " + arquivoSaida);

        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }

}
