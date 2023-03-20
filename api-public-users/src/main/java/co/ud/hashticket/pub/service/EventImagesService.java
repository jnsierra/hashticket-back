package co.ud.hashticket.pub.service;

import co.ud.ud.hashticket.dto.EventImagesDto;
import co.ud.ud.hashticket.enumeration.TypeImages;

import java.util.Set;

public interface EventImagesService {
    Set<EventImagesDto> findByEventAndTypeImages(Long idEvent, TypeImages typeImages);
}
