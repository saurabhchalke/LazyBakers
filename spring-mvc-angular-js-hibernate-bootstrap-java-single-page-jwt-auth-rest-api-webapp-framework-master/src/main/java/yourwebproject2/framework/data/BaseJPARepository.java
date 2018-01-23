package yourwebproject2.framework.data;

import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.Collection;

/**
 * Generic JPA Repository protocol with common CRUD operations and some specific find operations
 *
 * Created by Y.Kamesh on 8/2/2015.
 */
public interface BaseJPARepository<T extends Entity, ID extends Serializable> {
    /**
     * Method to setup the class type of the Entity for which
     * the DAO works
     *
     * @param clazz
     */
    public void setupEntityClass(Class clazz);

    /**
     * Method to insert the new row into config.database table
     *
     * @param object
     *         The object entity to be persisted
     */
    public T insert(T object);

    /**
     * Method to update an existing row in the config.database table
     *
     * @param object
     *         The object entity to be updated
     */
    public T update(T object);

    /**
     * Method to insert a new row or update a row if it was
     * already existing in the system.
     *
     * @param object
     *         The object entity to be updated
     */
    public T insertOrUpdate(T object);

    /**
     * Method to delete an existing row in the config.database table
     *
     * @param object
     *         The object entity to be deleted
     */
    public void delete(T object);

    /**
     * Method to find a database item by id
     *
     * @param id
     *         The id by which the row has to be found
     */
    public T findById(ID id);

    /**
     * Method to find a collection of database entities by pages
     *
     * @param pageNum
     * @param countPerPage
     * @param order
     *
     * @return
     *
     * @throws Exception
     */
    public Collection<T> findAllByPage(int pageNum, int countPerPage, Order order);
}
