package ToDoService.Controller;

import ToDoService.Models.ToDoListItem;
import ToDoService.Repository.ToDoItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/todo")
public class ToDoController {

    @Autowired
    private ToDoItemsRepository toDoItemsRepository;

    @GetMapping("/items")
    public @ResponseBody Iterable<ToDoListItem> getAllItems(){
        return toDoItemsRepository.findAll();
    }

    @PostMapping(value = "/item")
    public ResponseEntity insertItem(@RequestBody String str){
        ToDoListItem t = new ToDoListItem();
        t.setItemValue(str);
        toDoItemsRepository.save(t);
        return new ResponseEntity(str, HttpStatus.OK);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity deleteItem(@PathVariable Long id){
        toDoItemsRepository.delete(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @PutMapping(path = "/item")
    public ResponseEntity checkedItem(@RequestBody Long id){
        ToDoListItem t;
        t = toDoItemsRepository.findOne(id);
        if (t.isChecked()) {
            t.setChecked(false);
            toDoItemsRepository.save(t);
        } else {
            t.setChecked(true);
            toDoItemsRepository.save(t);
        }
        return new ResponseEntity(id, HttpStatus.OK);
    }
}
