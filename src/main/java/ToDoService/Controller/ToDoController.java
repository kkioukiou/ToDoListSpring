package ToDoService.Controller;

import ToDoService.Models.ToDoListItem;
import ToDoService.Repository.ToDoItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping(path = "/api/todo")
public class ToDoController {

    private final Logger logger = Logger.getLogger("Logger");

    @Autowired
    private ToDoItemsRepository toDoItemsRepository;

    @GetMapping("/item/getAllItems/{id}")
    public @ResponseBody Iterable<ToDoListItem> getAllItems(@PathVariable int id){
        return toDoItemsRepository.findByOwnerAndParentIdIsNull(id);
    }

    @PostMapping(value = "/item/insertNewItem/", consumes="application/json")
    public ResponseEntity insertItem(@RequestBody ToDoListItem t){
        toDoItemsRepository.save(t);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/item/insertChild/", consumes="application/json")
    public ResponseEntity insertChildItem(@RequestBody ToDoListItem t){
        toDoItemsRepository.save(t);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/item/delete/{id}")
    public ResponseEntity deleteItem(@PathVariable int id){
        toDoItemsRepository.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path = "/item/edit/", consumes="application/json")
    public ResponseEntity updateItemValue(@RequestBody ToDoListItem t){
        ToDoListItem toDoListItem = toDoItemsRepository.findOne(t.getId());
        toDoListItem.setItemValue(t.getItemValue());
        toDoItemsRepository.save(toDoListItem);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path = "/item/check/", consumes = "application/json")
    public ResponseEntity checkedItem(@RequestBody ToDoListItem t){
        ToDoListItem toDoListItem = toDoItemsRepository.findOne(t.getId());
        if (toDoListItem.isChecked()) {
            toDoListItem.setChecked(false);
        } else {
            toDoListItem.setChecked(true);
        }
        toDoItemsRepository.save(toDoListItem);
        return new ResponseEntity(HttpStatus.OK);
    }
}
