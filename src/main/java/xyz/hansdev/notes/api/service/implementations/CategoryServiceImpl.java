package xyz.hansdev.notes.api.service.implementations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.hansdev.notes.api.exception.ResourceNotFoundException;
import xyz.hansdev.notes.api.model.Category;
import xyz.hansdev.notes.api.repository.CategoryRepository;
import xyz.hansdev.notes.api.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> findPaginated(Pageable pageable) {
        return categoryRepository.findPaginated(pageable);
    }

    @Override
    public Category findOne(Integer id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        Category original = categoryRepository
                .findById(category.getId())
                .orElseThrow(ResourceNotFoundException::new);

        original.setName(category.getName());

        return categoryRepository.save(original);
    }

    @Override
    public void delete(Integer id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        categoryRepository.delete(category);
    }
}
