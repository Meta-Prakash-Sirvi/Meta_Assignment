
 function employees(nextFieldId) {
   document.getElementById(nextFieldId).style.display = "block";
}

document.getElementById("employeeForm").addEventListener("submit", function (event) {
   event.preventDefault(); 
   const passwordInput = document.getElementById("password");
   
   const confirmPasswordInput = document.getElementById("confirm password");
   const mobileInput = document.getElementById("mobile");
   const fullName = document.getElementById("fullname");
   const emailInput = document.getElementById("Email");

   
   if (passwordInput.value.length < 8) {
       alert("Password must be at least 8 characters long.");
       passwordInput.style.borderColor = "red";
       return;
   } else if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/.test(passwordInput.value)) {
       alert("Password must include uppercase, lowercase, and numeric characters.");
       passwordInput.style.borderColor = "red";
       return;
   }

   if (confirmPasswordInput.value !== passwordInput.value) {
       alert("confirm password must be same as password. Please try again.");
       confirmPasswordInput.style.borderColor = "red";
       return;
   }


   //validate mobile name
   if(mobileInput.value.length <8){
       alert("mobile number greter then 8 digit"); 
       mobileInput.style.borderColor = "red"; 
       return;
   }else if(!/^\d+$/.test(mobileInput.value)){
         alert("only digit is allowed ");
         mobileInput.style.borderColor = "red"; 
         return ;
   } 


   //validate full name
   if(fullName.value.length < 2){
      alert("name must be greater the 2 lenght");
      mobileInput.style.borderColor = "red"; 
      return ; 
   }else if(/\d/.test(fullName.value)){
      alert("numeric value not allowed");
      mobileInput.style.borderColor = "red"; 
      return ;
   }


   // vaildate email

if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(emailInput)) { 
    alert("Email must be valid and contain '@'");
    return;
}


  
   alert("Form submitted successfully!");
   passwordInput.style.borderColor = "green";
   confirmPasswordInput.style.borderColor = "green";
  
});

// Function to display pricing for selected vehicle type
// Function to display pricing for the selected vehicle type
function showPricing(vehicleType) {
    // Hide all pricing sections
    document.getElementById('car').style.display = 'none';
    document.querySelector('.pri2').style.display = 'none';
    document.querySelector('.pri1').style.display = 'none';

    // Show the selected pricing section based on vehicle type
    if (vehicleType === 'Car') {
        document.getElementById('car').style.display = 'block';
    } else if (vehicleType === 'Bike') {
        document.querySelector('.pri2').style.display = 'block';
    } else if (vehicleType === 'Cycle') {
        document.querySelector('.pri1').style.display = 'block';
    }
}

// Add event listeners to the radio buttons
document.querySelectorAll('input[name="Vehicle"]').forEach(radio => {
    radio.addEventListener('click', () => {
        showPricing(radio.value); // Use the value attribute of the clicked radio button
    });
});
function click(){}
const price=document.getElementById(currencySelector);
if(price.value==="USD"){
    document.getElementById("cycle").innerHTML = "New text!";
}

