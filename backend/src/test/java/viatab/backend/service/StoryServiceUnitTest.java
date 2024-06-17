package viatab.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import viatab.backend.dto.StoryDto;
import viatab.backend.entity.Department;
import viatab.backend.entity.Story;
import viatab.backend.repository.IDepartmentRepository;
import viatab.backend.repository.IStoryRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) public class StoryServiceUnitTest
{
  @InjectMocks private StoryService storyService;
  @Mock private IStoryRepository storyRepository;
  @Mock private IDepartmentRepository departmentRepository;

  @Test public void testCreateStory_Success()
  {
    Long departmentId = 1L;
    StoryDto storyDto = new StoryDto("Test Title", "Test Description",
        departmentId);

    Department department = new Department();
    department.setId(departmentId);

    when(departmentRepository.findById(departmentId)).thenReturn(
        Optional.of(department));
    Story savedStory = new Story(department, storyDto.getTitle(),
        storyDto.getDescription());
    when(storyRepository.save(any(Story.class))).thenReturn(savedStory);

    Story createdStory = storyService.createStory(storyDto);

    assertEquals(savedStory, createdStory);
    verify(departmentRepository, times(1)).findById(departmentId);
    verify(storyRepository, times(1)).save(any(Story.class));
  }

  @Test public void testGetStoryById_Success()
  {
    Long expectedStoryId = 1L;
    Story expectedStory = new Story();

    when(storyRepository.findById(expectedStoryId)).thenReturn(
        Optional.of(expectedStory));

    Story retrievedStory = storyService.getStoryById(expectedStoryId);

    assertEquals(expectedStory, retrievedStory);
    verify(storyRepository, times(1)).findById(expectedStoryId);
  }

  @Test public void testGetStoryById_NotFound()
  {
    Long invalidStoryId = 100L; // Non-existent story ID

    when(storyRepository.findById(invalidStoryId)).thenReturn(Optional.empty());

    try
    {
      storyService.getStoryById(invalidStoryId);
      fail("Expected NoSuchElementException");
    }
    catch (NoSuchElementException e)
    {

    }
  }

  @Test public void testUpdateStory_Success()
  {
    Long storyId = 1L;
    Story existingStory = new Story();
    existingStory.setId(storyId);

    when(storyRepository.findById(storyId)).thenReturn(
        Optional.of(existingStory));
    existingStory.setTitle("Updated Title");

    storyService.updateStory(existingStory);

    verify(storyRepository, times(1)).save(existingStory);
  }

  @Test public void testUpdateStory_NotFound()
  {
    Story updatedStory = new Story(); // Incomplete story object
    updatedStory.setId(100L); // Invalid ID

    when(storyRepository.findById(updatedStory.getId())).thenReturn(
        Optional.empty());

    try
    {
      storyService.updateStory(updatedStory);
      fail("Expected NoSuchElementException");
    }
    catch (NoSuchElementException e)
    {
    }
  }

  @Test public void testDeleteStory_Success()
  {
    Long storyId = 1L;
    Story existingStory = new Story(); // Create a sample story object
    existingStory.setId(storyId);

    when(storyRepository.findById(storyId)).thenReturn(
        Optional.of(existingStory));

    storyService.deleteStory(storyId);

    verify(storyRepository, times(1)).delete(existingStory);
  }

  @Test public void testDeleteStory_NotFound()
  {
    Long invalidStoryId = 100L;

    when(storyRepository.findById(invalidStoryId)).thenReturn(Optional.empty());

    try
    {
      storyService.deleteStory(invalidStoryId);
      fail("Expected NoSuchElementException");
    }
    catch (NoSuchElementException e)
    {
    }
  }

}
