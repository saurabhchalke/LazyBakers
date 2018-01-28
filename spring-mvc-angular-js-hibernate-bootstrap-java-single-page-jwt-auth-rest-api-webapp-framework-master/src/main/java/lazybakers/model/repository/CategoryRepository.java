package lazybakers.model.repository;

import lazybakers.framework.data.BaseJPARepository;
import lazybakers.model.entity.Category;

import java.util.List;

public interface CategoryRepository extends BaseJPARepository<Category, Long> {

    public Category findByCategoryName(String categoryName);

    public Category findByCategoryPriority(Integer categoryPriority);

    public List<Category> findSubCategories(Category parentCategory);
}
