"use strict";

function ToDoList(){
    var list = this;
    this.element = $("<UL>");

    function prepareAdd() {
        var input = $("<INPUT>");
        input.keydown(function(e){
            if(e.keyCode === 13 && this.value){
                if (input.closest("li").attr("id") > 0){
                    toDoRepository.insertChildItem(input.closest("li").attr("id"), this.value);
                } else {
                    toDoRepository.insertItem(this.value);
                }
                input.val("");
            }
        });
        return input;
    }

    this.element.append(prepareAdd());

    this.addItem = function (id, text, check, children, labels, remindMe){
        var item = new Item(id, text, check, children, labels, remindMe);
        list.element.append(item.element);
    };
}