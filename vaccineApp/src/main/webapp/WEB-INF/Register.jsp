<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>

<style type="text/css">
	body {
	background-color: Grey;
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
}

header {
	background-color: #92DFF3;
	color: white;
	text-align: center;
	padding: 10px;
}

footer {
	background-color: #92DFF3;
	color: white;
	text-align: center;
	padding: 10px;
}

h1 {
        text-align: center;
        margin-top: 20px; /* Adjust as needed */
    }
.registeryourself{
	color: White;
}

form {
	margin: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	color:White;
	padding: 10px;
	text-align: left;
	border: none;
}

input[type="submit"] {
	background-color: #008000;
	color: white;
	padding: 5px 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.error {
    color: red;
    font-size: 80%;
}

.success {
    color: green;
}


.failure {
    color: red;
}

input[type="text"],
input[type="password"],
input[type="date"] {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
}

input[type="text"]:focus,
input[type="password"]:focus,
input[type="date"]:focus {
    border: none;
    border-bottom: 2px solid blue;
    outline: none;
}

input[type="radio"] {
    margin-right: 5px;
}
div {
        text-align: center;
        margin-top: 20px;
    }

    div a {
        color: blue;
        text-decoration: none;
        font-weight: bold;
    }
</style>
<script>
function validatePassword() {
    let password = document.getElementsByName("password")[0].value;
    let confirmPassword = document.getElementsByName("confirmPassword")[0].value;

    if (password.length < 8 || password.length > 12) {
        alert("Password should be between 8 to 12 characters!");
        return false;
    }

    let passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{8,12}$/;
    if (!passwordRegex.test(password)) {
        alert("Password should include uppercase, lowercase, special characters, and numbers!");
        return false;
    }

    if (password !== confirmPassword) {
        alert("Passwords do not Matched!");
        return false;
    }

    return true;
}

function validateMobileNumber() {
    let mobileNumber = document.getElementsByName("mobileNumber")[0].value;
    let mobileRegex = /^\d{10}$/;
    if (!mobileRegex.test(mobileNumber)) {
        alert("Invalid Mobile Number!");
        return false;
    }
    return true;
}

function validateEmail() {
    let email = document.getElementsByName("email")[0].value;
    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        alert("Invalid Email Address!");
        return false;
    }
    return true;
}

function validateForm() {
    return validatePassword() && validateMobileNumber() && validateEmail();
}
</script>
</head>
<body>
    <header>
        <h5>Header</h5>
    </header>
    <h1 class="registeryourself">Register Yourself</h1>
    <form action="registerForm" method="post" onsubmit="return validateForm()">
        <table>
            <tr>
                <th>UserName:</th>
                <td><input type="text" name="username" placeholder="username" required="required"></td>
            </tr>
            <tr>
                <th>Email:</th>
                <td><input type="text" name="email" placeholder="email" required="required"></td>
            </tr>
            <tr>
                <th>Password:</th>
                <td><input type="password" name="password" placeholder="password" required="required"></td>
            </tr>
            <tr>
                <th>Confirm Password:</th>
                <td><input type="password" name="confirmPassword" placeholder="Confirm Password" required="required"></td>
            </tr>
            <tr>
                <th>Mobile Number:</th>
                <td><input type="text" name="mobileNumber" placeholder="mobile number" required="required"></td>
            </tr>
            <tr>
                <th>Gender:</th>
                <td>
			        <input type="radio" name="gender" value="male" id="male" required>
			        <label for="male">Male</label>
			
			        <input type="radio" name="gender" value="female" id="female" required>
			        <label for="female">Female</label>
			
			        <input type="radio" name="gender" value="other" id="other" required>
			        <label for="other">Other</label>
   			 </td>
            </tr>
            <tr>
                <th>Date Of Birth:</th>
                <td><input type="date" name="dob" placeholder="DOB" required="required"></td>
            </tr>
        </table>
        <input type="submit" value="Register">
    </form>
    <h1 class="${responseClass}">${response}</h1>
    <h1 class="${mailResponseClass}">${mailResponse}</h1>
    <div>
        <p>Already have an account? <a href="alreadyHaveAnAccount">Login</a></p>
    </div>
    <footer>
        <h5>Footer</h5>
    </footer>
</body>
</html>