package br.com.alura;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ApiConversor {
    private final String apiKey;

    public ApiConversor(String apiKey) {
        this.apiKey = apiKey;
    }

    public double converter(Moeda moedaOrigem, Moeda moedaDestino, double valor) {
        HttpURLConnection con = null;
        BufferedReader in = null;

        try {
            // Formata a URL para a API ExchangeRate
            String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", apiKey, moedaOrigem.getCodigo());
            con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");

            // Verifica se a resposta foi bem-sucedida
            if (con.getResponseCode() != 200) {
                System.err.println("Erro ao conectar com a API. Código de resposta: " + con.getResponseCode());
                return 0;
            }

            // Lê a resposta da API
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(in, JsonObject.class);

            // Pega a taxa de conversão da moeda de destino
            double taxaConversao = jsonResponse.getAsJsonObject("conversion_rates")
                    .get(moedaDestino.getCodigo())
                    .getAsDouble();

            // Retorna o valor convertido
            return valor * taxaConversao;
        } catch (Exception e) {
            System.err.println("Erro ao realizar a conversão: " + e.getMessage());
            return 0;
        } finally {
            try {
                if (in != null) in.close();
                if (con != null) con.disconnect();
            } catch (Exception e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
