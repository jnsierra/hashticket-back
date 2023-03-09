package co.ud.hashticket.datos.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String usuario = "";
        try {
            usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        }catch (Exception e){
            usuario = "publico";
        }
        return Optional.of(usuario);
    }
}
