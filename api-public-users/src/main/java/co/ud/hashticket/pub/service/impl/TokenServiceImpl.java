package co.ud.hashticket.pub.service.impl;

import co.ud.hashticket.datos.entity.UserEntity;
import co.ud.hashticket.datos.repository.UserRepository;
import co.ud.hashticket.pub.service.TokenService;
import co.ud.ud.hashticket.dto.TokenDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TokenServiceImpl implements TokenService {
    private final UserRepository userRepository;
    public final Integer seconds = Integer.valueOf("3600") ;
    private static final String SECRET = "Aqt&fNpb9^0i*Xs!94v2v3Ijrp5T0vVQy6wussLv$Bw9$p%9rpW0Yb9rI&5R0yDRI8J25lt^*iSoK@1b8vSz3dy1r5sx#GLFC$tq";
    @Autowired
    public TokenServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Optional<TokenDto> generateTokenUser(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            String jwt = "";
            Instant now = Instant.now();


            jwt = Jwts.builder().setSubject(email)
                    .claim("authorities",
                            getRolesByUser(user.get()).stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plus(seconds, ChronoUnit.SECONDS)))
                    .signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.encode(SECRET)).compact();

            return Optional.of(TokenDto.builder()
                    .token(jwt)
                    .mensaje("Autenticacion exitosa")
                    .time(seconds)
                    .build());
        }
        return Optional.empty();
    }
    private List<GrantedAuthority> getRolesByUser(UserEntity user){
        Optional<String> roleStr = user.getUserTypes().stream().map(item -> item.getType())
                .reduce( (accumulator, type) ->  accumulator.concat(",".concat(type) ));
        List<GrantedAuthority> grantedAuthorities;
        if(roleStr.isPresent()) {
            grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roleStr.get());
        }else{
            grantedAuthorities = new ArrayList<>();
        }
        return grantedAuthorities;
    }
}
