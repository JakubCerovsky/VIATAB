package viatab.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viatab.backend.entity.Story;

public interface IStoryRepository extends JpaRepository<Story, Long>
{
}
