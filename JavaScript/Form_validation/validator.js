//Đối tượng Validator
function Validator(options) {
    // Hàm thực hiện thay đổi thông báo lỗi
    function validate(inputElement, rule) {
        var errorElement = inputElement.parentElement.querySelector('.form-message');
        var errorMessage = rule.test(inputElement.value);

        if (errorMessage) {
            errorElement.innerText = errorMessage;
            inputElement.parentElement.classList.add('invalid');
        } else {
            errorElement.innerText = '';
            inputElement.parentElement.classList.remove('invalid');
        }
    }

    // Lấy element của form cần validate
    var formElement = document.querySelector(options.form);

    if (formElement) {

        options.rules.forEach(function (rule) {
            var inputElement = formElement.querySelector(rule.selector);
            // console.log(inputElement.parentElement.querySelector('.form-message')); Lấy thẻ span từ thẻ cha form-group
            var errorElement = inputElement.parentElement.querySelector('.form-message');

            if (inputElement) {
                inputElement.onblur = function () {
                    // Gọi hàm validate khi blur ra khỏi input
                    validate(inputElement, rule);
                }
            }
        });
    }
}

//Định nghĩa các rules
//Nguyên tắc của các rules:
//1. Khi có lỗi => trả ra message lỗi
//2. Khi hợp lệ => không trả ra cái gì cả (underfined)
Validator.isRequired = function (selector) {
    return {
        selector: selector,
        test: function (value) {
            return value.trim() ? undefined : 'Vui lòng nhập trường này';
        }
    }
}

Validator.isEmail = function (selector) {
    return {
        selector: selector,
        test: function (value) {
            var regex =
                /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            return regex.test(value) ? undefined : 'Trường này phải là email';
        }
    }
}