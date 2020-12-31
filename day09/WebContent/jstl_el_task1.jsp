<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 실습(조건문)</title>
</head>
<body>
<!--  
	사용자에게 이름을 입력받고 확인 버튼을 눌렀을 때
	admin일 경우 관리자, member일 경우 "일반 회원 입력한이름", 그 외에는 "비회원"을 출력합니다.
	※다른 페이지 없이 현재 페이지에서 처리하고, 결과가 출력될 때에는 input 태그와 submit버튼이 나오게 해서는 안됩니다.(결과만 나오도록 합니다.)
	※form 태그의 속성인 action을 생략하면 새로고침이 됩니다.(현재 페이지로 이동)
	※el문과 jstl만 사용합니다. (자바스크립트 X)
-->
	<!-- input 태그로 입력한 값 저장 -->
	<c:set var="id" value="${param.id}" />
	<!-- 일반 회원 목록 -->
	<c:set var="memberlist">홍길동,고길동,유재석,정형돈,노홍철</c:set>
	<!-- 1. 입력된 값이 없을 경우 입력창 출력 -->
	<c:if test="${id == null}">
		<form action="#">
			<input type="text" name="id">
			<input type="submit" value="확인">
		</form>	
	</c:if>
	<!-- 2. 입력된 값이 있을 경우 그에 맞는 문구 출력 -->
	
	<!-- admin,일반회원은 true, 비회원은 false로 구분하기 위한 flag -->
	<!-- forEach문에서 break문의 효과를 주기 위해서도 사용 -->
	<c:set var="flag" value="false" />
	<c:if test="${id != null}">
		<c:if test="${id == 'admin'}">
			관리자
			<c:set var="flag" value="true" />
		</c:if>
		<c:forEach var="item" items="${memberlist}">
			<c:if test="${!flag}">
				<c:if test="${item eq id}">
					일반 회원 ${id}
					<c:set var="flag" value="true" />
				</c:if>
			</c:if>
		</c:forEach>
		<!-- flag 값이 false일 경우(값이 바뀌지 않았을 경우), admin,일반 회원도 아닌 경우 -->
		<c:if test="${!flag}">
			비회원
		</c:if>
	</c:if>
</body>
</html>