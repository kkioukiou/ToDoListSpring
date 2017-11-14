package ToDoService.Controller;

import ToDoService.Models.ToDoListItem;
import ToDoService.Repository.ToDoItemsRepository;
import ToDoService.Security.SecurityModels.User;
import ToDoService.Security.SecurityService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping(path = "/api/todo")
public class ToDoController {

    private final Logger logger = Logger.getLogger("Logger");

    @Autowired
    private ToDoItemsRepository toDoItemsRepository;

    @Autowired
    private UserService userService;

    private Authentication auth;

    private User authUser(){
        auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserByEmail(auth.getName());
    }

    @GetMapping("/getAllItems")
    public @ResponseBody Iterable<ToDoListItem> getAllItems(){
        return toDoItemsRepository.findByOwnerAndParentIdIsNull(authUser().getId());
    }

    @PostMapping(value = "/insertNewItem", consumes="application/json")
    public ResponseEntity insertItem(@RequestBody ToDoListItem t){
        t.setOwner(authUser().getId());
        toDoItemsRepository.save(t);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteItem(@PathVariable int id){
        toDoItemsRepository.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path = "/edit", consumes="application/json")
    public ResponseEntity updateItemValue(@RequestBody ToDoListItem t){
        ToDoListItem toDoListItem = toDoItemsRepository.findOne(t.getId());
        toDoListItem.setItemValue(t.getItemValue());
        toDoListItem.setOwner(authUser().getId()); //ToDo I'm not sure what it's need
        toDoItemsRepository.save(toDoListItem);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path = "/check", consumes = "application/json")
    public ResponseEntity checkedItem(@RequestBody ToDoListItem t){
        ToDoListItem toDoListItem = toDoItemsRepository.findOne(t.getId());
        toDoListItem.setChecked(!toDoListItem.isChecked());
        toDoItemsRepository.save(toDoListItem);
        return new ResponseEntity(HttpStatus.OK);
    }
}