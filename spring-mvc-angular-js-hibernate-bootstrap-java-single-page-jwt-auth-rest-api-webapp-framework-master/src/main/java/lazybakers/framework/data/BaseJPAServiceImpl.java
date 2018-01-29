package lazybakers.framework.data;

import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

public abstract class BaseJPAServiceImpl<T extends Entity, I extends Serializable> implements BaseService<T, I> {
    protected BaseJPARepository<T, I> baseJpaRepository; 
    protected Class<T> entityClass;

    public T insert(T object) {
        return baseJpaRepository.insert(object);
    }

    public T update(T object) {
        return baseJpaRepository.update(object);
    }

    public void delete(T object) {
        baseJpaRepository.delete(object);
    }

    public T findById(I id) {
        return baseJpaRepository.findById(id);
    }

    public Collection<T> findAllByPage(int pageNum, int countPerPage, Order order) {
        return Collections.emptyList();  //To change body of implemented methods use File | Settings | File Templates.
    }
}
