package Application;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskDAO {
	
	@Autowired
	TaskRepository taskRepository;
	
	//Add a task
	
	public Task add(Task task) {
		return taskRepository.save(task);
	}
	
	//all tasks
	public List<Task> findAll(){
		return taskRepository.findAll();
	}
	
	//get  tasks by name
	public List<Task> findByName(String name){
		return taskRepository.findByName(name);
	}
	
	//get one task
	public Task getByID(Long id){
		return taskRepository.getOne(id);
	}
	
	//delete task
	public void delete(Task task) {
		taskRepository.delete(task);
	}
}
