package no.sensor.web.filter;

import com.google.common.base.Optional;
import no.sensor.service.AuthService;
import no.sensor.service.model.auth.AuthException;
import no.sensor.service.model.auth.AuthResponse;
import no.sensor.web.auth.ApiAuthentication;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

public class AuthenticationFilter extends GenericFilterBean {

    private AuthService authService;

    public AuthenticationFilter(AuthService authService) {
        super();
        this.authService = authService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        Optional<String> token = Optional.fromNullable(httpRequest.getHeader("X-Auth-Token"));

        String resourcePath = new UrlPathHelper().getPathWithinApplication(httpRequest);

        try {

            if (token.isPresent()) {
                logger.debug("Trying to authenticate user by X-Auth-Token method. Token: " +  token.toString());
                AuthResponse authResponse = authService.getAuthForToken(token.get());
                if (authResponse.getExpires().after(new Date())) {
                    ApiAuthentication apiAuthentication =
                            new ApiAuthentication(authResponse.getUserName(),
                                    authResponse.getRoles().stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList()));
                    SecurityContextHolder.getContext().setAuthentication(apiAuthentication);
                    logger.debug("AUTH...");
                }
            }

            logger.debug("AuthenticationFilter is passing request down the filter chain");
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
            SecurityContextHolder.clearContext();
            logger.error("Internal authentication service exception", internalAuthenticationServiceException);
            httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (AuthenticationException authenticationException) {
            SecurityContextHolder.clearContext();
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
        } catch (AuthException authException) {
            SecurityContextHolder.clearContext();
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
    }
}
