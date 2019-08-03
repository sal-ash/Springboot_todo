package Application;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface TaskRepository extends JpaRepository<Task, Long> {

	
	@Query (value = "SELECT t FROM Task t WHERE t.name = :name")
	public List<Task> findByName(@Param("name") String name); 
	
	//or List<Task> findByName(String name); 
}
