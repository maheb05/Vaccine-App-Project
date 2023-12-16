<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Reset Password</title>
<style>
    body {
        background-color: Grey;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    header{
        background-color: #92DFF3;
        color: white;
        text-align: center;
        padding: 10px;
        width: 100%;
        box-sizing: border-box;
    }

    footer {
        background-color: #92DFF3;
        color: white;
        text-align: center;
        padding: 10px;
        width: 100%;
        box-sizing: border-box;
    }

    .email-box {
        background-color: #f5f5f5;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 300px;
        text-align: center;
        margin-top: 40px;
    }

    table {
        width: 100%;
    }

    table tr {
        margin-bottom: 10px;
    }

    input {
        width: 100%;
        padding: 8px;
        box-sizing: border-box;
    }

    button {
        background-color: #008000;
        color: white;
        padding: 10px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
	 a {
        color: blue;
        text-decoration: none;
        font-weight: bold;
    }
    .error {
        color: red;
    }
</style>

<script>
    function validateForm() {
        var email = document.getElementById("email").value;
        var emailError = document.getElementById("email-error");

        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            emailError.textContent = "Invalid email address";
            emailError.classList.add("error"); // Add red color
            return false;
        } else {
            emailError.textContent = "";
        }

        var password = document.getElementsByName("newPassword")[0].value;
        var confirmPassword = document.getElementsByName("confirmNewPassword")[0].value;
        var passwordError = document.getElementById("password-error");

        if (password.length < 8 || password.length > 12) {
            passwordError.textContent = "Password should be between 8 and 12 characters";
            passwordError.classList.add("error"); // Add red color
            return false;
        } else {
            passwordError.textContent = "";
        }

        var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{8,12}$/;
        if (!passwordRegex.test(password)) {
            passwordError.textContent = "Password should include uppercase, lowercase, special characters, and numbers";
            passwordError.classList.add("error"); // Add red color
            return false;
        } else {
            passwordError.textContent = "";
        }

        if (password !== confirmPassword) {
            passwordError.textContent = "Passwords do not match";
            passwordError.classList.add("error"); // Add red color
            return false;
        } else {
            passwordError.textContent = "";
        }

        return true;
    }
</script>

</head>
<body>
    <header>
        <h5>Header</h5>
    </header>

    <div class="email-box">
        <form action="enterEmailToResetPassword" method="post" onsubmit="return validateForm()">
            <table>
                <tr>
                    <td>Email:</td>
                </tr>
                <tr>
                    <td><input type="email" name="email" id="email" placeholder="Enter Email" required="required"></td>
                </tr>
                <tr>
                    <td>New Password:</td>
                </tr>
                <tr>
                    <td><input type="password" name="newPassword" placeholder="Enter New Password" required="required"></td>
                </tr>
                <tr>
                    <td>Confirm New Password:</td>
                </tr>
                <tr>
                    <td><input type="password" name="confirmNewPassword" placeholder="Confirm New Password" required="required"></td>
                </tr>
            </table>
            <span id="email-error" class="error"></span>
            <span id="password-error" class="error"></span>
            <button type="submit">Reset</button>
        </form>
    </div>
        <p>Go To <a href="goCheckLogin">Login</a></p>
    <h3>${emailUpdate }</h3>
    <h3>${passwordMessage }</h3>
    <footer>
        <h5>Footer</h5>
    </footer>
</body>
</html>