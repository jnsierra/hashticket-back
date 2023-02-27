package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.PresentationEntity;
import co.ud.hashticket.datos.repository.PresentationRepository;
import co.ud.hashticket.datos.service.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PresentationServiceImpl implements PresentationService {
    private final PresentationRepository presentationRepository;
    @Autowired
    public PresentationServiceImpl(PresentationRepository presentationRepository) {
        this.presentationRepository = presentationRepository;
    }
    @Override
    public PresentationEntity save(PresentationEntity presentation) {
        return presentationRepository.save(presentation);
    }

    @Override
    public Optional<PresentationEntity> getById(Long id) {
        return presentationRepository.findById(id);
    }
}