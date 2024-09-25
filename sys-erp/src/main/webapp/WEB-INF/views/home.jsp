<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" data-bs-theme="auto">

<head>
<script src="/js/color-modes.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>KH14mini</title>

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
					class="p-4 p-md-5 border rounded-3 bg-body-tertiary" method="post"
					autocomplete="off">
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
					<h5 class="modal-title" id="myModalLabel">Sign Up</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<!-- 모달 안에 들어갈 내용 -->
				<div class="modal-body">

					<!-- 여기에 action URL 설정 -->
					<form action="/emp/join" method="post" autocomplete="off">

						<!-- 아이디 -->
						<div class="row mb-3 align-items-center">
							<div class="col-auto">
								<label for="modalInputId" class="col-form-label">LoginId</label>
							</div>
							<div class="col-auto">
								<input type="text" id="modalInputId" class="form-control"
									aria-describedby="IdHelpInline" name="empId">
							</div>
							<div class="col-auto">
								<span id="IdHelpInline" class="form-text"> 이자리는 아이디 체크폼
									확인란용 설명이 들어갑니다 </span>
							</div>
						</div>

						<!-- 비밀번호 -->
						<div class="row mb-3 align-items-center">
							<div class="col-auto">
								<label for="modalInputPassword" class="col-form-label">Password</label>
							</div>
							<div class="col-auto">
								<input type="password" id="modalInputPassword"
									class="form-control" aria-describedby="passwordHelpInline"
									autocomplete="new-password" name="empPassword">
							</div>
							<div class="col-auto">
								<span id="passwordHelpInline" class="form-text"> Must be
									8-20 characters long. </span>
							</div>
						</div>

						<!-- 이름 -->
						<div class="row mb-3 align-items-center">
							<div class="col-auto">
								<label for="modalInputName" class="col-form-label">Name</label>
							</div>
							<div class="col-auto">
								<input type="text" id="modalInputName" class="form-control"
									autocomplete="new-name" name="empName">
							</div>
						</div>

						<!-- 직급 -->
						<div class="row mb-3 align-items-center">
							<div class="col-auto">
								<label class="col-form-label">Department</label>
							</div>
							<div class="col-auto">
								<select class="form-select" name="empDept">
									<option selected value="">==선택==</option>
									<option value="개발팀">개발팀</option>
									<option value="2">Two</option>
									<option value="3">Three</option>
								</select>
							</div>
						</div>

						<!-- 부서 -->
						<div class="row mb-3 align-items-center">
							<div class="col-auto">
								<label class="col-form-label">Level</label>
							</div>
							<div class="col-auto">
								<select class="form-select" name="empLevel">
									<option selected value="">==선택==</option>
									<option value="개발팀">개발팀</option>
									<option value="2">Two</option>
									<option value="3">Three</option>
								</select>
							</div>
						</div>

						<!-- 성별 -->
						<div class="row mb-3 align-items-center">
							<div class="col-auto">
								<label class="col-form-label">Gender</label>
							</div>
							<div class="col-auto">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="empGender"
										id="flexRadio1" value="M" checked> <label
										class="form-check-label" for="flexRadio1"> 남 </label>
								</div>
							</div>
							<div class="col-auto">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="empGender"
										id="flexRadio2" value="W"> <label
										class="form-check-label" for="flexRadio2"> 여 </label>
								</div>
							</div>
						</div>

						<!-- 전화번호 -->
						<div class="row mb-3 align-items-center">
							<div class="col-auto">
								<label for="modalInputHp" class="col-form-label">Tel</label>
							</div>
							<div class="col-auto">
								<input type="text" id="modalInputHp" class="form-control"
									name="empHp">
							</div>
						</div>

						<!-- 이메일 -->
						<div class="row mb-3 align-items-center">
							<div class="col-auto">
								<label class="col-form-label">Email</label>
							</div>
							<div class="col-auto">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="Username"
										id="Email-Username" name="empEmailUsername"> <span
										class="input-group-text">@</span> <input type="text"
										class="form-control" placeholder="Domain" id="Email-Domain"
										name="empEmailDomain">
								</div>
							</div>
						</div>

						<!-- 최종학력 (선택 작성) -->
						<div class="row mb-3 align-items-center">
							<div class="col-auto">
								<label for="finalEducationDegree" class="col-form-label">최종학력</label>
							</div>
							<div class="col-auto">
								<select class="form-select" id="finalEducationDegree"
									name="empEdu">
									<option selected value="">==선택==</option>
									<option value="고등학교 졸업">고등학교 졸업</option>
									<option value="전문대학 졸업">전문대학 졸업</option>
									<option value="대학 졸업">대학 졸업</option>
									<option value="대학원 졸업">대학원 졸업</option>
									<option value="재학중">재학중</option>
								</select>
							</div>
						</div>

						<!-- 생년월일 및 입사일 -->
						<div class="row mb-3 align-items-center">
							<!-- 생년월일 입력 필드 -->
							<div class="col-auto">
								<label for="dateInputBirth" class="form-label">생년월일</label> <input
									type="date" class="form-control" id="dateInputBirth"
									name="empBirth">
							</div>

							<!-- 입사일 입력 필드 -->
							<div class="col-auto">
								<label for="dateInputSDate" class="form-label">입사일</label> <input
									type="date" class="form-control" id="dateInputSDate"
									name="empSdate">
							</div>
						</div>




						<!-- 우편번호 -->
						<div class="row mb-3">
							<div class="col-auto">
								<input type="text" class="form-control" placeholder="Post"
									name="empPost">
							</div>
						</div>

						<!-- 주소1 -->
						<div class="row mb-3">
							<div class="col-12 col-sm-9">
								<input type="text" class="form-control" placeholder="Address"
									name="empAddress1">
							</div>
						</div>

						<!-- 주소2 -->
						<div class="row mb-3">
							<div class="col-12 col-sm-9">
								<input type="text" class="form-control"
									placeholder="AddressDetail" name="empAddress2">
							</div>
						</div>


						<!-- 모달 내의 푸터 -->
						<div class="modal-footer mb-3">
							<!-- 모달 닫기 버튼 -->
							<!-- 							<button type="button" class="btn btn-secondary" -->
							<!-- 								data-bs-dismiss="modal">닫기</button> -->

							<!-- 폼 제출 버튼 -->
							<button type="submit" class="btn btn-primary">가입하기</button>

						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 모달 끝 -->




	<%-- footer.jsp에 존재하는 내용을 불러오도록 설정 --%>
	<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>


	<!-- 이메일을 합치기위한 스크립트 -->
	<script type="text/javascript">
		document.querySelector('form[action="/emp/join"]').onsubmit = function() {
			const username = document.getElementById('Email-Username').value;
			const domain = document.getElementById('Email-Domain').value;
			const email = username + '@' + domain;

			const emailInput = document.createElement('input');
			emailInput.type = 'hidden';
			emailInput.name = 'empEmail';
			emailInput.value = email;
			this.appendChild(emailInput);
		};
	</script>
</body>

</html>