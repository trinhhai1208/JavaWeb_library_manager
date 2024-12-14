<%@page import="util.ConnectionPoolImpl"%>
<%@page import="util.ConnectionPool"%>
<%@page import="dao.MemberFunctionImpl"%>
<%@page import="dao.MemberDAOFunction"%>
<%@page import="model.Member"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Loan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Book"%>
<%@page import="model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Quản lý mượn sách</title>
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
<link href="css/custom.css" rel="stylesheet">

<style>
.custom-input-time {
	border: 2px solid #007bff;
	border-radius: 0.25rem;
	padding: 0.5rem 1rem;
	font-size: 1rem;
	width: 100%;
}

.custom-input-time:focus {
	border-color: #0056b3;
	box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
	outline: none;
}
</style>
<script>
	function toggleSearchResults(showResults) {
		var searchResultsDiv = document.getElementById("search-results");
		var searchFormDiv = document.getElementById("search-form");

		if (showResults) {
			searchResultsDiv.style.display = "block";
			searchFormDiv.style.display = "none";
		} else {
			searchResultsDiv.style.display = "none";
			searchFormDiv.style.display = "block";
		}
	}
	
</script>

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

					<ul>
						
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

			<li class="nav-item"><a class="nav-link collapsed"
				href="/library_manager/dashboard"> <i class="bi bi-grid"></i> <span>Trang
						chủ</span>
			</a></li>
			<!-- End Dashboard Nav -->

			<li class="nav-item"><a class="nav-link "
				data-bs-target="#components-nav" data-bs-toggle="collapse" href="#">
					<i class="bi bi-menu-button-wide"></i><span>Quản lý mượn - trả sách</span><i
					class="bi bi-chevron-down ms-auto"></i>
			</a>
				<ul id="components-nav" class="nav-content collapse show"
					data-bs-parent="#sidebar-nav">
					<li><a href="/library_manager/list-return-book" > <i
							class="bi bi-circle"></i><span>Quản lý trả sách</span>
					</a></li>
					<li><a href="/library_manager/manage-borrow-book"  >
							<i class="bi bi-circle"></i><span>Quản lý mượn sách</span>
					</a></li>
					<li><a href="/library_manager/overdue-book" class="active"> <i
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
			<li class="nav-item">
				<a class="nav-link collapsed" data-bs-target="#tables-nav" data-bs-toggle="collapse" href="#">
					<i class="bi bi-layout-text-window-reverse"></i>
					<span>Quản lý thành viên</span>
					<i class="bi bi-chevron-down ms-auto"></i>
				</a>
				<ul id="tables-nav" class="nav-content collapse "
					data-bs-parent="#sidebar-nav">
					<li>
						<a href="/library_manager/members"> 
							<i class="bi bi-circle"></i>
							<span>Danh sách thành viên</span>
						</a>
					</li>
				</ul>
			</li>
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
			<div class="row">
				<div class="col-8">
					<h1>Quản lý sách quá hạn</h1>
					<nav>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="books">Home</a></li>
							<li class="breadcrumb-item"><a href="books">Quản lý mượn - trả sách</a></li>
							<li class="breadcrumb-item active">Quản lý sách quá hạn</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
		<!-- End Page Title -->

		<section class="section dashboard">
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<div class="card-title">Những cuốn sách quá hạn trả</div>
							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
										<tr>
											<th scope="col">#</th>
											<th scope="col">Tên sách</th>
											<th scope="col">Người mượn</th>
											<th scope="col">Ngày mượn</th>
											<th scope="col">Ngày hết hạn</th>
										</tr>
									</thead>
									<tbody>
										<%
										ArrayList<Loan> loans = (ArrayList<Loan>) request.getAttribute("overdueBooks");
										if (loans != null) {
											for (Loan loan : loans) {
										%>
										<tr>
											<th scope="row"><%=loan.getLoan_id()%></th>
											<td><a class="text-black" href="bookInfo?bookId=<%=loan.getBook_id()%>"><%=loan.getBook_title()%></a></td>
											<td><a class="text-black" href="memberInfo?memberId=<%=loan.getMember_id()%>"><%=loan.getMember_name()%></a></td>
											<td><%=loan.getLoan_date()%></td>
											<td><%=loan.getLoan_due_date()%></td>
										</tr>
										<%
										}
										}
										%>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

	</main>

	<footer id="footer" class="footer">
		<div class="copyright">
			&copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights
			Reserved
		</div>
		<div class="credits">
			Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
		</div>
	</footer>

	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<script src="${pageContext.request.contextPath}/js/apexcharts.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>

	<script src="/js/echarts.min.js"></script>

	<script src="/js/tinymce.min.js"></script>
	<script src="/js/validate.js"></script>
	<script src="/js/main.js"></script>

</body>
</html>