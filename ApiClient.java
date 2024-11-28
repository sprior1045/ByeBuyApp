package app;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
/**
 * Handles API requests to fetch product information using a barcode.
 */
public class ApiClient {
    /**
     * Fetches product information from an external API using a barcode.
     *
     * @param barcode The barcode to look up.
     */
    public static String fetchProductInformation(String barcode) {
        try {
// Fetch the product information from the external API
            String apiUrl = "https://api.barcodelookup.com/v2/products";
            String params = "?barcode=" + barcode + "&formatted=y&key=h5o092xqoa6n5vkt3uymp1jjiog99h";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl + params))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String productInformation = response.body();
                return productInformation;
            } else {
                return null;
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error fetching product information: " + e.getMessage());
            return null;
        }
    }
}