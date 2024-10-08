const app = Vue.createApp({
	data(){
		return {
			title : "카테고리 관리",
			history:[],
			categoryList:[],
		};
	},
	methods:{
		async loadMainCategoryList(){//메인리스트 불러오기(뎁스 0)
			const resp = await axios.get(`${contextPath}/rest/category/categoryDepth/0`);
			this.categoryList = resp.data;
			this.history = [];
		},
		async loadSubCategoryList(category) {//하위리스트 불러오기
			const resp = await axios.get(`${contextPath}/rest/category/categoryOrigin/${category.categoryCode}`);
			this.categoryList = resp.data;
			this.history.push(category);
		},
		async loadUpperCategoryList(category, index) {//상단으로 이동할 경우
			const resp = await axios.get(`${contextPath}/rest/category/categoryOrigin/${category.categoryCode}`);
			this.categoryList = resp.data;
			this.history.splice(index+1);
		}
	},
	watch:{
		
	},
	created(){
		this.loadMainCategoryList();
	},
});
app.mount("#vue-app");