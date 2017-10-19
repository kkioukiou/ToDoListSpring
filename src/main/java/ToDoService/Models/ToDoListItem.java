package ToDoService.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "to_do_list_item")
public class ToDoListItem implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "item_value")
    private String itemValue;
    @Column(name = "is_checked")
    private boolean isChecked;
    @Column(name = "parent_id")
    private Integer parentId;

    @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL)
    private List<ToDoListItem> children = new ArrayList<>();

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<ToDoListItem> getChildren() {
        return children;
    }

    public void setChildren(List<ToDoListItem> children) {
        this.children = children;
    }
}
