package viatab.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "departments")
@NoArgsConstructor @Getter @Setter
public class Department
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = 0L;

  private String name;

  public Department(String name){
    this.name=name;
  }
}
