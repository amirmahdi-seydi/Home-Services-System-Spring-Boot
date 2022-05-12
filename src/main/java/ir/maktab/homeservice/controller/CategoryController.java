package ir.maktab.homeservice.controller;
/*
 * created by Amir mahdi seydi 5/11/2022 2:50 AM
 */


import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.model.Category;
import ir.maktab.homeservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryService categoryService;


    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        try{
            categoryService.deleteById(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        }
        catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
