package ToDoService.Repository;

import ToDoService.Models.Labels;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LabelsRepository extends CrudRepository<Labels, Integer> {
    List<Labels> findByOwnerLike(int id);
}
