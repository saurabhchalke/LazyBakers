package lazybakers.model.repository;

import lazybakers.framework.data.BaseJPARepository;
import lazybakers.model.entity.User;

/**
 *
 * DD Repository for User related actions and events
 *
 * @author: kameshr
 */
public interface UserRepository extends BaseJPARepository<User, Long> {
    /**
     * Finds a user with the given email
     *
     * @param email
     * @return
     */
    public User findByEmail(String email);
    
    public User getUserById(long userId);
    
}
