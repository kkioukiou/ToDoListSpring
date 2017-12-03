function LabelForLabelsListInItemFooter(id, labelName, owner) {
    var id = id;
    var labelName = labelName;
    var owner = owner;

    this.element = prepareElement();

    function prepareElement() {
        var span = $("<SPAN>");
        span.attr("id", id);
        span.addClass("badge badge-primary");
        span.text(labelName);

        return span;
    }

}