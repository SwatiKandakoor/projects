<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>X-workz</title>
</head>
<body>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
	crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg-navbar-Light bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img
				src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
				alt="" width="150" height="55" class="d-inline-block align text-top">
				<a href="index.jsp">Home</a>
		</div>
	</nav>
	
	<form action="sign" method="post">
	
  <div class="container px-8 py-10 px-md-12 text-center text-lg-start my-5">
    <div class="row gx-lg-5 align-items-center mb-5">
     <div class="col-lg-6 mb-5 mb-lg-0" style="z-index: 10">
        <h1 class="my-5 display-5 fw-bold ls-tight" style="color: hsl(218, 81%, 95%)">
          The best offer <br />
          <span style="color: hsl(218, 81%, 75%)">for your business</span>
        </h1>
        	
      </div>

      <div class="col-lg-6 mb-5 mb-lg-0 position-relative">
        
        <div class="card bg-glass">
          <div class="card-body px-4 py-5 px-md-5">
            <form>
              <!-- user id -->
              <div class="form-outline mb-4">
                <input type="text" id="form3Example3" name="userId" class="form-control" />
                <label class="form-label" for="form3Example3">User Id</label>
              </div>

              <!-- Email input -->
              <div class="form-outline mb-4">
                <input type="email" id="form3Example3" name="email" class="form-control" />
                <label class="form-label" for="form3Example3">Email address</label>
              </div>
              
         

              <!-- Password input -->
              <div class="form-outline mb-4">
                <input type="password" id="form3Example4" name="password" class="form-control" />
                <label class="form-label" for="form3Example4">Password</label>
              </div>
              
              <!-- Confirm Password input -->
              <div class="form-outline mb-4">
                <input type="password" id="form3Example4" namr="confirmPassword"class="form-control" />
                <label class="form-label" for="form3Example4">Confirm Password</label>
              </div>
              
              <!-- mobile number input -->
               <div class="form-outline mb-4">
                <input type="number" id="form3Example4" name="mobile" class="form-control" />
                <label class="form-label" for="form3Example4">Mobile number</label>
              </div>
              

              <!-- Checkbox -->
              <div class="form-check d-flex justify-content-center mb-4">
                <input class="form-check-input me-2" type="checkbox" value="" id="form2Example33" checked />
                <label class="form-check-label" for="form2Example33">
                 Accept Agreement
                </label>
              </div>

              <!-- Submit button -->
              <button type="submit" class="btn btn-primary btn-block mb-4">
                Sign up
              </button>

              <!-- Register buttons -->
              <div class="text-center">
                <p>or sign up with:</p>
                <button type="button" class="btn btn-link btn-floating mx-1">
                  <i class="fab fa-facebook-f"></i>
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</form>
</body>
</html>