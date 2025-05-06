// Ensuring correct types for elements
const formSteps: NodeListOf<HTMLElement> = document.querySelectorAll(".form-step");
formSteps.forEach((step, index) => step.style.display = index === 0 ? "block" : "none");

const vehicleSteps: NodeListOf<HTMLElement> = document.querySelectorAll(".form-vehicle");
vehicleSteps.forEach((step, index) => step.style.display = index === 0 ? "block" : "none");

// Validator Interface
interface Validator {
    validate(value: string): boolean;
    getErrorMessage(): string;
}

// Abstract Validator Class
abstract class BaseValidator implements Validator {
    protected errorMessage: string = "";

    abstract validate(value: string): boolean;

    getErrorMessage(): string {
        return this.errorMessage;
    }
}

// Name Validator
class NameValidator extends BaseValidator {
    validate(value: string): boolean {
        const regex: RegExp = /^([a-zA-Z]+[\s-']?){1,3}$/;
        if (!value.trim()) {
            this.errorMessage = "Full name is required.";
            return false;
        }
        if (!regex.test(value)) {
            this.errorMessage = "Invalid full name format.";
            return false;
        }
        if (value.length < 2) {
            this.errorMessage = "Name must be greater than 2 characters.";
            return false;
        }
        return true;
    }
}

// Email Validator
class EmailValidator extends BaseValidator {
    validate(value: string): boolean {
        const regex: RegExp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!value.trim()) {
            this.errorMessage = "Email is required.";
            return false;
        }
        if (!regex.test(value)) {
            this.errorMessage = "Invalid email format.";
            return false;
        }
        return true;
    }
}

// Password Validator
class PasswordValidator extends BaseValidator {
    validate(value: string): boolean {
        if (!value) {
            this.errorMessage = "Password is required.";
            return false;
        }
        if (value.length < 8) {
            this.errorMessage = "Password must be at least 8 characters.";
            return false;
        }
        return true;
    }
}

// Function to handle validation
function checkValidation(id: string, validator: Validator): boolean {
    const inputElement = document.getElementById(id) as HTMLInputElement;
    const errorElement = document.getElementById(`${id}-error`);

    if (!validator.validate(inputElement.value)) {
        if (errorElement) {
            errorElement.textContent = validator.getErrorMessage();
            errorElement.style.display = "inline";
        }
        return false;
    }

    if (errorElement) errorElement.style.display = "none";
    return true;
}

// Event Listeners
document.getElementById("fname")?.addEventListener("input", () => {
    document.getElementById("name-error")!.style.display = "none";
});

document.getElementById("Email")?.addEventListener("input", () => {
    document.getElementById("email-error")!.style.display = "none";
});

document.getElementById("password")?.addEventListener("input", () => {
    document.getElementById("password-error")!.style.display = "none";
});

// Employee ID Generator
function generateEmployeeId(): string {
    return `EMP2025${Math.floor(1000 + Math.random() * 9000)}`;
}

// Handle Form Submission
function handleSubmit(): void {
    const empId = generateEmployeeId();
    const displayElement = document.getElementById("employee-id-display");
    if (displayElement) {
        displayElement.textContent = `Employee ID: ${empId}`;
    }
    document.querySelector(".add_employee_form")!.style.display = "none";
}

// Move to Next Form Step
function moveToNext(nextId: string, currId: string): void {
    const curr = document.getElementById(currId);
    const next = document.getElementById(nextId);

    if (curr) curr.style.display = "none";
    if (next) next.style.display = "block";
}

// Pricing & Exchange Rates Objects
const pricingINR: Record<string, Record<string, number>> = {
    Cycle: { Daily: 5, Monthly: 100, Yearly: 500 },
    Motorcycle: { Daily: 10, Monthly: 200, Yearly: 1000 },
    "Four Wheeler": { Daily: 20, Monthly: 500, Yearly: 3500 },
};

const exchangeRates: Record<string, number> = {
    INR: 1,
    USD: 0.012,
    YEN: 1.82,
};

// Function to calculate and display the pass cost
function calculateAndShowPass(): void {
    const vehicle = (document.querySelector('input[name="vehicle-type"]:checked') as HTMLInputElement)?.value;
    const plan = (document.querySelector('input[name="plan-type"]:checked') as HTMLInputElement)?.value;
    const currency = (document.getElementById("currency-select") as HTMLSelectElement).value;
    const result = document.getElementById("final-pass");

    if (!vehicle || !plan) {
        result!.innerText = "Please select vehicle and plan type.";
        return;
    }

    const inr = pricingINR[vehicle][plan];
    const converted = (inr * exchangeRates[currency]).toFixed(2);
    const usd = (inr * exchangeRates["USD"]).toFixed(2);

    result!.innerHTML = `
        <p>Selected Vehicle: ${vehicle}</p>
        <p>Plan Type: ${plan}</p>
        <p>Price in ${currency}: ${converted}</p>
        <p style="color: green;">Stored Price (USD): $${usd}</p>
    `;
}