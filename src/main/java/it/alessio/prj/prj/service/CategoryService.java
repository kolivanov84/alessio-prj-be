package it.alessio.prj.prj.service;

import it.alessio.prj.prj.dto.CategoryDto;
import it.alessio.prj.prj.entity.Category;
import it.alessio.prj.prj.mapper.CategoryMapper;
import it.alessio.prj.prj.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

  @Autowired CategoryRepository categoryRepository;
  @Autowired CategoryMapper categoryMapper;

  public List<CategoryDto> findAll() {
    List<CategoryDto> categories = new ArrayList<>();
    categoryRepository.findAll().stream().forEach(c -> categories.add(categoryMapper.toDto(c)));
    return categories;
  }

  public CategoryDto findById(Integer id) {
    Optional<Category> category = categoryRepository.findById(id);
    if (category.isPresent()) return categoryMapper.toDto(category.get());
    else return null;
  }
}
