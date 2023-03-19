package co.ud.hashticket.security.filter;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtFilter extends OncePerRequestFilter {
    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";
    private static final String SECRET = "Aqt&fNpb9^0i*Xs!94v2v3Ijrp5T0vVQy6wussLv$Bw9$p%9rpW0Yb9rI&5R0yDRI8J25lt^*iSoK@1b8vSz3dy1r5sx#GLFC$tq";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            if (existeJWTToken(request)) {
                Claims claims = validateToken(request);
                if (claims.get("authorities") != null) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            }
            chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }
    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        return Jwts
                .parser()
                .setSigningKey(TextCodec.BASE64.encode(SECRET))
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    /**
     * Metodo para autenticarnos dentro del flujo de Spring
     *
     * @param claims
     */
    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List<String>) claims.get("authorities");

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                authorities.stream().map(SimpleGrantedAuthority::new).toList());
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    private boolean existeJWTToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER);
        return (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX));
    }
}
