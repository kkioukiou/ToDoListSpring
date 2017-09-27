package ToDoService.Repository;

import ToDoService.Models.ToDoListItem;
import org.springframework.data.repository.CrudRepository;

public interface ToDoItemsRepository extends CrudRepository<ToDoListItem, Long> {

}
