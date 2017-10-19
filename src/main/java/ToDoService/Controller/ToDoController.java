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
        return toDoItemsRepository.findByParentIdIsNull();
    }

    @PostMapping(value = "/item")
    public ResponseEntity insertItem(@RequestBody String str){
        ToDoListItem t = new ToDoListItem();
        t.setItemValue(str);
        toDoItemsRepository.save(t);
        return new ResponseEntity(str, HttpStatus.OK);
    }

    @PostMapping(value = "/item", produces = "application/json", consumes="application/json")
    public ResponseEntity insertChildItem(@RequestBody ToDoListItem toDoListItem){
        ToDoListItem t = new ToDoListItem();
        t.setItemValue(toDoListItem.getItemValue());
        t.setParentId(toDoListItem.getParentId());
        toDoItemsRepository.save(t);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity deleteItem(@PathVariable int id){
        toDoItemsRepository.delete(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @PutMapping(path = "/item")
    public ResponseEntity checkedItem(@RequestBody int id){
        ToDoListItem t;
        t = toDoItemsRepository.findOne(id);
        if (t.isChecked()) {
            t.setChecked(false);
        } else {
            t.setChecked(true);
        }
        toDoItemsRepository.save(t);
        return new ResponseEntity(id, HttpStatus.OK);
    }
}
