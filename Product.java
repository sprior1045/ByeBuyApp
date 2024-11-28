package app;
import app.EthicalDatabase;
import java.util.List;
import java.util.Map;
public class Product {
    private final String barcode;
    private final String category;
    private final String manufacturer;
    private final List<String> ingredients;
    private final Map<String, String> attributes;
    private EthicalDatabase ethicalDatabase;
    // Existing fields
// Constructor
    public Product(String barcode, String category, String manufacturer, List<String> ingredients, Map<String, String> attributes, EthicalDatabase ethicalDatabase) {
        this.barcode = barcode;
        this.category = category;
        this.manufacturer = manufacturer;
        this.ingredients = ingredients;
        this.attributes = attributes;
        this.ethicalDatabase = ethicalDatabase;
    }
    // Getters for other fields
    public String getBarcode() {
        return barcode;
    }
    public String getCategory() {
        return category;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public List<String> getIngredients() {
        return ingredients;
    }
    public Map<String, String> getAttributes() {
        return attributes;
    }
    // Getter for ethical information
    public EthicalDatabase getEthicalDatabase() {
        return ethicalDatabase;
    }
    // Setter for ethical information
    public void setEthicalInfo(EthicalDatabase ethicaldatabase) {
        this.ethicalDatabase = ethicalDatabase;
    }
    @Override
    public String toString() {
        return "Product{" +
                "barcode='" + barcode + '\'' +
                ", category='" + category + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", ingredients=" + ingredients +
                ", attributes=" + attributes +
                ", ethicalInfo=" + ethicalDatabase +
                '}';
    }
}