<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Member</title>
<style>
body {
	background-color: Grey;
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

header, footer {
	background-color: #92DFF3;
	color: white;
	text-align: center;
	padding: 10px;
}

form {
	width: 80%;
	margin: 20px auto;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: left;
}

th {
	color: white;
}

input, select {
	width: 100%;
	padding: 8px;
	margin: 5px 0;
	box-sizing: border-box;
}

input[type="submit"] {
	background-color: Green;
	color: white;
	cursor: pointer;
	width: auto; /* Adjusted width */
	display: block; /* Display below the dose */
	margin-top: 10px; /* Added margin for spacing */
}

input[type="radio"] {
	margin-right: 5px;
}

.viewAll-button {
	background-color: green;
	margin-right: 1000px; /* Adjusted margin to be on the right side */
	padding: 5px 10px;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	text-decoration: none; /* Remove underline for links */
}

.members-table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

.members-table th, .members-table td {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: left;
	background-color: #4CAF50;
	color: white;
}
</style>

<script>
	function validateForm() {
		var memberName = document.forms["myForm"]["memberName"].value;
		var gender = document.forms["myForm"]["gender"];
		var dob = document.forms["myForm"]["dob"].value;
		var governmentId = document.forms["myForm"]["governmentId"].value;
		var idProof = document.forms["myForm"]["idProof"].value;
		var vaccineType = document.forms["myForm"]["vaccineType"].value;
		var dose = document.forms["myForm"]["dose"].value;

		// Check if any field is empty
		if (memberName === "" || gender.value === "" || dob === ""
				|| governmentId === "" || idProof === "" || vaccineType === ""
				|| dose === "") {
			alert("All fields must be filled out");
			return false;
		}

		// Check if gender is selected
		if (!gender.checked) {
			alert("Gender is required");
			return false;
		}

		// Check if DOB is entered
		if (dob === "") {
			alert("Date of Birth is required");
			return false;
		}

		// Check if ID Proof has exactly 8 numbers
		if (idProof.length !== 8 || isNaN(idProof)) {
			alert("ID Proof must contain exactly 8 numeric digits");
			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<header>
		<a href="viewAlltButton" class="viewAll-button">View All Members</a>
	</header>

	<form action="addMember" method="post" onsubmit="return validateForm()">
		<table>
			<tr>
				<td>Member Name</td>
				<td><input type="text" name="memberName"
					placeholder="Enter Member Name" required="required"></td>
			</tr>
			<tr>
				<th>Gender:</th>
				<td><input type="radio" name="gender" value="male" id="male"
					required> <label for="male">Male</label> <input
					type="radio" name="gender" value="female" id="female" required>
					<label for="female">Female</label> <input type="radio"
					name="gender" value="other" id="other" required> <label
					for="other">Other</label></td>
			</tr>
			<tr>
				<td>DOB</td>
				<td><input type="date" name="dob" required="required"></td>
			</tr>
			<tr>
				<td>Government ID</td>
				<td><select name="governmentId" required="required">
						<option value="" selected disabled>Select Governament ID</option>
						<option value="Adhaar">Adhaar</option>
						<option value="Pan">Pan</option>
						<option value="DrivingLicense">Driving License</option>
				</select></td>
			</tr>
			<tr>
				<td>ID Proof</td>
				<td><input type="number" name="idProof"
					placeholder="Enter 8 Digits ID Proof" required="required"></td>
			</tr>
			<tr>
				<td>Vaccine Type</td>
				<td><select name="vaccineType" required="required">
						<option value="" selected disabled>Select Vaccine Type</option>
						<option value="Covaccine">Covaccine</option>
						<option value="Cowin">Cowin</option>
						<option value="Co-Xwin">Co-Xwin</option>
				</select></td>
			</tr>
			<tr>
				<td>Dose</td>
				<td><select name="dose" required="required">
						<option value="" selected disabled>Select How Many Doses
							Completed</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
				</select></td>
			</tr>
		</table>
		<input type="submit" value="Submit">
	</form>
	<p>${addMessage }</p>

	<table class="members-table">
		<thead>
			<tr>
				<th>MEMBER ID</th>
				<th>MEMBER NAME</th>
				<th>GENDER</th>
				<th>DOB</th>
				<th>GOVERNMENT ID</th>
				<th>ID PROOF</th>
				<th>VACCINE TYPE</th>
				<th>DOSE</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="member" items="${members}">
				<tr>
					<td>${member.memberId}</td>
					<td>${member.memberName}</td>
					<td>${member.gender}</td>
					<td>${member.dob}</td>
					<td>${member.governamentID}</td>
					<td>${member.idProof}</td>
					<td>${member.vaccineType}</td>
					<td>${member.dose}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<footer> footer </footer>
</body>
</html>
