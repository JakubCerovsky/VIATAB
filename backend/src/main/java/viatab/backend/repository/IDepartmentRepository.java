package viatab.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viatab.backend.entity.Department;

public interface IDepartmentRepository extends JpaRepository<Department, Long>
{
}
