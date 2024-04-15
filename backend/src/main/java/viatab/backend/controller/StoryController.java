package viatab.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import viatab.backend.entity.Department;
import viatab.backend.entity.Story;
import viatab.backend.dto.StoryDto;
import viatab.backend.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/viatab")
public class StoryController
{
  private final IStoryRepository storyRepository;
  private final IDepartmentRepository departmentRepository;

  @Autowired
  public StoryController(IStoryRepository sr, IDepartmentRepository dr)
  {
    storyRepository = sr;
    departmentRepository = dr;
  }

  @PostMapping("/stories/v1")
  public ResponseEntity<Object> createStory(@Validated @RequestBody StoryDto story)
  {
    Optional<Department> department = departmentRepository.findById(story.getDepartmentId());
    if (department.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    Story storyToCreate = new Story(department.get(), story.getTitle(), story.getDescription());
    storyRepository.save(storyToCreate);

    return new ResponseEntity<>("Story successfully saved!", HttpStatus.OK);
  }

  @GetMapping("/stories/v1/{id}")
  public ResponseEntity<Object> getStory(@PathVariable(value = "id") Long storyId) {
    Optional<Story> story = storyRepository.findById(storyId);
    if (!story.isPresent()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(story.get(), HttpStatus.OK);
  }

  @GetMapping("/stories/v1")
  public ResponseEntity<List<Story>> getStories() {
    List<Story> stories = new ArrayList<>(storyRepository.findAll());
    return new ResponseEntity<>(stories, HttpStatus.OK);
  }

  @PutMapping("/stories/v1")
  public ResponseEntity<Object> updateStory(@Validated @RequestBody Story updatedStory) {
    storyRepository.save(updatedStory);
    return new ResponseEntity<>("Story successfully updated!", HttpStatus.OK);
  }

  @DeleteMapping("/stories/v1/{id}")
  public ResponseEntity<Object> deleteStory(@PathVariable(value = "id") Long storyId) {
    Optional<Story> story = storyRepository.findById(storyId);
    if (!story.isPresent()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    storyRepository.delete(story.get());
    return new ResponseEntity<>("Story removed!", HttpStatus.OK);
  }
}
