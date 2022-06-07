package it.alessio.prj.prj.controller;

import io.swagger.annotations.ApiOperation;
import it.alessio.prj.prj.dto.CategoryDto;
import it.alessio.prj.prj.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

  @Autowired CategoryService categoryService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ApiOperation(value = "Get Categories", notes = "return all categories")
  public ResponseEntity getUser() {
    List<CategoryDto> categories = categoryService.findAll();
    if (categories != null && !categories.isEmpty())
      return ResponseEntity.status(HttpStatus.CREATED).body(categories);
    else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
}
