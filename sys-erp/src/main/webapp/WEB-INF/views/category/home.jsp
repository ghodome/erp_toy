<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="vue-app">

	<h1>{{title}}</h1>
	
	<div>
		<span @click="loadMainCategoryList">전체</span>
		<span v-for="(category, index) in history">
			&gt; 
			<span @click="loadUpperCategoryList(category, index)">
				{{category.categoryName}}
			</span>
		</span> 
	</div>
	
	<hr>
	
	<div v-for="(category, index) in categoryList">
		<span @click="loadSubCategoryList(category)">{{category.categoryName}}</span>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button>수정</button>
		<button>삭제</button>
	</div>
	
	<br>
	
	<button>신규 카테고리 추가</button>

</div>



<!-- 템플릿 및 JS -->
<script>
	const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script src="${pageContext.request.contextPath}/js/category/index.js"></script>