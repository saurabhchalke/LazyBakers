package lazybakers.model.repository.impl;

import lazybakers.framework.data.BaseHibernateJPARepository;
import lazybakers.model.entity.Category;
import lazybakers.model.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class CategoryRepositoryImpl extends BaseHibernateJPARepository<Category, Long> implements CategoryRepository {
    private static Logger log = LoggerFactory.getLogger(CategoryRepositoryImpl.class);

    @PostConstruct
    public void setUp() {
        log.info("categoryRepository created..!");
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        return (Category) sessionFactory.getCurrentSession().createQuery("from Category c where c.name = :categoryName")
                .setParameter("categoryName", categoryName).uniqueResult();
    }

    @Override
    public Category findByCategoryPriority(Integer categoryPriority) {
        return (Category) sessionFactory.getCurrentSession().createQuery("from Category c where c.priority = :categoryPriority")
                .setParameter("categoryPriority", categoryPriority).uniqueResult();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Category> findSubCategories(Category parentCategory) {
        return (List<Category>) sessionFactory.getCurrentSession().createQuery("from Category c where c.parentCategory = :parentCategory")
                .setParameter("parentCategory", parentCategory).list();
    }
}
