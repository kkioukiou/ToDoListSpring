"use strict";

function ToDoRepository(){

    this.getItems = function(){
        $.ajax({
            type: 'GET',
            url: '/api/todo/items',
            success: function(response){
                printFullArray(response);
            }
        });
    };

    this.insertItem = function(value) {
        $.ajax({
            type: 'POST',
            url: '/api/todo/item',
            contentType: 'text/plain',
            data: value,
            success: function (response) {
                console.log(response);
                toDoRepository.getItems();
            }
        });
    };

    this.insertChildItem = function(parentId, itemValue) {
        $.ajax({
            type: 'POST',
            url: '/api/todo/item',
            contentType: 'application/json',
            data: JSON.stringify({"parentId" : parentId, "itemValue" : itemValue}),
            success: function (response) {
                console.log(response);
                toDoRepository.getItems();
            }
        });
    };

    this.deleteItem = function(id) {
        $.ajax({
            type: 'DELETE',
            url: '/api/todo/item/' + id
        });
    };

    this.checkItem = function(id){
        $.ajax({
            type: 'PUT',
            url: '/api/todo/item',
            contentType: 'application/json',
            data: id,
            success: function (response) {
                console.log(response);
            }
        });
    }
}

