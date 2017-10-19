"use strict";

function printFullArray(array) {
    clearOutPrint();
    for (var i = 0; i < array.length; i++){
        list.addItem(array[i].id, array[i].itemValue, array[i].checked, array[i].children);
    }
}

function clearOutPrint(){
    $('#to-do-list li').remove();
}

var list = new ToDoList();
var toDoRepository = new ToDoRepository();
toDoRepository.getItems();

$("#to-do-list").append(list.element);