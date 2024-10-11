<%@page import="com.klef.project.models.Admin"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.List"%>
<%@page import="javax.ejb.EJB"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
    <link rel="stylesheet" href="sellers2.css">
    <title>Seller Management</title>
</head>

<body>
    <div class="container">
        <div class="topbar">
            <div class="logo">
                <h2>CAR SHOW ROOM</h2>
            </div>
            <div class="user">
                <img src="https://cdn-icons-png.flaticon.com/512/9187/9187604.png" alt="">
            </div><br/>
            <div class="logout">
                <a href="index.html">Logout</a>&nbsp;&nbsp;&nbsp;
            </div>
        </div>
        <div class="sidebar">
            <ul>
                <li>
                    <a href="sellerhome.jsp">
                        <i class="fas fa-th-large"></i>
                        <div>Dashboard</div>
                    </a>
                </li>
                <li class="active">
                    <a href="sellercar.jsp">
                        <i class="fas fa-car"></i>
                        <div>Cars</div>
                    </a>
                </li>
                <li>
                    <a href="sellerhome.jsp">
                        <i class="fas fa-chart-bar"></i>
                        <div>Analytics</div>
                    </a>
                </li>
            </ul>
        </div>
        <div class="main">
            <div class="car-crud">
                <h2>Car Management</h2>
                <div class="crud-actions">
                    <button onclick="window.location.href='AddCar.jsf';">Add Cars</button>
                    <button onclick="window.location.href='updatecar.jsf';">Update Cars</button>
                    <button onclick="window.location.href='viewallcars.jsf';">View Cars</button>
                    <button onclick="window.location.href='deletecars.jsf';">Delete Cars</button>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.1/dist/chart.min.js"></script>
    <script src="chart1.js"></script>
    <script src="chart2.js"></script>
</body>

</html>
