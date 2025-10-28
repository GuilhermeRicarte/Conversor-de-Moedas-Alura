import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Conversor {
    // tenta ler a chave da variável de ambiente
    private static String API_KEY = System.getenv("1dbeb72a8b7d18fb9bbd4059");
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Se a API key não estiver configurada, pedir ao usuário para informar
        if (API_KEY == null || API_KEY.isBlank()) {
            System.out.print("Digite sua API key (ou configure EXCHANGE_API_KEY): ");
            API_KEY = scanner.next();
        }

        try {
            while (true) {
                System.out.println("\nConversor de Moedas");
                System.out.println("1. Converter moedas");
                System.out.println("2. Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                if (opcao == 2) break;

                if (opcao == 1) {
                    System.out.print("Digite a moeda de origem (ex: USD): ");
                    String moedaOrigem = scanner.next().toUpperCase();

                    System.out.print("Digite a moeda de destino (ex: BRL): ");
                    String moedaDestino = scanner.next().toUpperCase();

                    System.out.print("Digite o valor a ser convertido: ");
                    double valor = scanner.nextDouble();

                    double taxaConversao = obterTaxaConversao(moedaOrigem, moedaDestino);
                    if (taxaConversao > 0) {
                        double resultado = valor * taxaConversao;
                        System.out.printf("%.2f %s = %.2f %s%n", 
                            valor, moedaOrigem, resultado, moedaDestino);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static double obterTaxaConversao(String moedaOrigem, String moedaDestino) {
        try {
            String urlStr = BASE_URL + API_KEY + "/pair/" + moedaOrigem + "/" + moedaDestino;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int status = conn.getResponseCode();
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(status >= 200 && status < 300 ? conn.getInputStream() : conn.getErrorStream())
            );
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            if (status < 200 || status >= 300) {
                System.out.println("Erro da API (HTTP " + status + "): " + response.toString());
                return -1;
            }

            // Extrai o campo conversion_rate da resposta JSON sem usar bibliotecas externas
            Pattern p = Pattern.compile("\\\"conversion_rate\\\"\\s*:\\s*([0-9]+(?:\\.[0-9]+)?)");
            Matcher m = p.matcher(response.toString());
            if (m.find()) {
                return Double.parseDouble(m.group(1));
            } else {
                System.out.println("Formato inesperado da resposta da API: " + response.toString());
                return -1;
            }

        } catch (Exception e) {
            System.out.println("Erro ao obter taxa de conversão: " + e.getMessage());
            return -1;
        }
    }
}
