window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

ajax = function (data, success) {
    return $.ajax({
        type: "POST",
        url: "",
        dataType: "json",
        data: data,
        success: function (response) {
            if (response["redirect"]) {
                location.href = response["redirect"];
            } else {
                success(response);
            }
        }
    })
};
