package ToDoService.Controller;

import ToDoService.Models.LabelForItemList;
import ToDoService.Models.Labels;
import ToDoService.Models.ToDoListItem;
import ToDoService.Models.ToDoListItemForLabelsList;
import ToDoService.Repository.LabelForItemListRepository;
import ToDoService.Repository.LabelsRepository;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(path = "/api/todo")
public class ToDoController {

    private final Logger logger = Logger.getLogger("Logger");

    @Autowired
    private LabelsRepository labelsRepository;

    @Autowired
    private LabelForItemListRepository labelForItemListRepository;

    @Autowired
    private ToDoItemsRepository toDoItemsRepository;

    @Autowired
    private UserService userService;

    private User authUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserByEmail(auth.getName());
    }

    @PostMapping(value = "/insertNewLabel", consumes = "application/json")
    public ResponseEntity insertLabel(@RequestBody Labels l){
        l.setOwner(authUser().getId());
        labelsRepository.save(l);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/addLabelToItem/{id}", consumes = "application/json")
    public ResponseEntity addLabelToItem(@PathVariable int id, @RequestBody Labels l){
        ToDoListItem item = toDoItemsRepository.findOne(id);
        LabelForItemList label = labelForItemListRepository.findOne(l.getLabelId());
        if (!item.getLabels().contains(label)){
            item.setLabels(label);
            toDoItemsRepository.save(item);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/deleteLabel/{id}")
    public ResponseEntity deleteLabel(@PathVariable int id){
        labelsRepository.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getAllItemsByLabelId/{id}")
    public @ResponseBody Iterable<ToDoListItemForLabelsList> getAllItemsByLabelId(@PathVariable int id){
        Labels label = labelsRepository.findOne(id);
        if (label.getOwner() == authUser().getId()){
            return label.getToDoListItems();
        } else {
            return null; //todo need change this string because null isn't true way)
        }
    }

    @GetMapping("/getAllLabelsForEveryItem/{id}")
    public @ResponseBody Iterable<LabelForItemList> getAllLabelsForEveryItem(@PathVariable int id){
        ToDoListItem t = toDoItemsRepository.findOne(id);
        if (t.getOwner() == authUser().getId()){
            List<LabelForItemList> l = labelForItemListRepository.findByOwnerLike(authUser().getId());
            l.removeAll(t.getLabels());
            return l;
        }
        return null;
    }

    @GetMapping("/getAllLabels")
    public @ResponseBody Iterable<LabelForItemList> getAllLabels(){
        return labelForItemListRepository.findByOwnerLike(authUser().getId());
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

    @PutMapping(path = "/setRemindMeTimer/{id}", consumes="application/json")
    public ResponseEntity setRemindMeTimer(@PathVariable int id, @RequestBody String t) throws ParseException {
        ToDoListItem toDoListItem = toDoItemsRepository.findOne(id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date time = simpleDateFormat.parse(t);
        toDoListItem.setRemindMe(time);
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