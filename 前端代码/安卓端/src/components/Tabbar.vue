<template>
	<van-tabbar class="tabbar" z-index="3" v-model="active" safe-area-inset-bottom="true">
		<van-tabbar-item icon="home-o" @click="toLink('/homeView/home')">
				主界面
		</van-tabbar-item>
		<van-tabbar-item  icon="apps-o" @click="toLink('/homeView/artcle')">
				拓展页
		</van-tabbar-item>
		<van-tabbar-item icon="friends-o" @click="toLink('/homeView/about')">
				关于我们
		</van-tabbar-item>
	</van-tabbar>
</template>
<script>
	import {
		reactive,
		toRefs,
		ref
	} from 'vue';
	import {
		Tabbar,
		TabbarItem
	} from 'vant';
	import { useRouter } from 'vue-router';
	export default {
		setup() {
			const router = useRouter();
			const state = reactive({
				myname: 'sazkuyo',
				myage: 20,
			})
			const handleChange = () => {
				state.myname = 'zhao'
				state.myage = 21
			}
			const active = ref(0)
			return {
				...toRefs(state),
				handleChange,
				active,
				router
			}
		},
		components: {
			Tabbar,
			TabbarItem
		},
		methods:{
			toLink(url){
				if(url!='/homeView/home'){
					this.$store.state.toucherShow = false;
				}else{
					this.$store.state.toucherShow = true;
				}
				 this.$router.push({path:url, query:{}})
			}
		}
	}
</script>

<style scoped>
	.link{
		width: 100%;
		height: 100%;
		color: #000;
		background-color: red;
	}
</style>
