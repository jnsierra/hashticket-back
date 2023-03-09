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
            log.info("Llega a la auditoria");
            usuario = SecurityContextHolder.getContext().getAuthentication().getName();
            log.info("Este es el usuario {}", usuario);
        }catch (Exception e){
            usuario = "jnsierrac";
        }
        return Optional.of(usuario);
    }
}
