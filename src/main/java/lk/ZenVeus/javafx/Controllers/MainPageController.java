package lk.ZenVeus.javafx.Controllers;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.Result;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ZenVeus.javafx.QR.QrCodeScanner;
import lk.ZenVeus.javafx.QR.QrGenerator;

public class MainPageController {
    public TextField generateTxt;
    public TextField scanTxt;

    public void generateBtn(ActionEvent actionEvent) {
        if (!generateTxt.getText().isEmpty()){

            String text = generateTxt.getText(); // get your text

            boolean isGenerated =  QrGenerator.generateQRCode(text);

            if (isGenerated){
                new Alert(Alert.AlertType.INFORMATION,"Qr Generated !").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Qr Not Generated !").show();
            }
        }
    }

    public void scanBtn(ActionEvent actionEvent) throws ChecksumException, FormatException {

        if (scanTxt.getText().isEmpty()){
            String scanText = QrCodeScanner.qrScanner();

            scanTxt.setText(scanText);

        }else {
            new Alert(Alert.AlertType.ERROR,"Clear you scan text field !").show();
        }
    }
}
