package app;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import com.google.gson.Gson;
import javafx.stage.Stage;

import java.io.File;

public class MainController {

    @FXML
    private TextArea productDetailsTextArea;

    @FXML
    private TextArea ethicalDetailsTextArea;

    @FXML
    private Button scanBarcodeButton;

    private EthicalDatabase ethicalDatabase;

    public MainController() {
        this.ethicalDatabase = new EthicalDatabase();
    }

    @FXML
    private void handleScanBarcode() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Barcode Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            String barcode = BarcodeScanner.scanBarcode(file.getAbsolutePath());
            if (barcode != null) {
                processBarcode(barcode);
            } else {
                productDetailsTextArea.setText("Failed to decode the barcode.");
            }
        } else {
            productDetailsTextArea.setText("No file selected.");
        }
    }

    private void processBarcode(String barcode) {
        String productInfo = ApiClient.fetchProductInformation(barcode);

        if (productInfo != null) {
            Product product = parseProductInformation(productInfo);
            enrichWithEthicalData(product);
            updateUI(product);
        } else {
            productDetailsTextArea.setText("No product information found for the scanned barcode.");
        }
    }

    private Product parseProductInformation(String productInfo) {
        return new Gson().fromJson(productInfo, Product.class);
    }

    private void enrichWithEthicalData(Product product) {
        String manufacturer = product.getManufacturer();
        String ethicalInfo = ethicalDatabase.getCompanyEthics(manufacturer);
        product.setEthicalInfo(ethicalDatabase);
    }

    private void updateUI(Product product) {
        productDetailsTextArea.setText(product.toString());
        ethicalDetailsTextArea.setText(
                ethicalDatabase.getCompanyEthics(product.getManufacturer())
        );
    }
}

