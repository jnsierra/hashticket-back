package co.ud.hashticket.datos.service;

import co.ud.hashticket.datos.entity.UserEntity;

public interface UserService {
    UserEntity save(UserEntity userEntity);
    boolean updatePassword(String email, String password);
    boolean deleteUserType(String email);
    boolean addUserType(String email,String userType);
}
