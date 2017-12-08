package ToDoService.Models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "to_do_list_item")
public class ToDoListItemForLabelsList implements Serializable {

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public Date getRemindMe() {
        return remindMe;
    }

    public void setRemindMe(Date remindMe) {
        this.remindMe = remindMe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDoListItemForLabelsList that = (ToDoListItemForLabelsList) o;

        if (id != that.id) return false;
        if (isChecked != that.isChecked) return false;
        if (owner != that.owner) return false;
        if (!itemValue.equals(that.itemValue)) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        return remindMe != null ? remindMe.equals(that.remindMe) : that.remindMe == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + itemValue.hashCode();
        result = 31 * result + (isChecked ? 1 : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + owner;
        result = 31 * result + (remindMe != null ? remindMe.hashCode() : 0);
        return result;
    }
}
