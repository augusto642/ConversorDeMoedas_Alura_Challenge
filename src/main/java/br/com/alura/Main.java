package br.com.alura;

public class Main {

    public static void main(String[] args) {
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.3");
        String apiKey = "f84cea338a6e69e8276b075b";
        ApiConversor apiConversor = new ApiConversor(apiKey);
        ConversorDeMoedas conversorDeMoedas = new ConversorDeMoedas(apiConversor);
        conversorDeMoedas.iniciar();
    }
}
