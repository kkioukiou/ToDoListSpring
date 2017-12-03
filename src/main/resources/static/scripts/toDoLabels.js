"use strict";

function ToDoLabels() {
    var labels = this;
    this.element = $("<DIV>");

    function prepareAdd() {
        var input = $("<INPUT>");
        input.keydown(function(e){
            if(e.keyCode === 13 && this.value){
                toDoRepository.insertLabel(this.value);
                input.val("");
            }
        });
        return input;
    }

    this.element.append(prepareAdd());

    this.addLabel = function (id, labelName, owner) {
        var label = new LabelForAllLabelsList(id, labelName, owner);
        labels.element.append(label.element);
    }
}