package app;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BarcodeScanner {

    public static String scanBarcode(String filePath) {
        try {
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);

            if (image == null) {
                System.out.println("Unable to read image file.");
                return null;
            }

            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            Result result = new MultiFormatReader().decode(bitmap);

            return result.getText();
        } catch (NotFoundException e) {
            System.out.println("Barcode not found in the image.");
        } catch (Exception e) {
            System.out.println("Error decoding barcode: " + e.getMessage());
        }
        return null;
    }
}

