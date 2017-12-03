function LabelsForAddLabelToItem(id, labelName, owner) {
    var label = new Label(id, labelName, owner);
    label.element.attr("class", "dropdown-item labels-for-every-item");
    this.element = label.element;

    label.element.click(function () {
        var itemId = $(this.closest("li")).attr("id");
        toDoRepository.addLabelToItem(itemId, id);
    });
}