package co.ud.hashticket.pub.service.impl;

import co.ud.hashticket.datos.entity.EventImagesEntity;
import co.ud.hashticket.datos.repository.EventImagesRepository;
import co.ud.hashticket.pub.mapper.EventImagesMapper;
import co.ud.hashticket.pub.service.EventImagesService;
import co.ud.ud.hashticket.dto.EventImagesDto;
import co.ud.ud.hashticket.enumeration.TypeImages;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventImagesServiceImpl implements EventImagesService {

    private final EventImagesRepository eventImagesRepository;
    @Autowired
    public EventImagesServiceImpl(EventImagesRepository eventImagesRepository) {
        this.eventImagesRepository = eventImagesRepository;
    }
    @Override
    public Set<EventImagesDto> findByEventAndTypeImages(Long idEvent, TypeImages typeImages) {
        return eventImagesRepository.findByEventAndTypeImages(idEvent, typeImages)
                .stream()
                .map(this::translate)
                .collect(Collectors.toSet());
    }
    private EventImagesDto translate(EventImagesEntity eventImages){
        EventImagesDto item = EventImagesMapper.INSTANCE.map(eventImages);
        item.setBase64(getBase64(item.getLocation()));
        return item;
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
