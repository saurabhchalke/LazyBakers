package lazybakers.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lazybakers.auth.AuthenticationFailedException;
import lazybakers.auth.JWTTokenAuthFilter;
import lazybakers.framework.api.APIResponse;
import lazybakers.framework.controller.BaseController;
import lazybakers.model.dto.UserDTO;
import lazybakers.model.entity.User;
import lazybakers.service.UserService;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

/**
 * Referred: https://github.com/mpetersen/aes-example, http://niels.nu/blog/2015/json-web-tokens.html
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);

    private @Autowired UserService userService;

    /**
     * Authenticate a user
     *
     * @param userDTO
     * @return
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, headers = {JSON_API_CONTENT_HEADER})
    public @ResponseBody APIResponse authenticate(@RequestBody UserDTO userDTO,
                                                  HttpServletRequest request, HttpServletResponse response) throws AuthenticationFailedException {
        Validate.isTrue(StringUtils.isNotBlank(userDTO.getEmail()), "Email is blank");
        String password = userDTO.getPassword();
        
        log.info("Looking for user by email: "+userDTO.getEmail());
        User user = userService.findByEmail(userDTO.getEmail());

        HashMap<String, Object> authResp = new HashMap<>();
        if(userService.isValidPass(user, password)) {
            log.info("User authenticated: "+user.getEmail());
            userService.loginUser(user, request);
            createAuthResponse(user, authResp);
        } else {
            throw new AuthenticationFailedException("Invalid username/password combination");
        }

        return APIResponse.toOkResponse(authResp);
    }

    /**
     * Register new user
     * POST body expected in the format - {"user":{"displayName":"Display Name", "email":"something@somewhere.com"}}
     *
     * @param userDTO
     * @return
     */
    
    @RequestMapping(value = "/user/{uId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public User getUser(@PathVariable long uId) {
        return userService.getUserById(uId);
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = {JSON_API_CONTENT_HEADER})
    public @ResponseBody APIResponse register(@RequestBody UserDTO userDTO,
                                              HttpServletRequest request) {
        Validate.isTrue(StringUtils.isNotBlank(userDTO.getEmail()), "Email is blank");
        Validate.isTrue(StringUtils.isNotBlank(userDTO.getDisplayName()), "Display name is blank");
        String password = userDTO.getPassword();
        
        log.info("Looking for user by email: " + userDTO.getEmail());
        if(userService.isEmailExists(userDTO.getEmail())) {
            return APIResponse.toErrorResponse("Email is taken");
        }

        log.info("Creating user: " + userDTO.getEmail());
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setDisplayName(userDTO.getDisplayName());
        user.setPassword(password);
        user.setEnabled(true);
        user.setRole(User.Role.USER);
        userService.registerUser(user, request);

        HashMap<String, Object> authResp = new HashMap<>();

        return APIResponse.toOkResponse(authResp);
    }

    private void createAuthResponse(User user, HashMap<String, Object> authResp) {
        String token = Jwts.builder().setSubject(user.getEmail())
                .claim("role", user.getRole().name()).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, JWTTokenAuthFilter.JWT_KEY).compact();
        authResp.put("token", token);
        authResp.put("user", user);
    }

    /**
     * Logs out a user by deleting the session
     *
     * @param userDTO
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public @ResponseBody APIResponse logout(@RequestBody UserDTO userDTO) {
        return APIResponse.toOkResponse("success");
    }

    @SuppressWarnings("unused")
	private String base64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    @SuppressWarnings("unused")
	private byte[] base64(String str) {
        return Base64.decodeBase64(str);
    } 

    @SuppressWarnings("unused")
	private String hex(byte[] bytes) {
        return Hex.encodeHexString(bytes);
    }

    @SuppressWarnings("unused")
	private byte[] hex(String str) {
        try {
            return Hex.decodeHex(str.toCharArray());
        }
        catch (DecoderException e) {
            throw new IllegalStateException(e);
        }
    }
}
