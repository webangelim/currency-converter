import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrencyConverter {
    private static final String API_KEY = "23d5e501417d7e510fbf0e49";
    private final Gson gson;
    private final HttpClient client;

    public CurrencyConverter() {
        this.gson = new Gson();
        this.client = HttpClient.newHttpClient();
    }

    public double getConversionRate(String baseCurrency, String targetCurrency) {
        String apiUrl = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", API_KEY, baseCurrency);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);
            if (!"success".equals(jsonResponse.get("result").getAsString())) {
                System.out.println("Erro na resposta da API: " + jsonResponse.get("error-type").getAsString());
                return -1;
            }
            JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");

            if (conversionRates != null && conversionRates.has(targetCurrency)) {
                return conversionRates.get(targetCurrency).getAsDouble();
            } else {
                System.out.println("Taxa de conversão não disponível para " + targetCurrency);
                return -1;
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao acessar a API: " + e.getMessage());
            return -1;
        }
    }

    public double convert(double amount, double conversionRate) {
        return amount * conversionRate;
    }

    public void logConversion(String baseCurrency, String targetCurrency, double amount, double convertedAmount) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);

        String logMessage = String.format("%s: %f %s é igual a %f %s\n", formattedDateTime, amount, baseCurrency, convertedAmount, targetCurrency);

        try (FileWriter writer = new FileWriter("conversion_log.txt", true)) {
            writer.write(logMessage);
        } catch (IOException e) {
            System.out.println("Erro ao gravar no arquivo de log: " + e.getMessage());
        }
    }
}
