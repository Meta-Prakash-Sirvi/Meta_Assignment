const formSteps = document.querySelectorAll(".form-step");
formSteps.forEach((step, index) => step.style.display = index === 0 ? "block" : "none");

const vehicleSteps = document.querySelectorAll(".form-vehicle");
vehicleSteps.forEach((step, index) => step.style.display = index === 0 ? "block" : "none");



function checkValidationName() {
    const val = document.getElementById("fname").value.trim();
    const err = document.getElementById("name-error");
    const regex = /^([a-zA-Z]+[\s-']?){1,3}$/;
    if (!val) {
        err.textContent = "Full name is required.";
        return false;
    }
    if (!regex.test(val)) {
        err.textContent = "Invalid full name format.";
        return false;
    }

    if (val.length < 2) {
        err.textContent = "name must be greater than 2";
        return false;
    }
    err.style.display = "none";


    return true;
}

function checkEmail() {
    const val = document.getElementById("Email").value.trim();
    const err = document.getElementById("email-error");
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!val) {
        return err.textContent = "Email is required.";
        return false;
    }
    if (!regex.test(val)) {
        err.textContent = "Invalid email format.";
        return false;
    }

    return true;
}



function checkPassword() {
    const val = document.getElementById("password").value;
    const err = document.getElementById("password-error");
    const passwordInput = document.getElementById("password");


    if (!val) {
        err.textContent = "Password is required.";
        passwordInput.style.borderColor = "red";
        return false;
    }


    if (val.length < 8) {
        err.textContent = "Password must be at least 8 characters.";
        passwordInput.style.borderColor = "red";
        return false;
    }


    const strength = evaluatePasswordStrength(val);


    if (strength === "weak") {
        passwordInput.style.borderColor = "red";
        err.textContent = "Password strength: Weak";
    } else if (strength === "normal") {
        passwordInput.style.borderColor = "orange";
        err.textContent = "Password strength: Normal";
    } else if (strength === "strong") {
        passwordInput.style.borderColor = "green";
        err.textContent = "Password strength: Strong";
    }

    return true;
}


function evaluatePasswordStrength(password) {
    let score = 0;

    if (password.length >= 8) score++;
    if (/[A-Z]/.test(password)) score++;
    if (/[a-z]/.test(password)) score++;
    if (/\d/.test(password)) score++;
    if (/[@$!%*?&#]/.test(password)) score++;


    if (score <= 2) {
        return "weak";
    }

    if (score === 3 || score === 4) {
        return "normal";
    }

    if (score === 5) {
        return "strong";
    }

}


function checkConfirmPassword() {
    const pwd = document.getElementById("password").value;
    const conf = document.getElementById("conpassword").value;
    const err = document.getElementById("conpassword-error");
    if (!conf) {
        err.textContent = "Confirm password is required.";
        return false;
    }

    if (pwd !== conf) {
        err.textContent = "Passwords do not match.";
        return false;
    }


    return true;
}

function checkContact() {
    const val = document.getElementById("Contact").value.trim();
    const err = document.getElementById("contact-error");
    const regex = /^[0-9]{10}$/;
    if (!val) {
        return err.textContent = "Contact number is required.", false;
    }
    if (!regex.test(val)) {
        return err.textContent = "Enter a valid 10-digit number.", false;
    }
    if (val.length < 8) {
        return err.textContent = "enter more than 8 digit", false;
    }
    err.textContent = "";
    return true;
}

function generateEmployeeId() {
    return "EMP2025" + Math.floor(1000 + Math.random() * 9000);

}

function handleSubmit() {
    const empId = generateEmployeeId();
    document.getElementById("employee-id-display").textContent = "Employee ID: " + empId;
    document.querySelector(".add_employee_form").style.display = "none";
}

const pricingINR = {
    Cycle: { Daily: 5, Monthly: 100, Yearly: 500 },
    Motorcycle: { Daily: 10, Monthly: 200, Yearly: 1000 },
    "Four Wheeler": { Daily: 20, Monthly: 500, Yearly: 3500 },
};

const exchangeRates = {
    INR: 1,
    USD: 0.012,
    YEN: 1.82,
};

function calculateAndShowPass() {
    const vehicle = document.querySelector('input[name="vehicle-type"]:checked')?.value;
    const plan = document.querySelector('input[name="plan-type"]:checked')?.value;
    const currency = document.getElementById("currency-select").value;
    const result = document.getElementById("final-pass");

    if (!vehicle || !plan) {
        result.innerText = "Please select vehicle and plan type.";
        return;
    }

    const inr = pricingINR[vehicle][plan];
    const converted = (inr * exchangeRates[currency]).toFixed(2);
    const usd = (inr * exchangeRates["USD"]).toFixed(2);

    result.innerHTML = `
   
 <p>Selected Vehicle: ${vehicle}</p>
 <p>Plan Type: ${plan}</p>
 <p>Price in ${currency}: ${converted}</p>
 <p style="color: green;">Stored Price (USD): $${usd}</p>
 `;
}

function moveToNext(nextId, currId) {

    if (currId === "step-1" && !checkValidationName()) return;
    if (currId === "step-3" && !checkEmail()) return;
    if (currId === "step-4" && !checkPassword()) return;
    if (currId === "step-5" && !checkConfirmPassword()) return;
    if (currId === "step-6" && !checkContact()) return;


    const curr = document.getElementById(currId);
    const next = document.getElementById(nextId);
    if (curr) curr.style.display = "none";
    if (next) next.style.display = "block";
}

