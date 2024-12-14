<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quên mật khẩu</title>
    <!-- CSS links -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/stylex.css" rel="stylesheet">
    <style>
        /* Custom CSS to match the design */
        body {
            background-color: #f9fafb;
            font-family: 'Open Sans', sans-serif;
        }
        .forgot-password-container {
            max-width: 400px;
            margin: 0px auto;
            padding: 30px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }
        .forgot-password-container h2 {
            font-size: 24px;
            font-weight: 700;
            margin-bottom: 15px;
            text-align: center;
            color: #012970;
        }
        .forgot-password-container p {
            font-size: 14px;
            color: #6c757d;
            text-align: center;
        }
        .form-control {
            height: 45px;
            border-radius: 5px;
        }
        .btn-primary {
            background-color: #4154f1;
            border-color: #4154f1;
            border-radius: 5px;
            padding: 10px;
        }
        .btn-primary:hover {
            background-color: #3a47e6;
        }
        .back-to-login {
            text-align: center;
            margin-top: 10px;
        }
        .back-to-login a {
            color: #4154f1;
            text-decoration: none;
            font-size: 14px;
        }
        .back-to-login a:hover {
            text-decoration: underline;
        }
        .margin-top-200 {
        	margin-top: 200px;
        }
    </style>
</head>

<body>
	<div class="d-flex justify-content-center py-4 margin-top-200">
                <a href="index.html" class="logo d-flex align-items-center w-auto">
                <img src="img/logox.png" alt=""><span>Manager Library</span>                </a>
      </div>
   <div class="forgot-password-container">
        <h2>Quên mật khẩu</h2>
        <p>Vui lòng nhập email để đặt lại mật khẩu</p>
        <form action="/library_manager/reset-password" method="post">
            <div class="form-group mb-3">
                <label for="email">Email của bạn</label>
                <input type="email" name="email" id="email" class="form-control" placeholder="Nhập email" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Gửi yêu cầu</button>
        </form>
        <div class="back-to-login">
            <a href="login.jsp">Quay lại đăng nhập</a>
        </div>
        <% if (request.getParameter("success") != null) { %>
        <div class="alert alert-success mt-3" role="alert">
            Email đã được gửi. Vui lòng kiểm tra hộp thư của bạn.
        </div>
        <% } %>
        <% if (request.getParameter("error") != null) { %>
        <div class="alert alert-danger mt-3" role="alert">
            Có lỗi xảy ra. Vui lòng thử lại sau.
        </div>
        <% } %>
    </div>
</body>
</html>