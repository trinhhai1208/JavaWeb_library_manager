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
							href="#"> <i class="bi bi-box-arrow-right"></i> <span>Đăng xuất</span>
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
					<li><a href="/library_manager/manage-borrow-book" class="active" >
							<i class="bi bi-circle"></i><span>Quản lý mượn sách</span>
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
					<h1>Quản lý mượn sách</h1>
					<nav>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="books">Home</a></li>
							<li class="breadcrumb-item"><a href="books">Quản lý mượn - trả sách</a></li>
							<li class="breadcrumb-item active">Quản lý mượn sách</li>
						</ol>
					</nav>
				</div>
				<div class="col-4 d-flex">
					<div class="refresh-button me-3">
						<form
							action="${pageContext.request.contextPath}/manage-borrow-book"
							method="post">
							<input type="hidden" name="action" value="refresh">
							<button type="submit" class="btn btn-outline-secondary">Làm
								mới</button>
						</form>
					</div>
					<div class="save-button">
						<form
							action="${pageContext.request.contextPath}/manage-borrow-book"
							method="post">
							<input type="hidden" name="action" value="confirm">
							<button type="submit" class="btn btn-outline-secondary">Xác
								nhận mượn sách</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- End Page Title -->

		<section class="section dashboard">
			<div class=row>
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<%
							Member selectedMember = (Member) request.getAttribute("selectedMember");
							%>
							<div id="search-form"
								style="display: <%=(selectedMember == null) ? "block" : "none"%>;">
								<form
									action="${pageContext.request.contextPath}/manage-borrow-book"
									method="get">
									<h3 class="card-title">Tìm kiếm người dùng</h3>
									<div class="input-group mb-3">
										<input type="text" class="form-control"
											placeholder="Nhập tên người dùng bạn muốn tìm kiếm"
											name="member_name" aria-label="Recipient's username"
											aria-describedby="button-addon2">
										<button class="btn btn-outline-secondary" type="submit"
											id="button-addon2">Tìm kiếm</button>
									</div>
								</form>
							</div>
							<div id="search-results">
								<%
								ArrayList<Member> memberByName = (ArrayList<Member>) request.getAttribute("memberByName");
								String member_name = request.getParameter("member_name");
								if (member_name != null && memberByName != null && !memberByName.isEmpty()) {
								%>
								<h6>Kết quả tìm kiếm</h6>
								<div class="table-responsive">
									<table class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>Id</th>
												<th>Tên</th>
												<th>Số điện thoại</th>
												<th>Email</th>
												<th>Địa chỉ</th>
												<th>Chọn</th>
											</tr>
										</thead>
										<tbody>
											<%
											for (Member member : memberByName) {
											%>
											<tr>
												<td><%=member.getMember_id()%></td>
												<td><%=member.getMember_name()%></td>
												<td><%=member.getMember_phone()%></td>
												<td><%=member.getMember_email()%></td>
												<td><%=member.getMember_address()%></td>
												<td>
													<form
														action="${pageContext.request.contextPath}/manage-borrow-book"
														method="post">
														<input type="hidden" name="selectedMemberId"
															value="<%=member.getMember_id()%>">
														<button type="submit" class="btn btn-outline-primary">Chọn</button>
													</form>
												</td>
											</tr>
											<%
											}
											%>
										</tbody>
									</table>
								</div>
								<%
								} else if (member_name != null) {
								%>
								<p>
									Không tìm thấy người dùng với tên:
									<%=member_name%></p>
								<%
								}
								%>
							</div>

							<!-- Hiển thị thông tin thành viên đã chọn -->
							<div id="selected-member-info">
								<%
								if (selectedMember != null) {
								%>
								<h6 class="card-title">Thông tin thành viên đã chọn</h6>
								<div>
									<p>
										<strong>Tên:</strong>
										<%=selectedMember.getMember_name()%></p>
									<p>
										<strong>Số điện thoại:</strong>
										<%=selectedMember.getMember_phone()%></p>
									<p>
										<strong>Email:</strong>
										<%=selectedMember.getMember_email()%></p>
									<p>
										<strong>Địa chỉ:</strong>
										<%=selectedMember.getMember_address()%></p>
									<form
										action="${pageContext.request.contextPath}/manage-borrow-book"
										method="post">
										<input type="hidden" name="removeSelectedMember" value="true">
										<button type="submit" class="btn btn-outline-danger">Thay
											đổi</button>
									</form>
								</div>
								<%
								}
								%>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="card">

						<div class="card-body">
							<h3 class="card-title">Tìm kiếm sách</h3>
							<div class="search-book">
								<div class="row">
									<div class="col-lg-8">
										<div class="input-book">
											<form
												action="${pageContext.request.contextPath}/manage-borrow-book"
												method="get">
												<div class="input-group mb-3">
													<input type="text" class="form-control" name="keyword"
														placeholder="Nhập từ khóa tìm kiếm"
														aria-label="Search keyword"
														aria-describedby="button-addon2">
													<button class="btn btn-outline-secondary" type="submit"
														id="button-addon2">Tìm kiếm</button>
												</div>
										</div>
									</div>
									<div class="col-lg-4">
										<div class="input-book">
											<select class="form-select" name="searchType"
												aria-label="Default select example">
												<option selected value="title">Tìm kiếm theo tên
													sách</option>
												<option value="category">Tìm kiếm theo thể loại</option>
												<option value="author">Tìm kiếm theo tác giả</option>
											</select>
											</form>
										</div>
									</div>
								</div>
							</div>

							<!-- Ô sách được chọn -->
							<div class="selected-books">
								<h5 class="card-title">Sách được chọn</h5>
								<div class="table-responsive"
									style="padding-top: 10px; border-top: 1px solid black">
									<table class="table table-striped">
										<thead>
											<tr>
												<th scope="col">Tên sách</th>
												<th scope="col">Thông tin chi tiết</th>
												<th scope="col">Hành động</th>
											</tr>
										</thead>
										<tbody id="selected-book-table-body">
											<%
											ArrayList<Book> selectedBooks = (ArrayList<Book>) request.getAttribute("selectedBooks");
											if (selectedBooks != null && !selectedBooks.isEmpty()) {
												for (Book book : selectedBooks) {
											%>
											<tr>
												<td><%=book.getBook_title()%></td>
												<td>ISBN: <%=book.getBook_isbn()%>, Năm xuất bản: <%=book.getBook_published_year()%>,
													Số lượng: <%=book.getBook_number_of_copies()%></td>
												<td>
													<form
														action="${pageContext.request.contextPath}/manage-borrow-book"
														method="post">
														<input type="hidden" name="action" value="remove">
														<input type="hidden" name="bookIdToRemove"
															value="<%=book.getBook_id()%>">
														<button type="submit" class="btn btn-outline-danger">Xoá</button>
													</form>
												</td>
											</tr>
											<%
											}
											} else {
											%>
											<tr>
												<td colspan="3">Chưa có sách nào được chọn</td>
											</tr>
											<%
											}
											%>
										</tbody>
									</table>
								</div>
							</div>

							<div class="list-book table-responsive mt-3"
								style="padding-top: 10px; border-top: 1px solid black">
								<div class="card-title">Kết quả tìm kiếm</div>
								<table class="table table-striped">
									<thead>
										<tr>
											<th scope="col">#</th>
											<th scope="col">Tên sách</th>
											<th scope="col">ISBN</th>
											<th scope="col">Năm xuất bản</th>
											<th scope="col">Số lượng</th>
											<th scope="col">Chọn</th>
										</tr>
									</thead>
									<tbody>
										<%
										ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("books");
										if (books != null) {
											for (Book book : books) {
										%>
										<tr>
											<th scope="row"><%=book.getBook_id()%></th>
											<td><%=book.getBook_title()%></td>
											<td><%=book.getBook_isbn()%></td>
											<td><%=book.getBook_published_year()%></td>
											<td><%=book.getBook_number_of_copies()%></td>
											<td>
												<form
													action="${pageContext.request.contextPath}/manage-borrow-book"
													method="post">
													<input type="hidden" name="action" value="add"> <input
														type="hidden" name="bookIdToAdd"
														value="<%=book.getBook_id()%>">
													<button type="submit" class="btn btn-outline-primary">Chọn</button>
												</form>
											</td>
										</tr>
										<%
										}
										} else {
										%>
										<tr>
											<td colspan="6">Không tìm thấy sách nào</td>
										</tr>
										<%
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
	<script>
    // JavaScript code to display messages from the server-side
    <% String message = (String) request.getAttribute("message"); %>
    <% if (message != null && !message.isEmpty()) { %>
        alert("<%= message %>");
        <% request.removeAttribute("message"); // Đảm bảo thông báo chỉ hiển thị một lần %>
    <% } %>
</script>


</body>
</html>