package lk.ZenVeus.javafx.QR;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class QrCodeScanner {

    public static String qrScanner() throws ChecksumException, FormatException {
        Webcam webcam = Webcam.getDefault();

        String totalResult = null;

        // Create a JFrame for webcam display
        JFrame frame = new JFrame("QR Code Scanner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        // Create a panel to display webcam feed
        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setMirrored(true);

        // Add webcam panel to the frame
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(630, 480);

        // Start capturing images from the webcam
        webcam.open();

        boolean isOk = true;

        // Continuously scan for QR codes
        while (isOk) {
            BufferedImage image = webcam.getImage();
            if (image != null) {
                try {
                    LuminanceSource source = new BufferedImageLuminanceSource(image);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                    Reader reader = new MultiFormatReader();
                    Result result = reader.decode(bitmap);
                    if (result != null) {
                        System.out.println("QR Code Content: " + result.getText());
                        totalResult = result.getText();
                        isOk=false;
                        return totalResult;
                    }
                } catch (NotFoundException e) {
                }
            }
        }
        return totalResult;
    }
}
