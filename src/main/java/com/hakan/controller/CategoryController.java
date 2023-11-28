package com.hakan.controller;

import com.hakan.dto.request.CategoryDto;
import com.hakan.dto.request.CategorySaveRequestDto;
import com.hakan.dto.request.PostRequestDto;
import com.hakan.dto.request.PostSaveRequestDto;
import com.hakan.entity.Category;
import com.hakan.entity.Post;
import com.hakan.entity.User;
import com.hakan.mapper.ICategoryMapper;
import com.hakan.mapper.IPostMapper;
import com.hakan.service.CategoryService;
import lombok.RequiredArgsConstructor;
import static com.hakan.constant.EndPoints.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(ROOT+CATEGORY)
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(CREATE)
    public ResponseEntity<String> createCategory(@RequestBody CategoryDto dto){
        categoryService.createCategory(dto);
        return ResponseEntity.ok("Kategori kaydetme işlemi tamamlanmıştır...");
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<Category>> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping(FINDBYID)
    public ResponseEntity<Optional<Category>> findById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> update(CategorySaveRequestDto dto){
        categoryService.update(dto);
        return ResponseEntity.ok("Kategori güncelleme işlemi tamamlanmıştır...");
    }

    @DeleteMapping(DELETEBYID)
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.ok("Kategori silme işlemi tamamlanmıştır...");
    }

    @GetMapping(FINDBYNAME)
    public ResponseEntity<List<Category>> findByNameIgnoreCase(String name){
        return ResponseEntity.ok(categoryService.findByNameIgnoreCase(name));
    }

}
