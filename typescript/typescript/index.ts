
enum Gender {
    Male = "Male",
    Female = "Female",
    Other = "Other", 
   }
   
   enum VehicleType {
    Cycle = "Cycle",
    Motorcycle = "Motorcycle",
    FourWheeler = "Four Wheeler",
   }
   
   enum PlanType {
    Daily = "Daily",
    Monthly = "Monthly",
    Yearly = "Yearly",
   }
   
   enum Currency {
    INR = "INR",
    USD = "USD",
    YEN = "YEN",
   }
   
  
   interface PricingPlan {
    [PlanType.Daily]: number;
    [PlanType.Monthly]: number;
    [PlanType.Yearly]: number;
   }
   
 
   
   interface PricingStructure {
    [VehicleType.Cycle]: PricingPlan;
    [VehicleType.Motorcycle]: PricingPlan;
    [VehicleType.FourWheeler]: PricingPlan;
   }
   
   
   
  
   type ExchangeRateStructure = Record<Currency, number>;
   
   // --- Constants ---
   const PRICING_INR: PricingStructure = {
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
   
   const EXCHANGE_RATES: ExchangeRateStructure = {
    [Currency.INR]: 1,
    [Currency.USD]: 0.012,
    [Currency.YEN]: 1.82,
   };
   
   // --- Classes ---
   
   class Vehicle {
    make: string;
    model: string;
    type: VehicleType;
   
    constructor(make: string, model: string, type: VehicleType) {
    this.make = make;
    this.model = model;
    this.type = type;
    }
   
    getDisplayName(): string {
    return `${this.make} ${this.model} (${this.type})`;
    }
   }
   
   class Pass {
    vehicleType: VehicleType;
    planType: PlanType;
    currency: Currency;
    priceINR: number;
    priceConverted: number;
    priceUSD: number; 
   
    constructor(
    vehicleType: VehicleType,
    planType: PlanType,
    currency: Currency
    ) {
    this.vehicleType = vehicleType;
    this.planType = planType;
    this.currency = currency;
    this.priceINR = 0;
    this.priceConverted = 0;
    this.priceUSD = 0;
    this.calculatePrices();
    }
   
    private calculatePrices(): void {
    if (
    !PRICING_INR[this.vehicleType] ||
    !PRICING_INR[this.vehicleType][this.planType]
    ) {
    console.error("Invalid vehicle type or plan type for pricing.");
    return;
    }
   
    this.priceINR = PRICING_INR[this.vehicleType][this.planType];
    this.priceConverted = parseFloat(
    (this.priceINR * EXCHANGE_RATES[this.currency]).toFixed(2)
    );
    this.priceUSD = parseFloat(
    (this.priceINR * EXCHANGE_RATES[Currency.USD]).toFixed(2)
    );
    }
   
    getDisplayDetailsHTML(): string {
    return `
    <p>Selected Vehicle Type: ${this.vehicleType}</p>
    <p>Plan Type: ${this.planType}</p>
    <p>Price in ${this.currency}: ${this.priceConverted.toLocaleString(
    undefined,
    { minimumFractionDigits: 2, maximumFractionDigits: 2 }
    )}</p>
    <p style="color: green;">Stored Price (USD): $${this.priceUSD.toLocaleString(
    undefined,
    { minimumFractionDigits: 2, maximumFractionDigits: 2 }
    )}</p>
    `;
    }
   }
   
   class Employee {
    fullName: string;
    gender?: Gender; 
    email: string;
   
    passwordHash?: string; 
    contact: string;
    employeeId: string;
   
    constructor(
    fullName: string,
    email: string,
    contact: string,
    gender?: Gender
    ) {
    this.fullName = fullName;
    this.email = email;
    this.contact = contact;
    this.gender = gender;
    this.employeeId = Employee.generateEmployeeId();
    }
   
    static generateEmployeeId(): string {
    return "EMP2025" + Math.floor(1000 + Math.random() * 9000);
    }
   
   
   }
   
   
   function getInputElement(id: string): HTMLInputElement {
    const el = document.getElementById(id) as HTMLInputElement | null;
    if (!el)
    throw new Error(`Element with ID "${id}" not found or not an input.`);
    return el;
   }
   
   function getElement(id: string): HTMLElement {
    const el = document.getElementById(id);
    if (!el) throw new Error(`Element with ID "${id}" not found.`);
    return el;
   }
   
   function getSpanElement(id: string): HTMLSpanElement {
    const el = document.getElementById(id) as HTMLSpanElement | null;
    if (!el) throw new Error(`Element with ID "${id}" not found or not a span.`);
    return el;
   }
   
  
   
   function displayError(errorElement: HTMLSpanElement, message: string): void {
    errorElement.textContent = message;
    errorElement.style.display = "inline";
   }
   
   function clearError(errorElement: HTMLSpanElement): void {
    errorElement.style.display = "none";
    errorElement.textContent = "";
   }
   
   function checkValidationName(): boolean {
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
    displayError(
    err,
    "Invalid full name format. Use letters, spaces, hyphens, apostrophes."
    );
    return false;
    }
    clearError(err);
    return true;
   }
   
   function checkEmail(): boolean {
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
   
   type PasswordStrength = "weak" | "normal" | "strong" | "invalid";
   
   function evaluatePasswordStrength(password: string): PasswordStrength {
    if (password.length < 8) return "invalid";
   
    let score = 0;
    if (password.length >= 8) score++; 
    if (/[A-Z]/.test(password)) score++;
    if (/[a-z]/.test(password)) score++;
    if (/\d/.test(password)) score++;
    if (/[@$!%*?&#]/.test(password)) score++;
   
    if (score <= 2) return "weak"; 
    if (score === 3 || score === 4) return "normal"; 
    if (score >= 5) return "strong"; 
    return "weak"; 
   }
   
   function checkPassword(): boolean {
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
    displayError(
    err,
    "Password strength: Weak. Needs uppercase, lowercase, number, and symbol."
    );
   
    } else if (strength === "normal") {
    displayError(
    err,
    "Password strength: Normal. Consider adding more complexity."
    );
    
    } else if (strength === "strong") {
    clearError(err); 
    }
    
    return true;
   }
   
   function checkConfirmPassword(): boolean {
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
   
   function checkContact(): boolean {
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
   
   // --- Form Step Management ---
   const formSteps: NodeListOf<HTMLElement> =
    document.querySelectorAll(".form-step");
   formSteps.forEach(
    (step, index) => (step.style.display = index === 0 ? "block" : "none")
   );
   
   const vehicleSteps: NodeListOf<HTMLElement> =
    document.querySelectorAll(".form-vehicle");
   vehicleSteps.forEach(
    (step, index) => (step.style.display = index === 0 ? "block" : "none")
   );
   
   function moveToNext(
    nextId: string,
    currId: string,
    isVehicleForm: boolean = false
   ): void {
    let isValid = true;
    if (!isVehicleForm) {
    // Employee form validations
    if (currId === "step-1") isValid = checkValidationName();
    else if (currId === "step-3") isValid = checkEmail();
    else if (currId === "step-4")
    isValid =
    checkPassword(); // checkPassword always returns true unless empty or too short
    else if (currId === "step-5") isValid = checkConfirmPassword();
    else if (currId === "step-6") isValid = checkContact(); // This is the submit step, validation already called by oninput
    } else {
    // Vehicle form validations (if any were added for step-v-1 or step-v-2)
    // Example: if (currId === "step-v-1") isValid = checkVehicleMake();
    }
   
    if (!isValid) return;
   
    const currentStep = document.getElementById(currId);
    const nextStep = document.getElementById(nextId);
   
    if (currentStep) currentStep.style.display = "none";
    if (nextStep) nextStep.style.display = "block";
   }
   
   // --- Event Handlers / Main Logic ---
   
   function handleSubmitEmployee(): void {
    // Final validation check before creating employee
    const isNameValid = checkValidationName();
    const isEmailValid = checkEmail();
    const isPasswordValid = checkPassword(); // Checks for length and required
    const isConfirmPasswordValid = checkConfirmPassword();
    const isContactValid = checkContact();
    // const isGenderSelected = (document.querySelector('input[name="Gender"]:checked') as HTMLInputElement)?.value;
   
    if (
    isNameValid &&
    isEmailValid &&
    isPasswordValid &&
    isConfirmPasswordValid &&
    isContactValid
    ) {
    const fullName = getInputElement("fname").value.trim();
    const email = getInputElement("Email").value.trim();
    const contact = getInputElement("Contact").value.trim();
    const genderInput = document.querySelector(
    'input[name="Gender"]:checked'
    ) as HTMLInputElement | null;
    const gender = genderInput ? (genderInput.value as Gender) : undefined; // Cast to Gender enum
   
    const newEmployee = new Employee(fullName, email, contact, gender);
   
    getElement("employee-id-display").textContent =
    "Employee ID: " + newEmployee.employeeId;
    const employeeForm = document.querySelector(
    ".add_employee_form"
    ) as HTMLElement | null;
    if (employeeForm) employeeForm.style.display = "none";
   
    console.log("New Employee Created:", newEmployee);
    // Here you would typically send newEmployee data to a backend
    } else {
    // alert("Please correct the errors in the form.");
    // Errors are already displayed by the validation functions
    }
   }
   
   function calculateAndShowPass(): void {
    const vehicleTypeInput = document.querySelector('input[name="vehicle-type"]:checked') as HTMLInputElement | null;
    const planTypeInput = document.querySelector('input[name="plan-type"]:checked') as HTMLInputElement | null;
    const currencySelect = getInputElement("currency-select") as unknown as HTMLSelectElement; // Cast for <select>
    const resultDiv = getElement("final-pass");
   
    if (!vehicleTypeInput || !planTypeInput) {
    resultDiv.innerText = "Please select vehicle and plan type.";
    return;
    }
   
    const vehicleType = vehicleTypeInput.value as VehicleType;
    const planType = planTypeInput.value as PlanType;
    const currency = currencySelect.value as Currency;
   
    const vehicleMake = getInputElement("vehicle-make").value.trim() || "N/A";
    const vehicleModel = getInputElement("vehicle-model").value.trim() || "N/A";
   
    // We don't strictly need a full Vehicle object for the Pass if Pass only needs type for pricing
    // But it's good practice if you might expand Vehicle use later.
    // const vehicle = new Vehicle(vehicleMake, vehicleModel, vehicleType);
   
    const pass = new Pass(vehicleType, planType, currency);
    resultDiv.innerHTML = pass.getDisplayDetailsHTML();
    if (vehicleMake !== "N/A" || vehicleModel !== "N/A") {
    resultDiv.innerHTML =
    `<p>Vehicle: ${vehicleMake} ${vehicleModel}</p>` + resultDiv.innerHTML;
    }
   
    console.log("Pass Generated:", pass);
    // Here you might store the pass details or associate with an employee/vehicle
   }
   
   // --- Attach Event Listeners ---
   document.addEventListener("DOMContentLoaded", () => {
    // Input event listeners for instant error clearing
    getInputElement("fname").addEventListener("input", () =>
    clearError(getSpanElement("name-error"))
    );
    getInputElement("Email").addEventListener("input", () =>
    clearError(getSpanElement("email-error"))
    );
    getInputElement("password").addEventListener("input", () => {
    clearError(getSpanElement("password-error"));
    checkPassword(); // Re-evaluate strength on input
    });
    getInputElement("conpassword").addEventListener("input", () =>
    clearError(getSpanElement("conpassword-error"))
    );
    getInputElement("Contact").addEventListener("input", () => {
    clearError(getSpanElement("contact-error"));
    checkContact(); // Validate on input to give immediate feedback
    });
   
    // Attach to "Next" buttons - needs to be done carefully if elements are dynamically added
    // Using global functions called from onclick in HTML is simpler for this structure
    // If you want to do it purely in TS:
    // Example for one button:
    // getElement('someNextButtonId').addEventListener('click', () => moveToNext('step-2', 'step-1'));
   
    // Make functions globally accessible for onclick="" in HTML
    (window as any).moveToNext = moveToNext;
    (window as any).checkValidationName = checkValidationName;
    (window as any).checkEmail = checkEmail;
    (window as any).checkPassword = checkPassword;
    (window as any).checkConfirmPassword = checkConfirmPassword;
    (window as any).checkContact = checkContact;
    (window as any).handleSubmitEmployee = handleSubmitEmployee;
    (window as any).calculateAndShowPass = calculateAndShowPass;
   });