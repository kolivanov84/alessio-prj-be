package it.alessio.prj.prj.mapper;

import it.alessio.prj.prj.dto.CategoryDto;
import it.alessio.prj.prj.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  ModelMapper modelMapper = new ModelMapper();

  public CategoryDto toDto(Category category) {
    return modelMapper.map(category, CategoryDto.class);
  }
}
