package br.com.snarloff.todolist.task;

import br.com.snarloff.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final ITaskRepository taskRepository;

    public TaskController(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody TaskModel task, HttpServletRequest request) {
        task.setIdUser((UUID) request.getAttribute("idUser"));

        var currentDate = LocalDateTime.now();

        if (currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt())) {
            return ResponseEntity.badRequest().body("Start / end date must be greater than current date.");
        }

        if (task.getStartAt().isAfter(task.getEndAt())) {
            return ResponseEntity.badRequest().body("Start date must be less than end date.");
        }

        var createdTask = this.taskRepository.save(task);
        return ResponseEntity.ok().body(createdTask);
    }

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        return this.taskRepository.findByIdUser((UUID) request.getAttribute("idUser"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody TaskModel task, @PathVariable UUID id, HttpServletRequest request) {
        var existsTask = this.taskRepository.findById(id).orElse(null);
        var idUser = (UUID) request.getAttribute("idUser");

        if (existsTask == null) {
            return ResponseEntity.status(404).body("Task not found.");
        }

        if (task.getIdUser().equals(idUser)) {
            return ResponseEntity.badRequest().body("You can't update a task from another user.");
        }

        Utils.copyNonNullProperties(task, existsTask);

        var updatedTask = this.taskRepository.save(existsTask);
        return ResponseEntity.ok().body(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id, HttpServletRequest request) {
        var existsTask = this.taskRepository.findById(id).orElse(null);
        var idUser = (UUID) request.getAttribute("idUser");

        if (existsTask == null) {
            return ResponseEntity.status(404).body("Task not found.");
        }

        if (existsTask.getIdUser().equals(idUser)) {
            return ResponseEntity.badRequest().body("You can't delete a task from another user.");
        }

        this.taskRepository.delete(existsTask);
        return ResponseEntity.ok().body("Task deleted.");
    }
}
