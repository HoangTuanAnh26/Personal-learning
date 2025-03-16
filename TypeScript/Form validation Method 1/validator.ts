interface ValidatorOptions {
    form: string;
    formGroupSelector: string;
    errorSelector: string;
    rules: Rule[];
    onSubmit?: (formValues: Record<string, any>) => void;
}

interface Rule {
    selector: string;
    test: (value: string) => string | undefined;
}

class Validator {
    private selectorRules: Record<string, ((value: string) => string | undefined)[]> = {};

    constructor(private options: ValidatorOptions) {
        const formElement = document.querySelector(this.options.form) as HTMLFormElement;
        if (formElement) {
            formElement.onsubmit = (e) => this.handleSubmit(e, formElement);

            this.options.rules.forEach((rule) => {
                if (Array.isArray(this.selectorRules[rule.selector])) {
                    this.selectorRules[rule.selector].push(rule.test);
                } else {
                    this.selectorRules[rule.selector] = [rule.test];
                }

                const inputElements = formElement.querySelectorAll(rule.selector);
                Array.from(inputElements).forEach((inputElement) => {
                    inputElement.addEventListener("blur", () => {
                        this.validate(inputElement as HTMLInputElement, rule);
                    });

                    inputElement.addEventListener("input", () => {
                        const errorElement = this.getParent(inputElement, this.options.formGroupSelector).querySelector(
                            this.options.errorSelector
                        ) as HTMLElement;
                        errorElement.innerText = '';
                        this.getParent(inputElement, this.options.formGroupSelector).classList.remove('invalid');
                    });
                });
            });
        }
    }

    private getParent(element: HTMLElement, selector: string): HTMLElement {
        while (element.parentElement) {
            if (element.parentElement.matches(selector)) {
                return element.parentElement;
            }
            element = element.parentElement;
        }
        return element;
    }

    private validate(inputElement: HTMLInputElement, rule: Rule): boolean {
        const errorElement = this.getParent(inputElement, this.options.formGroupSelector).querySelector(
            this.options.errorSelector
        ) as HTMLElement;
        let errorMessage = rule.test(inputElement.value);

        const rules = this.selectorRules[rule.selector];
        for (let i = 0; i < rules.length; i++) {
            switch (inputElement.type) {
                case 'radio':
                case 'checkbox':
                    errorMessage = rules[i](inputElement.checked ? inputElement.value : '');
                    break;
                default:
                    errorMessage = rules[i](inputElement.value);
            }

            if (errorMessage) break;
        }

        if (errorMessage) {
            errorElement.innerText = errorMessage;
            this.getParent(inputElement, this.options.formGroupSelector).classList.add('invalid');
        } else {
            errorElement.innerText = '';
            this.getParent(inputElement, this.options.formGroupSelector).classList.remove('invalid');
        }

        return !errorMessage;
    }

    private handleSubmit(e: Event, formElement: HTMLFormElement): void {
        e.preventDefault();

        let isFormValid = true;

        this.options.rules.forEach((rule) => {
            const inputElement = formElement.querySelector(rule.selector) as HTMLInputElement;
            const isValid = this.validate(inputElement, rule);
            if (!isValid) {
                isFormValid = false;
            }
        });

        if (isFormValid) {
            if (typeof this.options.onSubmit === 'function') {
                const enableInputs = formElement.querySelectorAll('[name]:not([disabled])');
                const formValues = Array.from(enableInputs).reduce((values, input: HTMLInputElement) => {
                    switch (input.type) {
                        case 'radio':
                            values[input.name] = formElement.querySelector(`input[name="${input.name}"]:checked`)?.value;
                            break;
                        case 'checkbox':
                            if (!input.matches(':checked')) {
                                values[input.name] = '';
                                return values;
                            }
                            if (!Array.isArray(values[input.name])) {
                                values[input.name] = [];
                            }
                            values[input.name].push(input.value);
                            break;
                        case 'file':
                            values[input.name] = input.files;
                            break;
                        default:
                            values[input.name] = input.value;
                    }
                    return values;
                }, {} as Record<string, any>);
                this.options.onSubmit(formValues);
            } else {
                formElement.submit();
            }
        }
    }
}

Validator.isRequired = function (selector: string, message: string) {
    return {
        selector: selector,
        test: (value: string) => (value ? undefined : message || 'Vui lòng nhập trường này'),
    };
};

Validator.isEmail = function (selector: string, message: string) {
    return {
        selector: selector,
        test: (value: string) => {
            const regex = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            return regex.test(value) ? undefined : message || 'Trường này phải là email';
        },
    };
};

Validator.minLength = function (selector: string, min: number, message: string) {
    return {
        selector: selector,
        test: (value: string) => (value.length >= min ? undefined : message || `Vui lòng nhập tối thiểu ${min} ký tự`),
    };
};

Validator.isConfirmed = function (selector: string, getConfirmValue: () => string, message: string) {
    return {
        selector: selector,
        test: (value: string) => (value === getConfirmValue() ? undefined : message || 'Giá trị nhập vào không chính xác'),
    };
};
