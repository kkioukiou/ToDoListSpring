"use strict";

function printFullArray(array) {
    clearOutPrint();
    for (var i = 0; i < array.length; i++){
        list.addItem(array[i].id, array[i].itemValue, array[i].checked, array[i].children, array[i].labels, array[i].remindMe);
    }
}

function clearOutPrint(){
    $('.to-do-list li').remove();
}

function clearLabelsList() {
    $(".labelClass").remove();
}

function printLabelsArray(array) {
    clearLabelsList();
    for (var i = 0; i < array.length; i++){
        labels.addLabel(array[i].labelId, array[i].labelName, array[i].owner);
    }
}

function printLabelsForEveryItemArray(array) {
    clearLabelsListForEveryItem();
    for (var i = 0; i < array.length; i++){
        var label = new LabelsForAddLabelToItem(array[i].labelId, array[i].labelName, array[i].owner);
        $(".to-do-item-labels").append(label.element);
    }
}

function clearLabelsListForEveryItem() {
    $(".labels-for-every-item").remove();
}

var labels = new ToDoLabels();
var list = new ToDoList();
var toDoRepository = new ToDoRepository();
toDoRepository.getAllLabels();
toDoRepository.getItems();

$(".to-do-labels").append(labels.element);
$(".to-do-list").append(list.element);