package co.ud.ud.hashticket.utilities;

import co.ud.ud.hashticket.enumeration.TYPE_FILES;
import co.ud.ud.hashticket.exception.BusinessException;
import co.ud.ud.hashticket.exception.enumeration.TYPE_EXCEPTION;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
public class Base64Utilities {
    private Base64Utilities() {
    }

    private static final Logger logger = LogManager.getLogger(Base64Utilities.class);

    public static Boolean saveFile(String base64, String directoryPath, String nameFile){
        Path path = Paths.get(directoryPath);
        boolean isDir = Files.isDirectory(path);
        if(!isDir){
            logger.error("NOT_EXISTS_DIRECTORY|{}|{}|{}","SAVE_FILE", directoryPath, nameFile);
            return Boolean.FALSE;
        }
        try {
            base64 = borraTipoBase64(base64);
            writeFile(directoryPath + nameFile, base64);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private static void writeFile(String fileName, String base64) throws IOException {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(base64);
        File file = new File(fileName);
        try(OutputStream os = new FileOutputStream(file)){
            os.write(decodedByteArray);
        }
    }
    private static String borraTipoBase64(String base64){
        final String IDENTIFICADOR = ";base64,";
        if(base64.contains(IDENTIFICADOR)){
            int find = base64.indexOf(IDENTIFICADOR) + 8;
            base64 = base64.substring(find);
        }
        return base64;
    }
    public static String convertFileToBase64(String filePath, TYPE_FILES typeFiles){
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(new File(filePath));
        } catch (IOException e) {
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR,"Error al convertir a base64", e);
        }
        return generateDataType(typeFiles) + Base64.getEncoder().encodeToString(fileContent);
    }
    public static String generateDataType(TYPE_FILES typeFiles){
        return switch (typeFiles){
            case PDF -> "data:image/pdf;base64,";
            case PNG -> "data:image/png;base64,";
        };
    }
}
