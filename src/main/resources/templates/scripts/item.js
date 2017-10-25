"use strict";

function Item(id, text, check, childrenArray){
    var item = this;
    var child = new ToDoList();
    var text = text;

    this.element = prepareElement();

    function prepareDelBtn() {
        var btn = $("<BUTTON>");
        btn.text("X");
        btn.addClass("btn btn-danger");
        btn.click(function(){
            toDoRepository.deleteItem(item.element.attr("id"));
            item.element.remove();
        });
        return btn;
    }

    function prepareEditBtn() {
        var btn = $("<BUTTON>");
        btn.text("Edit");
        btn.addClass("btn btn-warning");
        btn.click(editItemValue());
        return btn;
    }

    function prepareCreateSubList() {
        var btn = $("<BUTTON>");
        btn.text("+");
        btn.addClass("btn btn-success");
        btn.click(function(){
            item.element.append(new ToDoList().element);
        });
        return btn;
    }

    function prepareElement() {
        var elem = $("<LI>");
        var div = $("<DIV>");
        var subList = $("<DIV>");

        //elem.addClass("list-group-item");
        elem.append(prepareCheckbox());
        elem.append(text);
        elem.attr("id", id);
        div.append(prepareEditBtn());
        div.append(prepareDelBtn());
        div.append(prepareCreateSubList());
        elem.append(div);
        elem.append(subList);
        subList.addClass("sub-list");
        div.addClass("btn-group");
        if(check){
            elem.addClass("done");
        }
        if(childrenArray.length !== 0){
            createChildren(childrenArray);
            subList.append(child.element);
        }
        return elem;
    }

    function prepareCheckbox(){
        var cb = $("<INPUT>", {type: "checkbox"});
        cb.prop("checked", check);
        cb.change(function(){
            if(this.checked){
                item.element.addClass("done");
                toDoRepository.checkItem(item.element.attr("id"));
            } else {
                item.element.removeClass("done");
                toDoRepository.checkItem(item.element.attr("id"));
            }
        });
        return cb;
    }

    function createChildren(children) {
        for(var i = 0; i < children.length; i++){
            child.addItem(children[i].id, children[i].itemValue, children[i].checked, children[i].children);
        }
    }

    function editItemValue() {
        var id = item.element.attr("id");
        var input = $("<INPUT>");
        item.element.append(input);
        input.keydown(function(e){
            if(e.keyCode === 13 && this.value){
                toDoRepository.editItemValue(id, this.value);
                input.remove;
            }
        });
    }
}