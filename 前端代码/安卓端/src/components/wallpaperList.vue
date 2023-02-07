<template>
	<div class="con">
		<van-nav-bar
		  class="navBar"
		  :title="category"
		  left-arrow
		  @click-left="onClickLeft"
		/>
		<router-view></router-view>
	</div>
</template>

<script>
	import { reactive,toRefs } from 'vue';
	import {useRoute,useRouter} from 'vue-router';
	import { NavBar } from 'vant';
	export default{
		setup(){
			const route = useRoute();
			const router = useRouter();
			const category = route.query.category;
			const flag = route.query.flag;
			const cid = route.query.cid;
			if(flag == 1){
				router.push({path:'/detail/smshow', query:{category:category,cid:cid}})
			}else{
				router.push({path:'/detail/lgshow', query:{category:category,cid:cid}})
			}
			const onClickLeft = () => {
				router.push({path:'/home'});
			};
			console.log(category)
			return {
				category,
				onClickLeft,
			}
		},
		mounted() {
			
		}
	}
</script>

<style scoped="scoped" lang="scss">
	.navBar{
		background-color: #000000;
		--van-nav-bar-title-text-color: #fff;
		--van-nav-bar-text-color: #fff;
		--van-nav-bar-icon-color: #fff;
		--van-nav-bar-z-index: 9;
	}
</style>
