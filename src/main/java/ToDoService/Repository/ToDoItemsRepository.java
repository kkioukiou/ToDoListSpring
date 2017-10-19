package ToDoService.Repository;

import ToDoService.Models.ToDoListItem;
import org.springframework.data.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface ToDoItemsRepository extends CrudRepository<ToDoListItem, Integer> {
    List<ToDoListItem> findByParentIdIsNull();
    List<ToDoListItem> findByParentIdLike(int id);
}
