package xyz.hansdev.notes.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import xyz.hansdev.notes.api.exception.ResourceNotFoundException;
import xyz.hansdev.notes.api.model.Category;
import xyz.hansdev.notes.api.service.CategoryService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/categories/")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ResponseBody
    public List<Category> findPaginated(@PageableDefault(direction = Sort.Direction.DESC, sort = "id") Pageable pageable) {

        Page<Category> result = categoryService.findPaginated(pageable);
        if(pageable.getPageNumber() > result.getTotalPages()) {
            throw new ResourceNotFoundException();
        }

        return result.getContent();
    }

    @GetMapping(value = "{id}")
    public Category findOne(@PathVariable("id") Integer id) { return categoryService.findOne(id); }

    @PostMapping
    public Category create(@PathParam("category") Category category) {
        return categoryService.create(category);
    }

    @PutMapping(value = "{id}")
    public Category update(@PathVariable("id") Integer id, @PathParam("category") Category category) {
        return categoryService.update(category);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Integer id) {
        categoryService.delete(id);
    }
}
