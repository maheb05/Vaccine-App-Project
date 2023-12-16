<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<style>
	body {
		background-color: Grey;
	}

	header {
	    background-color:#92DFF3;
	    height:60px;
	    position:fixed;
	    width:100%;
	    top: 0;
        z-index: 1000;
	}
	footer {
		background-color:#92DFF3;
	    height:60px;
	    position:fixed;
	    width:100%;
	    bottom: 0;
        z-index: 1000;
	}

	span {
	    display: flex;
		margin-left:2%;
	}

	#span1 {
		display: flex;
		margin-left:2%;
		margin-top:10%;
		margin-bottom:10%;
	}

	.card {
	  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
	  transition: 0.3s;
	  width:18%;
	  height:350px;
	  text-align: center;
      display: flex;
      flex-direction: column;
      justify-content: center;
	}

	.card:hover {
	  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
	}

	.container {
	  padding: 2px 16px;
	}

	/* Added styles for buttons */
	.btn {
		display: inline-block;
		padding: 10px 20px;
		margin-top: 5px;
		margin-bottom: 5px;
		border: none;
		cursor: pointer;
		text-align: center;
		text-decoration: none;
		font-size: 16px;
	}

	.btn-add-member {
		background-color: green;
		color: white;
	}

	.btn-logout {
		background-color: blue;
		color: white;
	}

</style>
</head>
<body>
	<header>
		<script>
			function go() {
				window.location.replace("logout.jsp",'window','toolbar=1,location=1,directories=1,status=1,menubar=1,scrollbars=1,resizable=1');
				self.close()
			}
		</script>
		<span>
			<a href="addMemberButton" class="btn btn-add-member">AddMember</a>
			<pre>                                                                                                              </pre>
			<%
				String str = request.getParameter("userEmail");
				session.setAttribute("EMAIL", request.getParameter("userEmail"));
			%>
			<h5 style="color:blue; margin-top:25px;">User:
			<%=session.getAttribute("userEmail")%></h5>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<%
				if (session.getAttribute("userEmail").equals("")) {
			%>
				<a href="login.jsp" class="btn btn-logout"><b>Login</b></a>
			<%
				} else {
			%>
				<a href="javascript:go()" class="btn btn-logout">Logout</a>
			<%
				}
			%>
		</span>
	</header>
	<aside>
	<span id=span1>
		<div class="card">
		  <div class="container">
		    	<h4 style="margin: 0;"><b>Registration Count</b></h4> 
		    	<h1 style="font-size:250px; color:blue; text-align: center; margin: 0;">${memCount}</h1>
	  	  </div>
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="card">
		  <div class="container">
		    <h4><b>Tiwan</b></h4> 
		    <p>Developer</p> 
		  </div>
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="card">
		  <div class="container">
		    <h4><b>Williams</b></h4> 
		    <p>Test Engineer</p> 
		  </div>
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="card">
		  <div class="container">
		    <h4><b>Julie</b></h4> 
		    <p>Full Stack Developer</p> 
		  </div>
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="card">
		  <div class="container">
		    <h4><b>Chamchin</b></h4> 
		    <p>Team Lead</p> 
		  </div>
		</div>
	</span>
	</aside>
	<footer>
	</footer>
</body>
</html>
