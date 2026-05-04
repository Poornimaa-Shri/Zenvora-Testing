async function login() {
    let username = document.getElementById("loginUser").value;
    let password = document.getElementById("loginPass").value;

    let res = await fetch("http://localhost:8081/login", {   
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ username, password })
    });

    let data = await res.json();

    if (data === true) {   
        document.getElementById("loginMsg").innerText = "Login Success";
        localStorage.setItem("user", username);

        setTimeout(() => {
            window.location.href = "../pages/products.html";
        }, 1000);

    } else {
        document.getElementById("loginMsg").innerText = "Invalid Credentials";
    }
}

async function signup() {

    let username = document.getElementById("signupUser").value;
    let password = document.getElementById("signupPass").value;

    let res = await fetch("http://localhost:8081/signup", {  
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ username, password })
    });

    let data = await res.text();   // ✅ FIX

    console.log("SIGNUP RESPONSE:", data); 

    if (data === "Signup successful") {
        document.getElementById("signupMsg").innerText = "Signup Success";

        setTimeout(() => {
            showLogin();
        }, 1500);

    } else {
        document.getElementById("signupMsg").innerText = data;
    }
}