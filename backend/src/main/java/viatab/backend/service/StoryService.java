package viatab.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viatab.backend.dto.StoryDto;
import viatab.backend.entity.Department;
import viatab.backend.entity.Story;
import viatab.backend.repository.IDepartmentRepository;
import viatab.backend.repository.IStoryRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service public class StoryService
{
  private final IStoryRepository storyRepository;
  private final IDepartmentRepository departmentRepository;

  @Autowired public StoryService(IStoryRepository storyRepository,
      IDepartmentRepository departmentRepository)
  {
    this.storyRepository = storyRepository;
    this.departmentRepository = departmentRepository;
  }

  public Story createStory(StoryDto storyDto)
  {
    Optional<Department> department = departmentRepository.findById(
        storyDto.getDepartmentId());
    if (department.isEmpty())
    {
      throw new NoSuchElementException(
          "Department with ID " + storyDto.getDepartmentId() + " not found");
    }

    Story storyToSave = new Story(department.get(), storyDto.getTitle(),
        storyDto.getDescription());
    return storyRepository.save(storyToSave);
  }

  public List<Story> getAllStories()
  {
    return storyRepository.findAll();
  }

  public Story getStoryById(Long storyId)
  {
    Optional<Story> story = storyRepository.findById(storyId);
    if (story.isEmpty())
    {
      throw new NoSuchElementException(
          "Story with ID " + storyId + " not found");
    }
    return story.get();
  }

  public Story updateStory(Story updatedStory)
  {
    Optional<Story> existingStory = storyRepository.findById(
        updatedStory.getId());
    if (existingStory.isEmpty())
    {
      throw new NoSuchElementException(
          "Story with ID " + updatedStory.getId() + " not found");
    }

    existingStory.get().setTitle(updatedStory.getTitle());
    existingStory.get().setDescription(updatedStory.getDescription());
    existingStory.get().setDepartment(
        updatedStory.getDepartment());

    return storyRepository.save(existingStory.get());
  }

  public void deleteStory(Long storyId)
  {
    Optional<Story> story = storyRepository.findById(storyId);
    if (story.isEmpty())
    {
      throw new NoSuchElementException(
          "Story with ID " + storyId + " not found");
    }
    storyRepository.delete(story.get());
  }
}
