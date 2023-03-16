package co.ud.hashticket.rest.services.impl;

import co.ud.hashticket.client.EventImagesClient;
import co.ud.hashticket.rest.services.EventImagesService;
import co.ud.ud.hashticket.dto.EventImagesDto;
import co.ud.ud.hashticket.utilities.Base64Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
        Boolean guardar = Base64Utilities.saveFile(eventImages.getBase64(), pathRepo, entity.getId() + ".jpg");
        if(guardar){
            String location = pathRepo + entity.getId() + ".jpg";
            //Update url
            entity.setLocation(location);
            return eventImagesClient.save(entity);
        }
        return null;
    }
}
