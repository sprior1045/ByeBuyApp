package app;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Manages ethical information about companies.
 */
public class EthicalDatabase {
    private Map<String, String> ethicalInfo = new HashMap<>();
    private Map<String, String> sources = new HashMap<>();
    public EthicalDatabase() {
        loadEthicalInfoFromCSV();
        loadEthicalInfoFromOpenCorporatesAPI();
    }
    private void loadEthicalInfoFromCSV() {
        File csvFile = new File("C:/Users/Ella/Downloads/mergedCSV.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String company = parts[0];
                String pros = parts[1];
                String cons = parts[2];
                String source = parts[3];
                addEthicalInfo(company, pros, cons, source);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadEthicalInfoFromOpenCorporatesAPI() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.opencorporates.com/companies/search")
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
// Parse the response body to extract the company information
// and add it to the ethicalInfo and sources maps
// ...
            } else {
                System.out.println("Failed to load data from OpenCorporates API");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void addEthicalInfo(String company, String pros, String cons, String source) {
        String ethicalInfoString = "Pros: " + pros + ". Cons: " + cons;
        ethicalInfo.put(company, ethicalInfoString);
        sources.put(company, source);
    }
    public void addCompanyEthics(String company, String pros, String cons, String source) {
        addEthicalInfo(company, pros, cons, source);
    }
    public String getCompanyEthics(String company) {
        return getEthicalInfo(company);
    }
    public String getSource(String company) {
        return sources.get(company);
    }
    /**
     * Displays ethical information for a given company.
     *
     * @param company The company to look up.
     */
    public void displayEthicalInfo(String company) {
        if (ethicalInfo.containsKey(company)) {
            System.out.println("Ethical Information for " + company + ":");
            System.out.println(ethicalInfo.get(company));
            System.out.println("Source: " + sources.get(company));
        } else {
            System.out.println("No ethical information found for " + company + ".");
        }
    }
    public String getEthicalInfo(String company) {
        return ethicalInfo.get(company);
    }
}
