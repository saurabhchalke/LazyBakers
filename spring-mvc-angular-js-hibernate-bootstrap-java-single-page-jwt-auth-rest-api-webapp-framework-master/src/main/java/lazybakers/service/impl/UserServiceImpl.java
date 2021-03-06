package lazybakers.service.impl;

import lazybakers.framework.data.BaseJPAServiceImpl;
import lazybakers.model.entity.User;
import lazybakers.model.repository.UserRepository;
import lazybakers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Service impl class to implement services for accessing the User object entity.
 * This class acts as an interface between the outer world and the UserRepository
 *
 */
@Service
@Transactional
public class UserServiceImpl extends BaseJPAServiceImpl<User, Long> implements UserService {
    private @Autowired UserRepository userRepository;

    @PostConstruct
    public void setupService() {
        this.baseJpaRepository = userRepository;
        this.entityClass = User.class;
        this.baseJpaRepository.setupEntityClass(User.class);
    }


    @Override
    public boolean isValidPass(User user, String rawPass) {
        return User.doesPasswordMatch(rawPass, user.getPassword());
    }


    @Override
    public User registerUser(User user, HttpServletRequest request) {
        user.setPassword(User.hashPassword(user.getPassword()));
        user.setCurrentLoginAt(new Date());
        user.setCurrentLoginIp(request.getRemoteHost());
        user.setLoginCount(0);
        return userRepository.insert(user);
    }


    @Override
    public User loginUser(final User user, final HttpServletRequest request) {
        user.setLastLoginAt(user.getCurrentLoginAt());
        user.setLastLoginIp(user.getCurrentLoginIp());
        user.setCurrentLoginAt(new Date());
        user.setCurrentLoginIp(request.getRemoteHost());
        user.setLoginCount(user.getLoginCount() + 1);
        user.setUpdatedAt(new Date());

        return userRepository.update(user);
    }

    @Override
    public boolean isEmailExists(String email) {
    	boolean returnValue = false;
        if (userRepository.findByEmail(email) != null) {
            returnValue = true;
        }
        return returnValue;
    }


    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }


	@Override
	public User getUserById(long userId) {
		return userRepository.getUserById(userId);
	}
}
