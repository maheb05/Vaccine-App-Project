<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
  <style>
        body {
            background-color: Grey;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        header, footer {
            background-color: #92DFF3;
            color: white;
            text-align: center;
            padding: 10px;
            position: fixed;
            width: 100%;
            z-index: 1;
        }

        header {
            display: flex;
            justify-content: space-between;
        }

        header div {
            margin-right: 25px;
        }

        header button {
            background-color: Blue;
            color: white;
            padding: 5px 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        footer {
            bottom: 0;
        }

        main {
            margin-top: 40px;
            flex-grow: 1;
        }
    </style>
</head>
<body>
	<header>
        <div></div>
        <div>
        	<a href="loginPage">
                <button>Login</button>
            </a>
            <a href="welcomePage">
                <button>Register</button>
            </a>
        </div>
    </header>

	<footer> This is the footer. </footer>
</body>
</html>