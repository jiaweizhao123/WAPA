<template>
	<div id="nav">
			<NavBar></NavBar>
				<router-view></router-view>
			<Tabbar v-show="this.$store.state.TabbarShow"></Tabbar>
	</div>
</template>

<script>
	import Tabbar from './Tabbar.vue'
	import NavBar from './NavBar.vue'
	export default{
		setup(){
			
		},
		components:{
			Tabbar,
			NavBar
		},
		mounted() {
			this.$store.state.toucherShow = true;
		}
	}
</script>

<style>
</style>
