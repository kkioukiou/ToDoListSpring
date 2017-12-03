function LabelForAllLabelsList(id, labelName, owner) {
    var label = new Label(id, labelName, owner);
    this.element = label.element;

    label.element.click(function () {
        toDoRepository.getItemsByLabelId(id);
    });
}
