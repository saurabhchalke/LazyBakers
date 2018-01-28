package lazybakers.service;

import lazybakers.framework.data.BaseService;
import lazybakers.model.entity.Category;

import java.util.List;

/**
 * Brings in the basic CRUD service ops from BaseService. Insert additional ops here.
 */
public interface CategoryService extends BaseService<Category, Long> {
    /**
     * Validates whether the given category already
     * exists in the system.
     *
     * @param categoryName
     *
     * @return
     */
    public boolean isCategoryPresent(String categoryName);

    /**
     * Validates whether the given category priority already
     * exists in the system.
     *
     * @param priorityId
     *
     * @return
     */
    public boolean isPriorityPresent(Integer priorityId);

    /**
     * Find category by name
     *
     * @param categoryName
     * @return
     */
    public Category findByCategoryName(String categoryName);

    /**
     * Find sub categories by parent category
     *
     * @param parentCategory
     * @return
     */
    public List<Category> findSubCategories(Category parentCategory);
}
