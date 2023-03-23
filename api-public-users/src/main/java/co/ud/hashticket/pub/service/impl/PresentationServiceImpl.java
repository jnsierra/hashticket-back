package co.ud.hashticket.pub.service.impl;

import co.ud.hashticket.datos.entity.PresentationEntity;
import co.ud.hashticket.datos.repository.PresentationRepository;
import co.ud.hashticket.pub.service.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PresentationServiceImpl implements PresentationService {
    private final PresentationRepository presentationRepository;
    @Autowired
    public PresentationServiceImpl(PresentationRepository presentationRepository) {
        this.presentationRepository = presentationRepository;
    }
    @Override
    public Set<PresentationEntity> findByEvent(Long eventId) {
        return presentationRepository.findByEvent(eventId);
    }
}