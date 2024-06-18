package viatab.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viatab.backend.entity.Department;
import viatab.backend.repository.IDepartmentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service public class DepartmentService
{
  private final IDepartmentRepository departmentRepository;

  @Autowired public DepartmentService(
      IDepartmentRepository departmentRepository)
  {
    this.departmentRepository = departmentRepository;
  }

  public List<Department> getAllDepartments()
  {
    return departmentRepository.findAll();
  }

  public Department getDepartmentById(Long departmentId)
  {
    Optional<Department> department = departmentRepository.findById(departmentId);
    if (department.isEmpty())
    {
      throw new NoSuchElementException(
          "Department with ID " + departmentId + " not found");
    }
    return department.get();
  }
}
