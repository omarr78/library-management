<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <link href="webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css" rel="stylesheet">
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
    <title>Manage Your Todo</title>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
    <h1 class="navbar-brand m-1">Books</h1>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav">
            <li class="nav-item"><a class="nav-link" href="/">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="/books-list">Show All Books</a></li>
            <li class="nav-item"><a class="nav-link" href="/myBooks-list">Show My Books</a></li>
        </ul>
    </div>
    <ul class="navbar-nav">
        <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
    </ul>
</nav>
<div class = "container">
    <h1>Welcome ${name}</h1>
    <a href = "books-list">Show All Books</a>
    <a href = "myBooks-list">Show My Books</a>
</div>
<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
</body>
</html>