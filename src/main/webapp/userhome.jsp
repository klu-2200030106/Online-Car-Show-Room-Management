<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.List"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="com.klef.project.services.UserService"%>
<%@page import="com.klef.project.models.User"%>
<%
User user = (User) session.getAttribute("user");
if(user==null)
{
  response.sendRedirect("usersessionexpiry.html");
}
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RVA SHOW ROOM</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css"/>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    
    <link rel="stylesheet" href="index.css">

</head>
<body>
    <header class="header">
        <div id="menu-btn" class="fas fa-bars"></div>
        <a href="#" class="logo"> <span>RVA CAR</span>SHOWROOM</a>
        <nav class="navbar">
            <a href="userhome.jsp">Home</a>
            <a href="explorecars.jsf">Cars</a>
        </nav>

       
        <div id="login-btn">
            <button class="btn" id="openModal" onclick="window.location.href='index.html';"  >Logout</button>
        </div>
    
       
    </header>

    <section class="home" id="home">
        <h1 class="home-parallax" data-speed="-2">Find your car</h1>
        <img class="home-parallax" data-speed="5" src="car-inventory.png" alt="">
        <a href="explorecars.jsf" class="btn home-parallax" data-speed="7">Explore cars</a>
    </section>

    
    
    
   <section class="footer">
     <div class="credit"> Created by RVA CAR SHOWROOM all rights reserved!</div>
   </section>







     
<script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>

    <script src="script.js"></script>
</body>
</html>