//Đối tượng Validator
function Validator(options) {
    // Lấy element của form cần validate
    var formElement = document.querySelector(options.form);

    // Hàm thực hiện thay đổi thông báo lỗi
    function validate(inputElement, rule) {
        var errorElement = inputElement.parentElement.querySelector(options.errorSelector);
        var errorMessage = rule.test(inputElement.value);

        if (errorMessage) {
            errorElement.innerText = errorMessage;
            inputElement.parentElement.classList.add('invalid');
        } else {
            errorElement.innerText = '';
            inputElement.parentElement.classList.remove('invalid');
        }
    }

    if (formElement) {

        options.rules.forEach(function (rule) {
            var inputElement = formElement.querySelector(rule.selector);

            if (inputElement) {
                // Xử lý trường hợp blur khỏi input
                inputElement.onblur = function () {
                    // Gọi hàm validate khi blur ra khỏi input
                    validate(inputElement, rule);
                }

                // Xử lý trường hợp mỗi khi người dùng nhập vào input
                inputElement.oninput = function () {
                    // console.log(inputElement.parentElement.querySelector('.form-message')); Lấy thẻ span từ thẻ cha form-group
                    var errorElement = inputElement.parentElement.querySelector(options.errorSelector);
                    errorElement.innerText = '';
                    inputElement.parentElement.classList.remove('invalid');
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

Validator.minLength = function (selector, min) {
    return {
        selector: selector,
        test: function (value) {
            return value.length >= min ? undefined : 'Vui lòng nhập tối thiểu ' + min + ' ký tự';
        }
    }
}