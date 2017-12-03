"use strict";

function Label(id, labelName, owner) {
    var label = this;
    var id = id;
    var labelName = labelName;
    var owner = owner;

    this.element = prepareElement();

    function prepareDelBtn() {
        var btn = $("<BUTTON>");
        btn.text("X");
        btn.addClass("btn btn-danger");
        btn.click(function(){
            toDoRepository.deleteLabel(label.element.attr("id"));
            label.element.remove();
        });
        return btn;
    }

    function prepareElement() {
        var button = $("<BUTTON>");
        button.attr("id", id);
        button.attr("class", "dropdown-item labelClass");
        button.attr("type", "button");
        button.append(labelName);
        button.append(prepareDelBtn());
        return button;
    }

}