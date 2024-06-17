package viatab.backend.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import viatab.backend.entity.Department;
import viatab.backend.repository.IDepartmentRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class DepartmentServiceUnitTest
{
  @InjectMocks private DepartmentService departmentService;
  @MockBean private IDepartmentRepository departmentRepository;

  @Test public void testGetDepartmentById_Success()
  {
    Long expectedDepartmentId = 1L;
    Department expectedDepartment = new Department();

    when(departmentRepository.findById(expectedDepartmentId)).thenReturn(
        Optional.of(expectedDepartment));

    Department retrievedDepartment = departmentService.getDepartmentById(
        expectedDepartmentId);

    assertEquals(expectedDepartment, retrievedDepartment);
    verify(departmentRepository, times(1)).findById(expectedDepartmentId);
  }

  @Test public void testGetDepartmentById_NotFound()
  {
    Long invalidDepartmentId = 100L; // Non-existent department ID

    when(departmentRepository.findById(invalidDepartmentId)).thenReturn(
        Optional.empty());

    try
    {
      departmentService.getDepartmentById(invalidDepartmentId);
      fail("Expected NoSuchElementException");
    }
    catch (NoSuchElementException e)
    {
    }
  }
}
