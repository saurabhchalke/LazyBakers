package yourwebproject2.service.impl;

import yourwebproject2.framework.data.BaseJPAServiceImpl;
import yourwebproject2.model.entity.Category;
import yourwebproject2.model.repository.CategoryRepository;
import yourwebproject2.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl extends BaseJPAServiceImpl<Category, Long> implements CategoryService {
    private static Logger LOG = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private @Autowired
    CategoryRepository categoryRepository;

    @PostConstruct
    public void setupService() {
        LOG.info("setting up categoryService...");
        this.baseJpaRepository = categoryRepository;
        this.entityClass = Category.class;
        this.baseJpaRepository.setupEntityClass(Category.class);
        LOG.info("categoryService created...");
    }


    @Override
    public boolean isCategoryPresent(String categoryName) {
        if (categoryRepository.findByCategoryName(categoryName) != null) {
            return true;
        } else
            return false;
    }

    @Override
    public boolean isPriorityPresent(Integer categoryPriority) {
        if (categoryRepository.findByCategoryPriority(categoryPriority) != null) {
            return true;
        } else
            return false;
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        Category c = categoryRepository.findByCategoryName(categoryName);

        if(c==null) {
            try {
				throw new Exception("Category: "+categoryName + " not found");
			} catch (Exception e) {
				
				e.printStackTrace();
			}
        }

        return c;
    }

    @Override
    public List<Category> findSubCategories(Category parentCategory) {
        List<Category> categories = categoryRepository.findSubCategories(parentCategory);

        if(categories==null || categories.size()==0) {
            try {
				throw new Exception("Subcategories for category: "+parentCategory.getName()+ " not found");
			} catch (Exception e) {
				e.printStackTrace();
			}
        }

        return categories;
    }
}
