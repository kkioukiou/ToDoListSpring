"use strict";

function ToDoRepository(){

    this.getItems = function(owner){
        $.ajax({
            type: 'GET',
            url: '/api/todo/item/getAllItems/' + owner,
            success: function(response){
                printFullArray(response);
            }
        });
    };

    this.insertItem = function(itemValue, owner) {
        $.ajax({
            type: 'POST',
            url: '/api/todo/item/insertNewItem/',
            contentType: 'application/json',
            data: JSON.stringify({"itemValue" : itemValue, "owner" : owner}),
            success: function (response) {
                console.log(owner);
                toDoRepository.getItems(owner);
            }
        });
    };

    this.insertChildItem = function(parentId, itemValue, owner) {
        $.ajax({
            type: 'POST',
            url: '/api/todo/item/insertChild/',
            contentType: 'application/json',
            data: JSON.stringify({"parentId" : parentId, "itemValue" : itemValue, "owner" : owner}),
            success: function (response) {
                console.log(owner);
                toDoRepository.getItems(owner);
            }
        });
    };

    this.editItemValue = function (id, newValue, owner) {
        $.ajax({
            type: 'PUT',
            url: '/api/todo/item/edit/',
            contentType: 'application/json',
            data: JSON.stringify({"id" : id, "itemValue" : newValue, "owner" : owner}),
            success: function (response) {
                console.log(response);
                toDoRepository.getItems(owner);
            }
        });
    };

    this.deleteItem = function(id) {
        $.ajax({
            type: 'DELETE',
            url: '/api/todo/item/delete/' + id
        });
    };

    this.checkItem = function(id){
        $.ajax({
            type: 'PUT',
            url: '/api/todo/item/check/',
            contentType: 'application/json',
            data: JSON.stringify({"id" : id}),
            success: function (response) {
                console.log(response);
            }
        });
    }
}

