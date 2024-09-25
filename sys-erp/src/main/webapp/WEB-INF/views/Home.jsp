<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" data-bs-theme="auto">

<head>

<script src="/js/color-modes.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>KH14mini</title>

<%-- header.jsp에 존재하는 내용을 불러오도록 설정 --%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<%-- 윤곽선 확인 CSS --%>
<!-- <link rel="stylesheet" type="text/css" href="/css/test.css"> -->

<style>
@media ( min-width : 992px) {
	.rounded-lg-3 {
		border-radius: .3rem;
	}
}

.modal-backdrop {
	transition: none !important;
	opacity: 0.5 !important;
}
</style>
</head>

<body>






	<div class="container col-xl-10 col-xxl-8 px-4 py-5">
		<div class="row align-items-center g-lg-5 py-5">
			<div class="col-lg-7 text-center text-lg-start">
				<h1 class="display-4 fw-bold lh-1 text-body-emphasis mb-3">Vertically
					centered hero sign-up form</h1>
				<p class="col-lg-10 fs-4">Below is an example form built
					entirely with Bootstrap’s form controls. Each required form group
					has a validation state that can be triggered by attempting to
					submit the form without completing it.</p>
			</div>
			<div class="col-md-10 mx-auto col-lg-5">
				<!-- 폼 -->
				<form action="/emp/login"
					class="p-4 p-md-5 border rounded-3 bg-body-tertiary" method="post">
					<!-- Input -->
					<div class="form-floating mb-3">
						<input type="text" class="form-control" id="floatingInput"
							placeholder="" name="empId"> <label for="floatingInput">아이디</label>
					</div>
					<!-- Input -->
					<div class="form-floating mb-3">
						<input type="password" class="form-control" id="floatingPassword"
							placeholder="" name="empPassword"> <label
							for="floatingPassword">패스워드</label>
					</div>
					<!-- 체크 박스 -->
					<div class="checkbox mb-3">
						<label> <input type="checkbox" value="remember-me">
							Remember me
						</label>
					</div>
					<!-- 회원 가입 버튼 -->
					<button class="w-100 btn btn-lg btn-primary" type="submit">Sign
						in</button>
					<small class="text-body-secondary d-block text-end">Need an
						account? <a href="#" data-bs-toggle="modal"
						data-bs-target="#myModal">Sign up</a>
					</small>
					<hr class="my-4">
					<small class="text-body-secondary">By clicking Sign up, you
						agree to the terms of use.</small>
				</form>
				<!-- 폼 -->
			</div>
		</div>
	</div>




	<!-- 모달 시작 -->
	<div class="modal fade" id="myModal" tabindex="-1"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-bs-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="myModalLabel">회원 가입</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<!-- 모달 안에 들어갈 내용 -->
					<form action="/emp/join" method="post">
						<!-- 여기에 action URL 설정 -->
						<div class="mb-3">
							<!-- 아이디 입력 필드 -->
							<label for="modalInputId" class="form-label">아이디</label> <input
								type="text" class="form-control" id="modalInputId" name="empId"
								required>

						</div>
						<div class="mb-3">
							<label for="modalInputPassword" class="form-label">비밀번호</label> <input
								type="password" class="form-control" id="modalInputPassword"
								name="empPassword" required>
							<!-- 비밀번호 입력 필드 -->
						</div>
						<div class="modal-footer">
							<!-- 모달 닫기 버튼 -->
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">닫기</button>

							<!-- 폼 제출 버튼 -->
							<button type="submit" class="btn btn-primary">가입하기</button>

						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 모달 끝 -->


</body>

</html>