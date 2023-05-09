package co.ud.hashticket.rest.services.impl;

import co.ud.hashticket.rest.services.QRGeneratorService;
import co.ud.ud.hashticket.exception.BusinessException;
import co.ud.ud.hashticket.exception.enumeration.TYPE_EXCEPTION;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.function.BiPredicate;
import java.util.function.Function;

@Service
public class QRGeneratorServiceImpl implements QRGeneratorService {
    @Value("${qr.path}")
    private String pathQRs;
    private QRCodeWriter qRCodeWriter;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private Function<String, BitMatrix> functionBitMatrix = confirmationCode -> {
        try {
            return this.qRCodeWriter.encode(confirmationCode, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
        } catch (WriterException e) {
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Error al crear PNG QR", e);
        }
    };
    private BiPredicate<Path, BitMatrix> isCreated = (path, bitMatrix) -> {
        try {
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (IOException e) {
            return false;
        }
        return true;
    };
    private Function<String, Path> functionGeneratePath = confirmationCode -> FileSystems.getDefault().getPath(String.format("%s%s.png", pathQRs,confirmationCode));
    @Autowired
    public QRGeneratorServiceImpl(QRCodeWriter qRCodeWriter) {
        this.qRCodeWriter = qRCodeWriter;
    }
    @Override
    public boolean generateQRCodeImage(String confirmationCode) {
        return isCreated.test(functionGeneratePath.apply(confirmationCode) ,functionBitMatrix.apply(confirmationCode));
    }
}