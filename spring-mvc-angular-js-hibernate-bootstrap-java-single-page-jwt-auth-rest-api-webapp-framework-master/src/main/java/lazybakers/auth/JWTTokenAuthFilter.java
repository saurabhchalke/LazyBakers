package lazybakers.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lazybakers.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class JWTTokenAuthFilter extends OncePerRequestFilter {
    private static List<Pattern> authRoutes = new ArrayList<>();
    private static List<String> noAuthRoutes = new ArrayList<>();
    public static final String JWT_KEY = "JWT-TOKEN-SECRET";

    static {
        authRoutes.add(Pattern.compile("/api/*"));
        noAuthRoutes.add("/api/user/authenticate");
        noAuthRoutes.add("/api/user/register");
    }

    @SuppressWarnings("unused")
	@Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("authorization");
        String authenticationHeader = request.getHeader("authentication");
        String route = request.getRequestURI();

        boolean needsAuthentication = false;

        for (Pattern p : authRoutes) {
            if (p.matcher(route).matches()) {
                needsAuthentication = true;
                break;
            }
        }

        if(route.startsWith("/api/")) {
            needsAuthentication = true;
        }

        if (noAuthRoutes.contains(route)) {
            needsAuthentication = false;
        }

        // Checking whether the current route needs to be authenticated
        if (needsAuthentication) {
        	
        	reduceComplexity(authorizationHeader, authenticationHeader, filterChain, request, response);
            
        } else {
            filterChain.doFilter(request, response);
        }
    }
    
    void reduceComplexity(String authorizationHeader, String authenticationHeader, FilterChain filterChain, HttpServletRequest request, HttpServletResponse response) throws AuthCredentialsMissingException, AuthenticationFailedException {
    	
    	// Check for authorization header presence
        String authHeader = null;
        if (authorizationHeader == null || authorizationHeader.equalsIgnoreCase("")) {
            if (authenticationHeader == null || authenticationHeader.equalsIgnoreCase("")) {
                authHeader = null;
            } else {
                authHeader = authenticationHeader;
            }
        } else {
            authHeader = authorizationHeader;
        }

        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith("Bearer ")) {
            throw new AuthCredentialsMissingException("Missing or invalid Authorization header.");
        }

        final String token = authHeader.substring(7); // The part after "Bearer"
        try {
            final Claims claims = Jwts.parser().setSigningKey(JWT_KEY)
                    .parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);

            // Now since the authentication process if finished
            // move the request forward
            filterChain.doFilter(request, response);
        } catch (final Exception e) {
            throw new AuthenticationFailedException("Invalid token. Cause:"+e.getMessage());
        }
    	
    }
}