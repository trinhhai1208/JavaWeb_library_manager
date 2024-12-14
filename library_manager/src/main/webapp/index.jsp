<%@page import="model.Member"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Trang chủ</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="img/logox.png" rel="icon">
<link href="img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
<link href="css/boxicons.min.css" rel="stylesheet">
<link href="css/quill.snow.css" rel="stylesheet">
<link href="css/quill.bubble.css" rel="stylesheet">
<link href="css/remixicon.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="css/stylex.css" rel="stylesheet">

<!-- =======================================================
  * Template Name: NiceAdmin
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Updated: Apr 20 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">

		<div class="d-flex align-items-center justify-content-between">
			<a href="books" class="logo d-flex align-items-center"> <img
				src="img/logox.png" alt=""> <span
				class="d-none d-lg-block">Manage Library</span>
			</a> <i class="bi bi-list toggle-sidebar-btn"></i>
		</div>
		<!-- End Logo -->

		<div class="search-bar">
			<form class="search-form d-flex align-items-center" method="GET"
				action="${pageContext.request.contextPath}/search-book">
				<input type="text" name="searchTitle" placeholder="Tìm kiếm"
					title="Enter search keyword">
				<button type="submit" title="Search">
					<i class="bi bi-search"></i>
				</button>
			</form>
		</div>
		<!-- End Search Bar -->

		<nav class="header-nav ms-auto">
			<ul class="d-flex align-items-center">

				<li class="nav-item d-block d-lg-none"><a
					class="nav-link nav-icon search-bar-toggle " href="#"> <i
						class="bi bi-search"></i>
				</a></li>
				<!-- End Search Icon-->

				<li class="nav-item dropdown pe-3"><a
					class="nav-link nav-profile d-flex align-items-center pe-0"
					href="#" data-bs-toggle="dropdown"> <img
						src="img/logox.png" alt="Profile"
						class="rounded-circle"> <span
						class="d-none d-md-block dropdown-toggle ps-2">Admin</span>
				</a> <!-- End Profile Iamge Icon -->

					<ul
						class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
						<li class="dropdown-header">
							<h6>Quản trị viên</h6>
						</li>
						<li>
							<hr class="dropdown-divider">
						</li>
						<li><a class="dropdown-item d-flex align-items-center"
							href="/library_manager/login"> <i class="bi bi-box-arrow-right"></i> <span>Đăng xuất</span>
						</a></li>

					</ul> <!-- End Profile Dropdown Items --></li>
				<!-- End Profile Nav -->

			</ul>
		</nav>
		<!-- End Icons Navigation -->

	</header>
	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
	<aside id="sidebar" class="sidebar">

		<ul class="sidebar-nav" id="sidebar-nav">

			<li class="nav-item">
        <a class="nav-link " href="index.html">
          <i class="bi bi-grid"></i>
          <span>Trang chủ</span>
        </a>
      </li><!-- End Dashboard Nav -->
			<!-- End Dashboard Nav -->

			<li class="nav-item"><a class="nav-link collapsed"
				data-bs-target="#components-nav" data-bs-toggle="collapse" href="#">
					<i class="bi bi-menu-button-wide"></i><span>Quản lý mượn - trả sách</span><i
					class="bi bi-chevron-down ms-auto"></i>
			</a>
				<ul id="components-nav" class="nav-content collapse "
					data-bs-parent="#sidebar-nav">
					<li><a href="/library_manager/list-return-book"> <i
							class="bi bi-circle"></i><span>Quản lý trả sách</span>
					</a></li>
					<li><a href="/library_manager/manage-borrow-book"
						> <i class="bi bi-circle"></i><span>Quản
								lý mượn sách</span>
					</a></li>
					<li><a href="/library_manager/overdue-book"> <i
							class="bi bi-circle"></i><span>Quản lý sách quá hạn</span>
					</a></li>
				</ul></li>
			<!-- End Components Nav -->

			<li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#forms-nav" data-bs-toggle="collapse" href="books">
          <i class="bi bi-layout-text-window-reverse"></i><span>Quản lý sách</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="forms-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
          <li>
            <a href="/library_manager/books">
              <i class="bi bi-circle"></i><span>Danh sách sách</span>
            </a>
          </li>
        </ul>
      </li><!-- End Forms Nav -->

			<li class="nav-item"><a class="nav-link collapsed"
				data-bs-target="#tables-nav" data-bs-toggle="collapse" href="#">
					<i class="bi bi-layout-text-window-reverse"></i> <span>Quản
						lý thành viên</span> <i class="bi bi-chevron-down ms-auto"></i>
			</a>
				<ul id="tables-nav" class="nav-content collapse "
					data-bs-parent="#sidebar-nav">
					<li><a href="/library_manager/members"> <i
							class="bi bi-circle"></i> <span>Danh sách thành viên</span>
					</a></li>
				</ul></li>
			<!-- End Tables Nav -->

			<li class="nav-item"><a class="nav-link collapsed"
				data-bs-target="#charts-nav" data-bs-toggle="collapse" href="#">
					<i class="bi bi-person"></i><span>Quản lý Tác giả</span><i
					class="bi bi-chevron-down ms-auto"></i>
			</a>
				<ul id="charts-nav" class="nav-content collapse "
					data-bs-parent="#sidebar-nav">
					<li><a href="/library_manager/authors"> <i class="bi bi-circle"></i><span>Danh sách Tác giả</span>
					</a></li>
				</ul></li>
			<!-- End Charts Nav -->

			<li class="nav-item"><a class="nav-link collapsed"
				data-bs-target="#icons-nav" data-bs-toggle="collapse" href="#">
					<i class="bi bi-gem"></i><span>Quản lý thể loại</span><i
					class="bi bi-chevron-down ms-auto"></i>
			</a>
				<ul id="icons-nav" class="nav-content collapse "
					data-bs-parent="#sidebar-nav">
					<li><a href="/library_manager/categories"> <i
							class="bi bi-circle"></i><span>Danh sách thể loại </span>
					</a></li>
				</ul></li>
			<!-- End Icons Nav -->

			
		</ul>

	</aside>
	<!-- End Sidebar-->

	<main id="main" class="main">

		<div class="pagetitle">
			<h1>Trang chủ</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="index.html">Home</a></li>
					<li class="breadcrumb-item active">Trang chủ</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->
		<%	
			Integer totalMembers = (Integer)request.getAttribute("totalMembers");
			Integer totalBooks = (Integer)request.getAttribute("totalBooks");
			Integer loansOfThisMonth = (Integer)request.getAttribute("loansOfThisMonth");
			Integer loansOfPreviousMonth = (Integer)request.getAttribute("loansOfPreviousMonth");
	        double x = (double) (Math.abs(loansOfThisMonth - loansOfPreviousMonth)) / loansOfPreviousMonth * 100;
	        /* DecimalFormat df = new DecimalFormat("#");
	        double formattedX = Double.parseDouble(df.format(x)); */
	        int xInt = (int)x;
		%>

		<section class="section dashboard">
			<div class="row">

				<!-- Left side columns -->
				<div class="col-lg-12">
					<div class="row">

						<!-- Sales Card -->
						<div class="col-xxl-4 col-md-6">
							<div class="card info-card sales-card">

								<div class="filter">
									<a class="icon" href="#" data-bs-toggle="dropdown"><i
										class="bi bi-three-dots"></i></a>
									<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
										<li class="dropdown-header text-start">
											<h6>Filter</h6>
										</li>

										<li><a class="dropdown-item" href="#">Today</a></li>
										<li><a class="dropdown-item" href="#">This Month</a></li>
										<li><a class="dropdown-item" href="#">This Year</a></li>
									</ul>
								</div>

								<div style="max-height: 148.59px" class="card-body">
									<h5 class="card-title">
										Sách <span>| Số lượng</span>
									</h5>

									<div class="d-flex align-items-center">
										<div
											class="card-icon rounded-circle d-flex align-items-center justify-content-center">
											<i class="bi bi-book"></i>
										</div>
										<div class="ps-3">
											<h6><%=totalBooks %></h6>
											<span class="text-success small pt-1 fw-bold">12%</span> <span
												class="text-muted small pt-2 ps-1">increase</span>
										</div>
									</div>
								</div>

							</div>
						</div>
						<!-- End Sales Card -->

						<!-- Revenue Card -->
						<div class="col-xxl-4 col-md-6">
							<div class="card info-card revenue-card">

								<div class="filter">
									<a class="icon" href="#" data-bs-toggle="dropdown"><i
										class="bi bi-three-dots"></i></a>
									<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
										<li class="dropdown-header text-start">
											<h6>Filter</h6>
										</li>

										<li><a class="dropdown-item" href="#">Today</a></li>
										<li><a class="dropdown-item" href="#">This Month</a></li>
										<li><a class="dropdown-item" href="#">This Year</a></li>
									</ul>
								</div>

								<div style="max-height: 148.59px" class="card-body">
									<h5 style="padding: 20px 0 0px 0" class="card-title">
										Lượt mượn <span>| Tháng này</span>
									</h5>

									<div class="d-flex align-items-center">
										<div
											class="card-icon rounded-circle d-flex align-items-center justify-content-center">
											<i class="bi bi-bar-chart"></i>
										</div>
										<div class="ps-3">
											<h6><%= loansOfThisMonth %></h6>
											<span class="text-success small pt-1 fw-bold"><%=xInt%>%</span> <span
												class="text-muted small pt-2 ps-1"> <% if (loansOfThisMonth - loansOfPreviousMonth > 0) { %>increase
													<% } else { %> decrease <% } %>
												</span>

										</div>
									</div>
								</div>

							</div>
						</div>
						<!-- End Revenue Card -->

						<!-- Customers Card -->
						<div class="col-xxl-4 col-xl-12">

							<div class="card info-card customers-card">

								<div class="filter">
									<a class="icon" href="#" data-bs-toggle="dropdown"><i
										class="bi bi-three-dots"></i></a>
									<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
										<li class="dropdown-header text-start">
											<h6>Filter</h6>
										</li>

										<li><a class="dropdown-item" href="#">Today</a></li>
										<li><a class="dropdown-item" href="#">This Month</a></li>
										<li><a class="dropdown-item" href="#">This Year</a></li>
									</ul>
								</div>

								<div style="max-height: 148.59px" class="card-body">
									<h5 class="card-title">
										Thành viên <span>| This Year</span>
									</h5>

									<div class="d-flex align-items-center">
										<div
											class="card-icon rounded-circle d-flex align-items-center justify-content-center">
											<i class="bi bi-people"></i>
										</div>
										<div class="ps-3">
											<h6><%= totalMembers %></h6>
											<span class="text-danger small pt-1 fw-bold">12%</span> <span
												class="text-muted small pt-2 ps-1">decrease</span>

										</div>
									</div>

								</div>
							</div>

						</div>
						<!-- End Customers Card -->

						<!-- Reports -->
						<div class="col-12">
							<div class="card">
								<div class="filter">
									<a class="icon" href="#" data-bs-toggle="dropdown"><i
										class="bi bi-three-dots"></i></a>
									<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
										<li class="dropdown-header text-start">
											<h6>Filter</h6>
										</li>
										<li><a class="dropdown-item" href="#">Today</a></li>
										<li><a class="dropdown-item" href="#">This Month</a></li>
										<li><a class="dropdown-item" href="#">This Year</a></li>
									</ul>
								</div>

								<div class="card-body">
									<h5 class="card-title">
										Báo cáo <span>/Thàng này</span>
									</h5>

									<!-- Line Chart -->
									<div id="reportsChart"></div>

									<script>
                document.addEventListener("DOMContentLoaded", () => {
                    // Lấy dữ liệu từ Java và truyền vào biểu đồ
                    var dataFromJava = <%= request.getAttribute("data") %>;
                    
                    new ApexCharts(document.querySelector("#reportsChart"), {
                        series: [{
                            name: 'Số lượt mượn sách',
                            data: dataFromJava
                        }],
                        chart: {
                            height: 350,
                            type: 'area',
                            toolbar: {
                                show: false
                            }
                        },
                        markers: {
                            size: 4
                        },
                        colors: ['#4154f1'],
                        fill: {
                            type: "gradient",
                            gradient: {
                                shadeIntensity: 1,
                                opacityFrom: 0.3,
                                opacityTo: 0.4,
                                stops: [0, 90, 100]
                            }
                        },
                        dataLabels: {
                            enabled: false
                        },
                        stroke: {
                            curve: 'smooth',
                            width: 2
                        },
                        xaxis: {
                            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
                        },
                        tooltip: {
                            x: {
                                format: 'MMM'
                            },
                            y: {
                                formatter: function(val) {
                                    return val;
                                }
                            }
                        }
                    }).render();
                });
            </script>
									<!-- End Line Chart -->
								</div>
							</div>
						</div>

						<!-- End Reports -->

						<!-- Recent Sales -->
						<div class="col-12">
							<div class="card recent-sales overflow-auto">

								<div class="filter">
									<a class="icon" href="#" data-bs-toggle="dropdown"><i
										class="bi bi-three-dots"></i></a>
									<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
										<li class="dropdown-header text-start">
											<h6>Filter</h6>
										</li>

										<li><a class="dropdown-item" href="#">Today</a></li>
										<li><a class="dropdown-item" href="#">This Month</a></li>
										<li><a class="dropdown-item" href="#">This Year</a></li>
									</ul>
								</div>

								<div class="card-body">
									<h5 class="card-title">
										Thành viên vàng <span>| Tháng này</span>
									</h5>

									<table class="table table-borderless datatable">
										<thead>
											<tr>
												<th scope="col">ID</th>
												<th scope="col">Họ và tên</th>
												<th scope="col">Số điện thoại</th>
												<th style="text-align: center" scope="col">Số lượt mượn tháng này</th>
											</tr>
										</thead>
										
										<tbody>
											<%
											ArrayList<Member> members = (ArrayList<Member>) request.getAttribute("membersVIP");
												for(Member member : members) {
											%>
											<tr>
												<th scope="row"><a href="#"><%=member.getMember_id() %></a></th>
												<td><%=member.getMember_name() %></td>
												<td><%=member.getMember_phone() %></td>
												<td style="text-align: center"><%=member.getTotal_loans() %></td>
											</tr>
											<% } %>
										</tbody>
									</table>

								</div>

							</div>
						</div>
						<!-- End Recent Sales -->

					</div>
				</div>
				<!-- End Left side columns -->

				
			</div>
		</section>

	</main>
	<!-- End #main -->
	<!-- ======= Footer ======= -->
	<!-- End Footer -->

	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Vendor JS Files -->
	<script src="${pageContext.request.contextPath}/js/apexcharts.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>

	<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>


	<script src="/js/tinymce.min.js"></script>
	<script src="/js/validate.js"></script>
	<!-- Template Main JS File -->
	<script src="${pageContext.request.contextPath}/js/main.js"></script>

</body>

</html>