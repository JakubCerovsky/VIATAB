package viatab.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import viatab.backend.entity.Department;
import viatab.backend.entity.Story;
import viatab.backend.repository.IDepartmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/viatab")
public class DepartmentController
{
  private final IDepartmentRepository departmentRepository;

  @Autowired
  public DepartmentController(IDepartmentRepository dr)
  {
    departmentRepository = dr;
  }

  @GetMapping("/departments/v1/{id}")
  public ResponseEntity<Object> getDepartment(@PathVariable(value = "id") long departmentId) {
    Optional<Department> department = departmentRepository.findById(departmentId);
    if (!department.isPresent()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(department.get(), HttpStatus.OK);
  }

  @GetMapping("/departments/v1")
  public ResponseEntity<List<Department>> getDepartments()
  {
    List<Department> departments = new ArrayList<>(departmentRepository.findAll());
    return new ResponseEntity<>(departments, HttpStatus.OK);
  }
}
