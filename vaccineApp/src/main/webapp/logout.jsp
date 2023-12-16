<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
<style>

	body{
		background-color :Grey; 
	}
	header{
	    background-color:#92DFF3;
	    height:60px;
	    position:fixed;
	    width:100%;
	    top: 0;
        z-index: 1000;
	}
	footer{
		background-color:#92DFF3;
	    height:60px;
	    position:fixed;
	    width:100%;
	    bottom: 0;
        z-index: 1000;
	}
	aside{
		margin-top:60px;
		margin-bottom:60px;
	}
	span{
	    display: inline-block;
		margin-top:9.5px;      
		margin-left:92%;       	
	}
	h1{
		text-align:center;
		color:black;
		font-size:30px;
		font-family:times new roman;
		font-weight:bold;
	}
</style>
</head>
<body>
	<header>
		<span>
            <a href="getLoginPage" class="btn btn-primary">Login</a>
        </span>
	</header>
	<aside>
		<h1>you have logged out successfully</h1>
		<h1> please login again...</h1>
		<h1>Thank you!</h1>
	</aside>
	<footer>
	</footer>
</body>
</html>