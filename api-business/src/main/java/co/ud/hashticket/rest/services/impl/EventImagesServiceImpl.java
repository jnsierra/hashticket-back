package co.ud.hashticket.rest.services.impl;

import co.ud.hashticket.client.EventImagesClient;
import co.ud.hashticket.rest.services.EventImagesService;
import co.ud.ud.hashticket.dto.EventImagesDto;
import co.ud.ud.hashticket.utilities.Base64Utilities;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class EventImagesServiceImpl implements EventImagesService {
    private final EventImagesClient eventImagesClient;
    private final String pathRepo;
    @Autowired
    public EventImagesServiceImpl(EventImagesClient eventImagesClient, @Value("${business.pathRepo}") String pathRepo) {
        this.eventImagesClient = eventImagesClient;
        this.pathRepo = pathRepo;
    }
    @Override
    public EventImagesDto save(EventImagesDto eventImages) {
        //Save the images
        EventImagesDto entity = eventImagesClient.save(eventImages);
        boolean keep = Base64Utilities.saveFile(eventImages.getBase64(), pathRepo, entity.getId() + ".jpg");
        if(keep){
            String location = pathRepo + entity.getId() + ".jpg";
            //Update url
            entity.setLocation(location);
            return eventImagesClient.save(entity);
        }
        return null;
    }

    @Override
    public EventImagesDto getById(Long id) {
        EventImagesDto entity = eventImagesClient.getById(id);
        entity.setBase64(getBase64(entity.getLocation()));
        return entity;
    }
    private String getBase64(String location){
        File file = new File(location);
        try {
            byte[] bytes = loadFile(file);
            byte[] encoded = Base64.encodeBase64(bytes);
            return new String(encoded);
        }catch (Exception e){
            return null;
        }

    }
    private static byte[] loadFile(File file) throws IOException {
        try(InputStream is = new FileInputStream(file)){
            long length = file.length();
            if (length > Integer.MAX_VALUE) {
                // File is too large
            }
            byte[] bytes = new byte[(int)length];

            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }

            if (offset < bytes.length) {
                throw new IOException("Could not completely read file "+file.getName());
            }
            return bytes;
        }
    }
}
