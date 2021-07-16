package ch.bbw.rs.todo.controller;

import ch.bbw.rs.todo.entity.Task;
import ch.bbw.rs.todo.repository.TaskRepository;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // add a task
    @PostMapping
    public void addTask(@RequestBody Task task) {
        taskRepository.save(task);
    }

    //get all Tasks in a list
    @GetMapping
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    //edit a Task and change the description and the title
    @PutMapping("/{id}")
    public void editTask(@PathVariable long id, @RequestBody Task task) {
        Task existingTask = taskRepository.findById(id).get();
        Assert.notNull(existingTask, "Task not found");
        existingTask.setDescription(task.getDescription());
        existingTask.setTitle(task.getTitle());
        taskRepository.save(existingTask);
    }

    //delete a task with the id
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        Task taskToDel = taskRepository.findById(id).get();
        taskRepository.delete(taskToDel);
    }
}