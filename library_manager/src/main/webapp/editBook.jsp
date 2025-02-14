<%@page import="java.util.ArrayList"%>
<%@page import="model.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Chỉnh sửa thông tin sách</title>
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

			<li class="nav-item"><a class="nav-link collapsed" href="/library_manager/dashboard">
					<i class="bi bi-grid"></i> <span>Trang chủ</span>
			</a></li>
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
				<a class="nav-link collapsed" data-bs-target="#forms-nav" data-bs-toggle="collapse" href="#">
					<i class="bi bi-layout-text-window-reverse"></i>
					<span>Quản lý sách</span>
					<i class="bi bi-chevron-down ms-auto"></i>
				</a>
				<ul id="forms-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
					<li>
						<a href="/library_manager/books" class="active"> 
							<i class="bi bi-circle"></i>
							<span>Danh sách sách</span>
						</a>
					</li>
				</ul>
			</li>
			<!-- End Forms Nav -->

			<li class="nav-item">
				<a class="nav-link " data-bs-target="#tables-nav" data-bs-toggle="collapse" href="#">
					<i class="bi bi-journal-text"></i>
					<span>Quản lý thành viên</span>
					<i class="bi bi-chevron-down ms-auto"></i>
				</a>
				<ul id="tables-nav" class="nav-content collapse show"
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
				<div class="col-10">
					<h1>Chỉnh sửa thông tin sách</h1>
					<nav>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="books">Home</a></li>
							<li class="breadcrumb-item">Quản lý sách</li>
							<li class="breadcrumb-item">Danh sách sách</li>
							<li class="breadcrumb-item active">Chỉnh sửa thông tin sách</li>
						</ol>
					</nav>
				</div>
				<div class="col-2"></div>
			</div>
		</div>
		<!-- End Page Title -->

		<section class="section dashboard">
			<div class="row">
				<%
            		String message = request.getParameter("message");
            		if ("addSuccess".equals(message)) {
        		%>
				<script type="text/javascript">
            		alert('Thêm sách thành công!');
        		</script>
				<%
            		}
        		%>
				<!-- Vertical Form -->
				<div class="card">
					<div class="card-body">
						<!-- Vertical Form -->
						<form class="row g-3"
							action="${pageContext.request.contextPath}/update-book"
							method="post">
							<input type="hidden" name="bookId" value="${book.book_id}">
							<div class="col-12 pt-3">
								<label for="input_category" class="form-label">Thể loại</label>
								<input type="text" class="form-control" id="input_category"
									name="category">
							</div>
							<div class="col-12">
								<label for="input_title" class="form-label">Tên sách</label> <input
									type="text" class="form-control" id="input_title" name="title">
							</div>
							<div class="col-12">
								<label for="input_author" class="form-label">Tác giả</label> <input
									type="text" class="form-control" id="input_author"
									name="author">
							</div>
							<div class="col-12">
								<label for="input_isbn" class="form-label">ISBN</label> <input
									type="text" class="form-control" id="input_isbn" name="isbn">
							</div>
							<div class="col-12">
								<label for="input_published_year" class="form-label">Năm
									xuất bản</label> <input type="text" class="form-control"
									id="input_published_year" name="published_year">
							</div>
							<div class="col-12">
								<label for="input_number_of_copies" class="form-label">Số
									lượng</label> <input type="text" class="form-control"
									id="input_number_of_copies" name="number_of_copies">
							</div>
							<div class="text-center">
								<button type="submit" class="btn btn-primary">Xong</button>
								<button type="reset" class="btn btn-secondary">Clear</button>
							</div>
						</form>

						<!-- Vertical Form -->

					</div>
				</div>
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

	<script src="/js/echarts.min.js"></script>

	<script src="/js/tinymce.min.js"></script>
	<script src="/js/validate.js"></script>
	<!-- Template Main JS File -->
	<script src="/js/main.js"></script>

</body>

</html>