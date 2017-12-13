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

    this.setRemindMeTimer = function (id, date) {
        $.ajax({
            type: 'PUT',
            url: '/api/todo/setRemindMeTimer/' + id,
            contentType: 'application/json',
            data: date,
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
            }
        });
    };

    this.getAllLabels = function(){
        $.ajax({
            type: 'GET',
            url: '/api/todo/getAllLabels',
            success: function(response){
                printLabelsArray(response);
            }
        });
    };

    this.getAllLabelsForEveryItem = function(id){
        $.ajax({
            type: 'GET',
            url: '/api/todo/getAllLabelsForEveryItem/' + id,
            success: function(response){
                printLabelsForEveryItemArray(response);
            }
        });
    };

    this.insertLabel = function(labelName) {
        $.ajax({
            type: 'POST',
            url: '/api/todo/insertNewLabel',
            contentType: 'application/json',
            data: JSON.stringify({"labelName" : labelName}),
            success: function (response) {
                toDoRepository.getAllLabels();
            }
        });
    };

    this.deleteLabel = function (id) {
        $.ajax({
            type: 'DELETE',
            url: '/api/todo/deleteLabel/' + id,
            success: function () {
                toDoRepository.getAllLabels();
                toDoRepository.getItems();
            }
        });
    };

    this.getItemsByLabelId = function(id){
        $.ajax({
            type: 'GET',
            url: '/api/todo/getAllItemsByLabelId/' + id,
            success: function(response){
                printFullArray(response);
            }
        });
    };

    this.addLabelToItem = function (itemId, labelId) {
        $.ajax({
            type: 'PUT',
            url: '/api/todo/addLabelToItem/' + itemId,
            contentType: 'application/json',
            data: JSON.stringify({"labelId" : labelId}),
            success: function (response) {
                toDoRepository.getItems();
            }
        });
    }
}