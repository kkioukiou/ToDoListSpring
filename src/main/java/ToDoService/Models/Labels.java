package ToDoService.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "labels")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="labelId")
public class Labels implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int labelId;

    @Column(name = "label_name")
    private String labelName;

    @Column(name = "owner")
    private int owner;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "to_do_list_item_labels",
            joinColumns = @JoinColumn(name = "labels_id"),
            inverseJoinColumns = @JoinColumn(name = "to_do_list_items_id")
    )
    private List<ToDoListItemForLabelsList> toDoListItems = new ArrayList<>();

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public List<ToDoListItemForLabelsList> getToDoListItems() {
        return toDoListItems;
    }

    public void setToDoListItems(ToDoListItemForLabelsList toDoListItems) {
        this.toDoListItems.add(toDoListItems);
    }

    @Override
    public String toString() {
        return "Labels{" +
                "labelId=" + labelId +
                ", labelName='" + labelName + '\'' +
                ", owner=" + owner +
                ", toDoListItems=" + toDoListItems +
                '}';
    }
}
