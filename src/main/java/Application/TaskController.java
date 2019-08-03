package Application;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TaskController {
	
	@Autowired
	TaskDAO taskDAO;
	
	@PostMapping("/tasks")
	public Task createTask(@Valid @RequestBody Task task) {
		return taskDAO.add(task);
	}
	
	@GetMapping("/tasks")
	public List<Task> getAllTasks() {
		return taskDAO.findAll();
	}
	
	@GetMapping("/tasksfind/{name}")
	public ResponseEntity<List<Task>> getSearch(@PathVariable("name") String name) {
		List<Task> tasks = taskDAO.findByName(name);
		
		if(tasks.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(tasks);
		
	}
	
	@PutMapping("/tasks/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable(value="id") long taskID, @Valid @RequestBody Task taskDetails) {
		Task task = taskDAO.getByID(taskID);
		if(task==null) {
			return ResponseEntity.notFound().build();
		}
		task.setName(taskDetails.getName());
		task.setDue(taskDetails.getDue());
		task.setDescription(taskDetails.getDescription());
		task.setComplete(taskDetails.getComplete());
		
		Task update=taskDAO.add(task);
		return ResponseEntity.ok().body(update);
		
	}
	
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<Task> deleteTask(@PathVariable(value = "id") Long taskID){
		Task task = taskDAO.getByID(taskID);
		
		if(task == null) {
			return ResponseEntity.notFound().build();
		}
		taskDAO.delete(task);
		return ResponseEntity.ok().build();
	}
} 
