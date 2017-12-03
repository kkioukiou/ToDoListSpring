package ToDoService.Repository;

import ToDoService.Models.LabelForItemList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LabelForItemListRepository extends CrudRepository<LabelForItemList, Integer> {
    List<LabelForItemList> findByOwnerLike(int id);
}
