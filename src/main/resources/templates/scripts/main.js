"use strict";

function printFullArray(array) {
    clearOutPrint();
    $.each(array, function(index, toDoObject){
        list.addItem(toDoObject.id, toDoObject.itemValue, toDoObject.checked);
    });
}

function clearOutPrint(){
    $('#to-do-list li').remove();
}

var list = new ToDoList();
var toDoRepository = new ToDoRepository();
toDoRepository.getItems();

$("#to-do-list").append(list.element);