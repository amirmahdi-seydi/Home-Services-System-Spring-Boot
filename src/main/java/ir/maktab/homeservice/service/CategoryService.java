package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 5/7/2022 11:36 AM
 */

import ir.maktab.homeservice.model.Category;
import ir.maktab.homeservice.service.base.BaseService;


public interface CategoryService extends BaseService<Category, Long> {

    @Override
    Category save(Category category);

    Category findCategoryByCategoryName(String name);

    Boolean existByCategoryName(String name);
}
