var FormValidator = {
    _controls: {},

    addControl: function (controlId, validateFunc) {
        this._controls[controlId] = validateFunc;
    },

    validate: function () {
        for (var ctrl in this._controls) {
            console.log(ctrl + " " + this._controls[ctrl](ctrl));
        }
    },

    logValidatorName: function () {
        console.log('FormValidator');
    }
};

var UserFormValidator = {
    __proto__: FormValidator,

    parseForm: function (form) {
        var inputs = form.getElementsByTagName('input');

        for (var i = 0; i < inputs.length; i++) {
            if (inputs[i].hasAttribute('required')) {
                var input = inputs[i];
                this.addControl(input.id, function (input) {
                    return !!document.getElementById(input).value;
                });
            }
        }
    },

    logValidatorName: function () {
        console.log('UserFormValidator');
    }
};


function validateForm(form) {
    var validator = UserFormValidator;
    validator.logValidatorName();
    validator.parseForm(form);
    validator.validate();
    return false;
}