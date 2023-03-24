package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.MusicBandEntity;
import co.ud.hashticket.datos.repository.MusicBandRepository;
import co.ud.hashticket.datos.service.MusicBandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MusicBandServiceImpl implements MusicBandService {
    private final MusicBandRepository musicBandRepository;
    @Autowired
    public MusicBandServiceImpl(MusicBandRepository musicBandRepository) {
        this.musicBandRepository = musicBandRepository;
    }
    @Override
    public MusicBandEntity save(MusicBandEntity musicBand) {
        return musicBandRepository.save(musicBand);
    }
    @Override
    public Optional<MusicBandEntity> getById(Long id) {
        return musicBandRepository.findById(id);
    }
    @Override
    public Set<MusicBandEntity> getAll() {
        return new HashSet<>(musicBandRepository.findAll());
    }
}
