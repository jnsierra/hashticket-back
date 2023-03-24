package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.ArtistEntity;
import co.ud.hashticket.datos.repository.ArtistRepository;
import co.ud.hashticket.datos.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }
    @Override
    public ArtistEntity save(ArtistEntity entity) {
        return artistRepository.save(entity);
    }
    @Override
    public Optional<ArtistEntity> getById(Long id) {
        return artistRepository.findById(id);
    }
    @Override
    public Set<ArtistEntity> getAll() {
        return new HashSet<>(artistRepository.findAll());
    }
}