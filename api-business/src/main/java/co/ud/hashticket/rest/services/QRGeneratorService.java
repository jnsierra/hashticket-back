package co.ud.hashticket.rest.services;

public interface QRGeneratorService {
    boolean generateQRCodeImage(String confirmationCode);
}
