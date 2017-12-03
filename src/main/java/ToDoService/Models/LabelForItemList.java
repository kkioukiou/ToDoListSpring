package ToDoService.Models;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "labels")
public class LabelForItemList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int labelId;

    @Column(name = "label_name")
    private String labelName;

    @Column(name = "owner")
    private int owner;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabelForItemList that = (LabelForItemList) o;

        if (labelId != that.labelId) return false;
        if (owner != that.owner) return false;
        return labelName.equals(that.labelName);
    }

    @Override
    public int hashCode() {
        int result = labelId;
        result = 31 * result + labelName.hashCode();
        result = 31 * result + owner;
        return result;
    }
}
