package ToDoService.Models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "to_do_list_item")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
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
    @Column(name = "owner")
    private int owner;
    @Column(name = "remind_me")
    private Date remindMe;

    @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL)
    private List<ToDoListItem> children = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "to_do_list_item_labels",
            joinColumns = @JoinColumn(name = "to_do_list_items_id"),
            inverseJoinColumns = @JoinColumn(name = "labels_id")
    )
    private List<LabelForItemList> labels = new ArrayList<>();

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

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public List<LabelForItemList> getLabels() {
        return labels;
    }

    public void setLabels(LabelForItemList labels) {
        this.labels.add(labels);
    }

    public Date getRemindMe() {
        return remindMe;
    }

    public void setRemindMe(Date remindMe) {
        this.remindMe = remindMe;
    }

    @Override
    public String toString() {
        return "ToDoListItem{" +
                "id=" + id +
                ", itemValue='" + itemValue + '\'' +
                ", isChecked=" + isChecked +
                ", parentId=" + parentId +
                ", owner=" + owner +
                ", children=" + children +
                ", labels=" + labels +
                '}';
    }
}
