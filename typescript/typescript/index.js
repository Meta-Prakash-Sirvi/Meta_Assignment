"use strict";

var Gender;
(function (Gender) {
 Gender["Male"] = "Male";
 Gender["Female"] = "Female";
 Gender["Other"] = "Other";
})(Gender || (Gender = {}));
var VehicleType;
(function (VehicleType) {
 VehicleType["Cycle"] = "Cycle";
 VehicleType["Motorcycle"] = "Motorcycle";
 VehicleType["FourWheeler"] = "Four Wheeler";
})(VehicleType || (VehicleType = {}));
var PlanType;
(function (PlanType) {
 PlanType["Daily"] = "Daily";
 PlanType["Monthly"] = "Monthly";
 PlanType["Yearly"] = "Yearly";
})(PlanType || (PlanType = {}));
var Currency;
(function (Currency) {
 Currency["INR"] = "INR";
 Currency["USD"] = "USD";
 Currency["YEN"] = "YEN";
})(Currency || (Currency = {}));
// --- Constants ---
const PRICING_INR = {
 [VehicleType.Cycle]: {
 [PlanType.Daily]: 5,
 [PlanType.Monthly]: 100,
 [PlanType.Yearly]: 500,
 },
 [VehicleType.Motorcycle]: {
 [PlanType.Daily]: 10,
 [PlanType.Monthly]: 200,
 [PlanType.Yearly]: 1000,
 },
 [VehicleType.FourWheeler]: {
 [PlanType.Daily]: 20,
 [PlanType.Monthly]: 500,
 [PlanType.Yearly]: 3500,
 },
};
const EXCHANGE_RATES = {
 [Currency.INR]: 1,
 [Currency.USD]: 0.012,
 [Currency.YEN]: 1.82,
};
// --- Classes ---
class Vehicle {
 constructor(make, model, type) {
 this.make = make;
 this.model = model;
 this.type = type;
 }
 getDisplayName() {
 return `${this.make} ${this.model} (${this.type})`;
 }
}
class Pass {
 constructor(vehicleType, planType, currency) {
 this.vehicleType = vehicleType;
 this.planType = planType;
 this.currency = currency;
 this.priceINR = 0;
 this.priceConverted = 0;
 this.priceUSD = 0;
 this.calculatePrices();
 }
 calculatePrices() {
 if (!PRICING_INR[this.vehicleType] ||
 !PRICING_INR[this.vehicleType][this.planType]) {
 console.error("Invalid vehicle type or plan type for pricing.");
 return;
 }
 this.priceINR = PRICING_INR[this.vehicleType][this.planType];
 this.priceConverted = parseFloat((this.priceINR * EXCHANGE_RATES[this.currency]).toFixed(2));
 this.priceUSD = parseFloat((this.priceINR * EXCHANGE_RATES[Currency.USD]).toFixed(2));
 }
 getDisplayDetailsHTML() {
 return `
 <p>Selected Vehicle Type: ${this.vehicleType}</p>
 <p>Plan Type: ${this.planType}</p>
 <p>Price in ${this.currency}: ${this.priceConverted.toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 })}</p>
 <p style="color: green;">Stored Price (USD): $${this.priceUSD.toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 })}</p>
 `;
 }
}
class Employee {
 constructor(fullName, email, contact, gender) {
 this.fullName = fullName;
 this.email = email;
 this.contact = contact;
 this.gender = gender;
 this.employeeId = Employee.generateEmployeeId();
 }
 static generateEmployeeId() {
 return "EMP2025" + Math.floor(1000 + Math.random() * 9000);
 }
}

function getInputElement(id) {
 const el = document.getElementById(id);
 if (!el)
 throw new Error(`Element with ID "${id}" not found or not an input.`);
 return el;
}
function getElement(id) {
 const el = document.getElementById(id);
 if (!el)
 throw new Error(`Element with ID "${id}" not found.`);
 return el;
}
function getSpanElement(id) {
 const el = document.getElementById(id);
 if (!el)
 throw new Error(`Element with ID "${id}" not found or not a span.`);
 return el;
}

function displayError(errorElement, message) {
 errorElement.textContent = message;
 errorElement.style.display = "inline";
}
function clearError(errorElement) {
 errorElement.style.display = "none";
 errorElement.textContent = "";
}
function checkValidationName() {
 const val = getInputElement("fname").value.trim();
 const err = getSpanElement("name-error");
 const regex = /^([a-zA-Z]+(?:[\s-']?[a-zA-Z]+)*){1,3}$/;
 if (!val) {
 displayError(err, "Full name is required.");
 return false;
 }
 if (val.length < 2) {
 displayError(err, "Name must be at least 2 characters.");
 return false;
 }
 if (!regex.test(val)) {
 displayError(err, "Invalid full name format. Use letters, spaces, hyphens, apostrophes.");
 return false;
 }
 clearError(err);
 return true;
}
function checkEmail() {
 const val = getInputElement("Email").value.trim();
 const err = getSpanElement("email-error");
 const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
 if (!val) {
 displayError(err, "Email is required.");
 return false;
 }
 if (!regex.test(val)) {
 displayError(err, "Invalid email format.");
 return false;
 }
 clearError(err);
 return true;
}
function evaluatePasswordStrength(password) {
 if (password.length < 8)
 return "invalid"; 
 let score = 0;
 if (password.length >= 8)
 score++; 
 if (/[A-Z]/.test(password))
 score++;
 if (/[a-z]/.test(password))
 score++;
 if (/\d/.test(password))
 score++;
 if (/[@$!%*?&#]/.test(password))
 score++;
 if (score <= 2)
 return "weak"; 
 if (score === 3 || score === 4)
 return "normal"; 
 if (score >= 5)
 return "strong"; 
 return "weak"; 
}
function checkPassword() {
 const val = getInputElement("password").value;
 const err = getSpanElement("password-error");
 if (!val) {
 displayError(err, "Password is required.");
 return false;
 }
 if (val.length < 8) {
 displayError(err, "Password must be at least 8 characters.");
 return false;
 }
 const strength = evaluatePasswordStrength(val);
 if (strength === "weak") {
 displayError(err, "Password strength: Weak. Needs uppercase, lowercase, number, and symbol.");
 
 }
 else if (strength === "normal") {
 displayError(err, "Password strength: Normal. Consider adding more complexity.");
 
 }
 else if (strength === "strong") {
 clearError(err); // Strong is good
 }

 return true;
}
function checkConfirmPassword() {
 const pwd = getInputElement("password").value;
 const conf = getInputElement("conpassword").value;
 const err = getSpanElement("conpassword-error");
 if (!conf) {
 displayError(err, "Confirm password is required.");
 return false;
 }
 if (pwd !== conf) {
 displayError(err, "Passwords do not match.");
 return false;
 }
 clearError(err);
 return true;
}
function checkContact() {
 const val = getInputElement("Contact").value.trim();
 const err = getSpanElement("contact-error");
 const regex = /^[0-9]{10}$/;
 if (!val) {
 displayError(err, "Contact number is required.");
 return false;
 }
 if (!regex.test(val)) {
 displayError(err, "Enter a valid 10-digit number.");
 return false;
 }
 clearError(err);
 return true;
}

const formSteps = document.querySelectorAll(".form-step");
formSteps.forEach((step, index) => (step.style.display = index === 0 ? "block" : "none"));
const vehicleSteps = document.querySelectorAll(".form-vehicle");
vehicleSteps.forEach((step, index) => (step.style.display = index === 0 ? "block" : "none"));
function moveToNext(nextId, currId, isVehicleForm = false) {
 let isValid = true;
 if (!isVehicleForm) {
 
 if (currId === "step-1")
 isValid = checkValidationName();
 else if (currId === "step-3")
 isValid = checkEmail();
 else if (currId === "step-4")
 isValid =
 checkPassword();
 else if (currId === "step-5")
 isValid = checkConfirmPassword();
 else if (currId === "step-6")
 isValid = checkContact(); 
 }
 else {

 }
 if (!isValid)
 return;
 const currentStep = document.getElementById(currId);
 const nextStep = document.getElementById(nextId);
 if (currentStep)
 currentStep.style.display = "none";
 if (nextStep)
 nextStep.style.display = "block";
}

function handleSubmitEmployee() {

 const isNameValid = checkValidationName();
 const isEmailValid = checkEmail();
 const isPasswordValid = checkPassword();
 const isConfirmPasswordValid = checkConfirmPassword();
 const isContactValid = checkContact();
 
 if (isNameValid &&
 isEmailValid &&
 isPasswordValid &&
 isConfirmPasswordValid &&
 isContactValid) {
 const fullName = getInputElement("fname").value.trim();
 const email = getInputElement("Email").value.trim();
 const contact = getInputElement("Contact").value.trim();
 const genderInput = document.querySelector('input[name="Gender"]:checked');
 const gender = genderInput ? genderInput.value : undefined; 
 const newEmployee = new Employee(fullName, email, contact, gender);
 getElement("employee-id-display").textContent =
 "Employee ID: " + newEmployee.employeeId;
 const employeeForm = document.querySelector(".add_employee_form");
 if (employeeForm)
 employeeForm.style.display = "none";
 console.log("New Employee Created:", newEmployee);

 }
 else {

 }
}
function calculateAndShowPass() {
 const vehicleTypeInput = document.querySelector('input[name="vehicle-type"]:checked');
 const planTypeInput = document.querySelector('input[name="plan-type"]:checked');
 const currencySelect = getInputElement("currency-select"); 
 const resultDiv = getElement("final-pass");
 if (!vehicleTypeInput || !planTypeInput) {
 resultDiv.innerText = "Please select vehicle and plan type.";
 return;
 }
 const vehicleType = vehicleTypeInput.value;
 const planType = planTypeInput.value;
 const currency = currencySelect.value;
 const vehicleMake = getInputElement("vehicle-make").value.trim() || "N/A";
 const vehicleModel = getInputElement("vehicle-model").value.trim() || "N/A";

 resultDiv.innerHTML = pass.getDisplayDetailsHTML();
 if (vehicleMake !== "N/A" || vehicleModel !== "N/A") {
 resultDiv.innerHTML =
 `<p>Vehicle: ${vehicleMake} ${vehicleModel}</p>` + resultDiv.innerHTML;
 }
 console.log("Pass Generated:", pass);

}

document.addEventListener("DOMContentLoaded", () => {

 getInputElement("fname").addEventListener("input", () => clearError(getSpanElement("name-error")));
 getInputElement("Email").addEventListener("input", () => clearError(getSpanElement("email-error")));
 getInputElement("password").addEventListener("input", () => {
 clearError(getSpanElement("password-error"));
 checkPassword(); 
 });
 getInputElement("conpassword").addEventListener("input", () => clearError(getSpanElement("conpassword-error")));
 getInputElement("Contact").addEventListener("input", () => {
 clearError(getSpanElement("contact-error"));
 checkContact(); 
 });
 
 window.moveToNext = moveToNext;
 window.checkValidationName = checkValidationName;
 window.checkEmail = checkEmail;
 window.checkPassword = checkPassword;
 window.checkConfirmPassword = checkConfirmPassword;
 window.checkContact = checkContact;
 window.handleSubmitEmployee = handleSubmitEmployee;
 window.calculateAndShowPass = calculateAndShowPass;
});