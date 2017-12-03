package ToDoService.Models;

import javax.persistence.*;
import java.io.Serializable;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDoListItemForLabelsList that = (ToDoListItemForLabelsList) o;

        if (id != that.id) return false;
        if (isChecked != that.isChecked) return false;
        if (owner != that.owner) return false;
        if (itemValue != null ? !itemValue.equals(that.itemValue) : that.itemValue != null) return false;
        return parentId != null ? parentId.equals(that.parentId) : that.parentId == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (itemValue != null ? itemValue.hashCode() : 0);
        result = 31 * result + (isChecked ? 1 : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + owner;
        return result;
    }
}
