package co.ud.hashticket.pub.service.impl;

import co.ud.hashticket.datos.entity.UserTypeEntity;
import co.ud.hashticket.datos.repository.UserTypeRepository;
import co.ud.hashticket.pub.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    private UserTypeRepository userTypeRepository;
    @Autowired
    public UserTypeServiceImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }
    @Override
    public Optional<UserTypeEntity> findByType(String type) {
        UserTypeEntity typeObj = userTypeRepository.findByType(type);
        return Objects.isNull(typeObj) ? Optional.empty() : Optional.of(typeObj);
    }
}