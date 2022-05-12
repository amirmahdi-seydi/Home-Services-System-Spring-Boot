package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:14 PM
 */

import ir.maktab.homeservice.exception.AlreadyExistException;
import ir.maktab.homeservice.model.Category;
import ir.maktab.homeservice.repository.CategoryRepository;
import ir.maktab.homeservice.service.CategoryService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, Long, CategoryRepository>
        implements CategoryService {

    public CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
    }

    @Override
    public Category save(Category category) {
        String categoryName = category.getCategoryName().trim().replaceAll("\\s+"," ").toUpperCase();

        if (repository.existsByCategoryName(categoryName)) {
            throw new AlreadyExistException("This service already define!");
        }

        category.setCategoryName(categoryName);

        return super.save(category);
    }

    @Override
    public Category findCategoryByCategoryName(String name) {
        return repository.findCategoryByCategoryName(name);
    }

    @Override
    public Boolean existByCategoryName(String name) {
        return repository.existsByCategoryName(name);
    }
}
