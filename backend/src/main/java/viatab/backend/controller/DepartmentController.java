package viatab.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import viatab.backend.entity.Department;
import viatab.backend.service.DepartmentService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/viatab/departments")
public class DepartmentController
{
  private final DepartmentService departmentService;

  @Autowired
  public DepartmentController(DepartmentService departmentService)
  {
    this.departmentService = departmentService;
  }

  @GetMapping("/v2/{id}")
  public ResponseEntity<Object> getDepartment(@PathVariable(value = "id") long departmentId) {
    Department department = departmentService.getDepartmentById(departmentId);
    if (department==null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(department, HttpStatus.OK);
  }

  @GetMapping("/v2")
  public ResponseEntity<List<Department>> getDepartments()
  {
    return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
  }
}
