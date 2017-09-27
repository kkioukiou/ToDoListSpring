"use strict";

function Item(id, text, check){
    var item = this;

    this.element = prepareElement();

    function prepareDelBtn() {
        var btn = $("<BUTTON>");
        btn.text("X");
        btn.addClass("btn btn-danger")
        btn.click(function(){
            toDoRepository.deleteItem(item.element.attr("id"));
            item.element.remove();
        });
        return btn;
    }

    function createSubList() {
        var btn = $("<BUTTON>");
        btn.text("+");
        btn.addClass("btn btn-success")
        btn.click(function(){
            item.element.append(new ToDoList().element);
        });
        return btn;
    }

    function prepareElement() {
        var elem = $("<LI>");
        var div = $("<DIV>");

        //elem.addClass("list-group-item");
        elem.append(prepareCheckbox());
        elem.append(text);
        elem.attr("id", id);
        div.append(prepareDelBtn());
        div.append(createSubList());
        elem.append(div);
        div.addClass("btn-group");
        if(check){
            elem.addClass("done");
        }
        return elem;
    }

    function prepareCheckbox(){
        var cb = $("<INPUT>", {type: "checkbox"});
        cb.prop("checked", check);
        cb.change(function(e){
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
}