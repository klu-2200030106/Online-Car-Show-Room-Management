<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.List"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="com.klef.project.services.SellerService"%>
<%@page import="com.klef.project.models.Seller"%>
<%
Seller sel = (Seller) session.getAttribute("seller");
if(sel==null)
{
	response.sendRedirect("sellersessionexpiry.html");
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
    <link rel="stylesheet" href="sellers.css">
    <title>Seller Panel</title>
</head>

<body>
    <div class="container">
        <div class="topbar">
            <div class="logo">
                <h2>CAR SHOW ROOM</h2>
            </div>
            <div class="user">
                <img src="https://cdn-icons-png.flaticon.com/512/9187/9187604.png"  alt="">
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

                <li>
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
            <div class="cards">
                <div class="card">
                    <div class="card-content">
                        <div class="number">121</div>
                        <div class="card-name">Users</div>
                    </div>
                    <div class="icon-box">
                        <i class="fas fa-users"></i>
                    </div>
                </div>
                <div class="card">
                    <div class="card-content">
                        <div class="number">42</div>
                        <div class="card-name">Cars</div>
                    </div>
                    <div class="icon-box">
                        <i class="fas fa-car"></i>
                    </div>
                </div>
                <div class="card">
                    <div class="card-content">
                        <div class="number">28</div>
                        <div class="card-name">Sellers</div>
                    </div>
                    <div class="icon-box">
                        <i class="fas fa-store"></i>
                    </div>
                </div>
                <div class="card">
                    <div class="card-content">
                        <div class="number">$4500</div>
                        <div class="card-name">Earnings</div>
                    </div>
                    <div class="icon-box">
                        <i class="fas fa-dollar-sign"></i>
                    </div>
                </div>
            </div>
            <div class="charts">
                <div class="chart">
                    <h2>Earnings (past 12 months)</h2>
                    <div>
                        <canvas id="lineChart"></canvas>
                    </div>
                </div>
                <div class="chart doughnut-chart">
                    <h2>Sellers</h2>
                    <div>
                        <canvas id="doughnut"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.1/dist/chart.min.js"></script>
    <script src="chart1.js"></script>
    <script src="chart2.js"></script>
</body>
</html>