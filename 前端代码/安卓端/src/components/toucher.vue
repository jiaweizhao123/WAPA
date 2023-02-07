<template>
	<div class="toucher" @click="toTOP()">
		<van-icon name="back-top"></van-icon>
	</div>
</template>

<script>
	export default{
		methods:{
			toTOP(){
				window.scrollTo({ 
				    top: 0, 
				    behavior: "smooth" 
				});
			}
		},
		props:['unaccept'],
		
	}
</script>

<style scoped="scoped" lang="scss">
	.toucher{
		position: fixed;
		width: 50px;
		height: 50px;
		border-radius: 50px;
		color: #fff;
		text-align: center;
		line-height: 50px;
		bottom: 7vh;
		right: 2vw;
		background-color: rgba($color: #000000, $alpha: 0.6);
		z-index: 10;
	}
</style>
