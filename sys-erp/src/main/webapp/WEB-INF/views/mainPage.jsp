<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>KH14mini</title>

<%-- 윤곽선 확인 CSS --%>
<!-- <link rel="stylesheet" type="text/css" href="/css/test.css"> -->
</head>
<body>

세션에 들어있는 내용 =  ${sessionScope.loginId}

<%-- footer.jsp에 존재하는 내용을 불러오도록 설정 --%>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
</body>
</html>