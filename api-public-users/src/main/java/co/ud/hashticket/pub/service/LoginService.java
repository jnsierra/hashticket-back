package co.ud.hashticket.pub.service;

import co.ud.ud.hashticket.enumeration.LOGIN_ACTION;

public interface LoginService {
    LOGIN_ACTION validaLogin(String email, String pass);
}
