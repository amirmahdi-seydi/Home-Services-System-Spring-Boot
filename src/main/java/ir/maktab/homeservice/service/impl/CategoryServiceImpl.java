package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:14 PM
 */

import ir.maktab.homeservice.model.Category;
import ir.maktab.homeservice.repository.CategoryRepository;
import ir.maktab.homeservice.service.CategoryService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;

public class CategoryServiceImpl extends BaseServiceImpl<Category, Long, CategoryRepository>
        implements CategoryService {

    public CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
    }
}
