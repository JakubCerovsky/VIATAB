package viatab.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class StoryDto{
  private Long departmentId;
  private String title;
  private String description;
}