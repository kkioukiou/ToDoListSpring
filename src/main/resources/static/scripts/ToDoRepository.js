"use strict";

function ToDoRepository(){

    this.getItems = function(){
        $.ajax({
            type: 'GET',
            url: '/api/todo/getAllItems',
            success: function(response){
                printFullArray(response);
            }
        });
    };

    this.insertItem = function(itemValue) {
        $.ajax({
            type: 'POST',
            url: '/api/todo/insertNewItem',
            contentType: 'application/json',
            data: JSON.stringify({"itemValue" : itemValue}),
            success: function (response) {
                toDoRepository.getItems();
            }
        });
    };

    this.insertChildItem = function(parentId, itemValue) {
        $.ajax({
            type: 'POST',
            url: '/api/todo/insertNewItem',
            contentType: 'application/json',
            data: JSON.stringify({"parentId" : parentId, "itemValue" : itemValue}),
            success: function (response) {
                toDoRepository.getItems();
            }
        });
    };

    this.editItemValue = function (id, newValue) {
        $.ajax({
            type: 'PUT',
            url: '/api/todo/edit',
            contentType: 'application/json',
            data: JSON.stringify({"id" : id, "itemValue" : newValue}),
            success: function (response) {
                console.log(response);
                toDoRepository.getItems();
            }
        });
    };

    this.deleteItem = function(id) {
        $.ajax({
            type: 'DELETE',
            url: '/api/todo/delete/' + id
        });
    };

    this.checkItem = function(id){
        $.ajax({
            type: 'PUT',
            url: '/api/todo/check',
            contentType: 'application/json',
            data: JSON.stringify({"id" : id}),
            success: function (response) {
                console.log(response);
            }
        });
    }
}