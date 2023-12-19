<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Details</title>
    <style>
        body {
            background-color: #808080; /* Grey background color for the body */
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        header, footer {
            background-color: #92DFF3; /* Header and footer color */
            padding: 10px;
            text-align: center;
        }

        form {
            margin: 20px auto; /* Center the form on the page */
            background-color: #808080; /* Grey background color for the form */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 60%; /* Adjusted width */
            margin: 0 auto; /* Center the table */
            border-collapse: collapse; /* Added border-collapse for table borders */
        }

        td {
            padding: 12px; /* Increased padding for input boxes */
            border: 1px solid #ddd; /* Added border for table cells */
        }

        /* Adjusted gender label */
        td label {
            display: inline-block;
            margin-right: 10px;
        }

        /* Adjusted width for input elements */
        input[type="number"],
        input[type="text"],
        input[type="date"],
        select {
            width: 100%;
            box-sizing: border-box; /* Include padding and border in the element's total width and height */
        }
    </style>
</head>
<body>
    <header>
        <h1>Edit Details</h1>
    </header>

    <form action="${pageContext.request.contextPath}/updateDetails" method="post">
        <table>
            <tr>
                <td>Member Id</td>
                <td><input type="number" name="memberId" value="${id}" required="required"></td>
            </tr>
            <tr>
                <td>Member Name</td>
                <td><input type="text" name="memberName" value="${name}" required="required"></td>
            </tr>
            <tr>
                <td>Gender:</td>
                <td>
                    <label><input type="radio" name="gender" value="male" id="male" required> Male</label>
                    <label><input type="radio" name="gender" value="female" id="female" required> Female</label>
                    <label><input type="radio" name="gender" value="other" id="other" required> Other</label>
                </td>
            </tr>
            <tr>
                <td>Date of Birth</td>
                <td><input type="date" name="dob" required="required" value="${dob}"></td>
            </tr>
            <tr>
                <td>Government ID</td>
                <td>
                    <select name="governmentId" required="required">
                        <option value="" selected disabled>Select Government ID</option>
                        <option value="Adhaar">Adhaar</option>
                        <option value="Pan">Pan</option>
                        <option value="DrivingLicense">Driving License</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>ID Proof</td>
                <td><input type="number" name="idProof" value="${idProof}" required="required"></td>
            </tr>
            <tr>
                <td>Vaccine Type</td>
                <td>
                    <select name="vaccineType" required="required">
                        <option value="" selected disabled>Select Vaccine Type</option>
                        <option value="Covaccine">Covaccine</option>
                        <option value="Cowin">Cowin</option>
                        <option value="Co-Xwin">Co-Xwin</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Dose</td>
                <td>
                    <select name="dose" required="required">
                        <option value="" selected disabled>Select How Many Doses Completed</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Submit">
    </form>
    <h2><p>${addMessage}</p></h2>
    <footer>
        <p>footer</p>
    </footer>
</body>
</html>
