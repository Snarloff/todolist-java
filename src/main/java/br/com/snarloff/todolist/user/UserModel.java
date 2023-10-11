package br.com.snarloff.todolist.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data // Lombok's annotation to generate getters, setters, toString, equals and hashCode
@Entity(name = "tb_users") // Annotation to map this class to a table named users
public class UserModel {

  @Id // Annotation to map this field to a primary key
  @GeneratedValue(generator = "UUID") // Annotation to generate a UUID automatically
  private UUID id;

  @Column(unique = true)
  private String username;

  private String name;
  private String password;

  @CreationTimestamp // Annotation to generate a timestamp automatically
  private LocalDateTime createdAt;
}
