"use strict";

function Item(id, text, check, childrenArray, labels){
    var item = this;
    var child = new ToDoList();
    var text = text;
    var id = id;

    this.element = prepareElement();

    function prepareDelBtn() {
        var btn = $("<BUTTON>");
        btn.text("X");
        btn.addClass("btn btn-danger");
        btn.click(function(){
            toDoRepository.deleteItem(item.element.attr("id"));
            item.element.remove();
        });
        return btn;
    }

    function prepareEditBtn() {
        var btn = $("<BUTTON>");
        btn.text("Edit");
        btn.addClass("btn btn-warning");
        btn.click(function () {
            editItemValue();
        });
        return btn;
    }

    function prepareCreateSubList() {
        var btn = $("<BUTTON>");
        btn.text("+");
        btn.addClass("btn btn-success");
        btn.click(function(){
            item.element.append(new ToDoList().element);
        });
        return btn;
    }

    function prepareAddLabel() {
        var dropdown = $("<DIV>");
        dropdown.attr("class", "dropdown");
        var dropdownMenuButton = $("<BUTTON>");
        dropdownMenuButton.attr("class", "btn btn-secondary dropdown-toggle");
        dropdownMenuButton.attr("type", "button");
        dropdownMenuButton.attr("id", "dropdownMenuButton");
        dropdownMenuButton.attr("data-toggle", "dropdown");
        dropdownMenuButton.attr("aria-haspopup", "true");
        dropdownMenuButton.attr("aria-expanded", "false");
        dropdownMenuButton.text("AddLabel");
        var dropdownMenu = $("<DIV>");
        dropdownMenu.attr("class", "dropdown-menu to-do-item-labels");
        dropdownMenu.attr("aria-labelledby", "dropdownMenuButton");
        dropdownMenuButton.click(function () {
            toDoRepository.getAllLabelsForEveryItem(id);
        });
        dropdown.append(dropdownMenu);
        dropdown.append(dropdownMenuButton);

        return dropdown;
    }

    // function clearLabelsList() {
    //     $(".labelClass").remove();
    // }

    function prepareElement() {
        var elem = $("<LI>");
        var card = $("<DIV>");
        var cardBody = $("<DIV>");
        var btnGroup = $("<DIV>");
        var subList = $("<DIV>");

        //elem.addClass("list-group-item");

        card.addClass("card");
        cardBody.addClass("card-body");
        card.append(cardBody);
        cardBody.append(prepareCheckbox());
        cardBody.append(text);
        elem.attr("id", id);
        btnGroup.append(prepareEditBtn());
        btnGroup.append(prepareDelBtn());
        btnGroup.append(prepareAddLabel());
        btnGroup.append(prepareCreateSubList());
        cardBody.append(btnGroup);
        cardBody.append(subList);
        subList.addClass("sub-list");
        btnGroup.addClass("btn-group");
        if(check){
            elem.addClass("done");
        }
        if (labels.length !== 0){
            var cardFooter = printLabels(labels);
            card.append(cardFooter);
        }
        if(childrenArray.length !== 0){
            createChildren(childrenArray);
            subList.append(child.element);
        }
        elem.append(card);
        return elem;
    }

    function prepareCheckbox(){
        var cb = $("<INPUT>", {type: "checkbox"});
        cb.prop("checked", check);
        cb.change(function(){
            if(this.checked){
                item.element.addClass("done");
                console.log(item.element.attr("id"));
                toDoRepository.checkItem(item.element.attr("id"));
            } else {
                item.element.removeClass("done");
                console.log(item.element.attr("id"));
                toDoRepository.checkItem(item.element.attr("id"));
            }
        });
        return cb;
    }

    function printLabels(labels) {
        var cardFooter = $("<DIV>");
        cardFooter.addClass("card-footer");
        for (var i = 0; i < labels.length; i++){
            var label = new LabelForLabelsListInItemFooter(labels[i].id, labels[i].labelName, labels[i].owner);
            cardFooter.append(label.element);
        }
        return cardFooter;
    }

    function createChildren(children) {
        for(var i = 0; i < children.length; i++){
            child.addItem(children[i].id, children[i].itemValue, children[i].checked, children[i].children, children[i].labels);
        }
    }

    function editItemValue() {
        var input = $("<INPUT>");
        item.element.prepend(input);
        input.keydown(function(e){
            if(e.keyCode === 13 && this.value){
                toDoRepository.editItemValue(id, this.value);
                input.remove;
            }
        });
    }
}