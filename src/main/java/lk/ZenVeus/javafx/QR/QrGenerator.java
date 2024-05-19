package lk.ZenVeus.javafx.QR;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class QrGenerator {

    public static boolean generateQRCode(String text) {
        int width = 300;
        int height = 300;

        // get local date and time
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        // time and date set String
        String dateString = String.valueOf(date);
        String timeString = String.valueOf(time);

        boolean isGenerated;

        // set qr save file path
        String filePath = "src/main/resources/QrCodeStore/"+dateString+" -"+timeString+".png";
        try {
            // Encode text content into QR code
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

            // Create buffered image to hold the QR code
            BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            qrImage.createGraphics();

            // Paint and save the QR code to the image
            Graphics2D graphics = (Graphics2D) qrImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (bitMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            // Save QR code image to specified file path
            ImageIO.write(qrImage, "png", new File(filePath));
            System.out.println("QR code generated successfully at: " + filePath);

            isGenerated = true;

        } catch (WriterException | IOException e) {
            System.out.println("Error generating QR code: " + e.getMessage());
            isGenerated = false;
        }
        return isGenerated;
    }
}
