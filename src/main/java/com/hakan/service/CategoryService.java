package com.hakan.service;

import com.hakan.dto.request.CategoryDto;
import com.hakan.dto.request.CategorySaveRequestDto;
import com.hakan.dto.request.PostSaveRequestDto;
import com.hakan.entity.Category;
import com.hakan.entity.Post;
import com.hakan.entity.User;
import com.hakan.exception.BlogException;
import com.hakan.exception.ErrorType;
import com.hakan.mapper.ICategoryMapper;
import com.hakan.mapper.IPostMapper;
import com.hakan.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void createCategory(CategoryDto dto){
        Category category = ICategoryMapper.INSTANCE.categoryDtoToCategory(dto);
        categoryRepository.save(category);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public void update(CategorySaveRequestDto dto){
        Optional<Category> optionalCategory = findById(dto.getId());
        if(optionalCategory.isPresent()){
            Category category = Category.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .build();

            categoryRepository.save(category);
        }else{
            throw new BlogException((ErrorType.CATEGORY_NOT_FOUND));
        }
    }

    public void deleteById(Long id){
        Optional<Category> optionalCategory = findById(id);
        if(optionalCategory.isPresent()){
            categoryRepository.deleteById(id);
        } else{
            throw new BlogException(ErrorType.CATEGORY_NOT_FOUND);
        }
    }

    public List<Category> findByNameIgnoreCase(String name) {
        Boolean varMi = categoryRepository.existsByName(name);
        if(varMi){
            return categoryRepository.findByNameIgnoreCase(name);
        }else{
            throw new BlogException(ErrorType.CATEGORY_NOT_FOUND);
        }

    }

}
