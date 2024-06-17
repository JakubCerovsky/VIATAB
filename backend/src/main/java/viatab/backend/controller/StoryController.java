package viatab.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import viatab.backend.entity.Department;
import viatab.backend.entity.Story;
import viatab.backend.dto.StoryDto;
import viatab.backend.service.StoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/viatab/stories")
public class StoryController
{
  private final StoryService storyService;

  public StoryController(StoryService storyService)
  {
    this.storyService = storyService;
  }

  @PostMapping("/v2")
  public ResponseEntity<Object> createStory(@Validated @RequestBody StoryDto story)
  {
    Story createdStory = storyService.createStory(story);
    if (createdStory==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    return new ResponseEntity<>(story, HttpStatus.OK);
  }

  @GetMapping("/v2/{id}")
  public ResponseEntity<Object> getStory(@PathVariable(value = "id") Long storyId) {
    Story story = storyService.getStoryById(storyId);
    if (story==null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(story, HttpStatus.OK);
  }

  @GetMapping("/v2")
  public ResponseEntity<List<Story>> getStories() {
    return new ResponseEntity<>(storyService.getAllStories(), HttpStatus.OK);
  }

  @PutMapping("/v2")
  public ResponseEntity<Object> updateStory(@Validated @RequestBody Story updatedStory) {
    Story story = storyService.updateStory(updatedStory);
    return new ResponseEntity<>(story, HttpStatus.OK);
  }

  @DeleteMapping("/v2/{id}")
  public ResponseEntity<Object> deleteStory(@PathVariable(value = "id") Long storyId) {
    storyService.deleteStory(storyId);
    return new ResponseEntity<>("Story removed!", HttpStatus.OK);
  }
}
