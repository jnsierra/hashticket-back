package co.ud.ud.hashticket.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
public class Base64Utilities {

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
            System.out.println("Este es el nuevo");
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] decodedByteArray = decoder.decode(base64);
            File file = new File(directoryPath + nameFile );

            OutputStream os = new FileOutputStream(file);
            os.write(decodedByteArray);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    private static String borraTipoBase64(String base64){
        final String IDENTIFICADOR = ";base64,";
        if(base64.contains(IDENTIFICADOR)){
            int find = base64.indexOf(IDENTIFICADOR) + 8;
            base64 = base64.substring(find);
        }
        return base64;
    }
}
