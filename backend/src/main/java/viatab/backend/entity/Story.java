package viatab.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stories")
@NoArgsConstructor @Getter @Setter
public class Story
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = 0L;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "department_id", nullable = false)
  private Department department;

  private String title;

  private String description;

  public Story(Department department, String title, String description){
    this.department = department;
    this.title = title;
    this.description = description;
  }
}
