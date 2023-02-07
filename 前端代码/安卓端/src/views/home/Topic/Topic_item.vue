<template>
	<div class="item_con" ref="item_con">
		<div class="about_it">
			<span ref="title">厚涂与动漫的碰撞--画师WLOP的作品赏析...</span>
		</div>
	</div>
</template>

<script>
	export default{
		props:{
			bk:{
				type:String,
				default:'bk.jpg'
			},
			title:{
				type:String,
				default:'厚涂与动漫的碰撞--画师WLOP的作品赏析...'
			}
		},
		mounted() {
			this.$refs.item_con.style.background = "url("+this.bk+") no-repeat";
			this.$refs.item_con.style.backgroundSize = 'cover';
			this.$refs.title.innerHTML = this.title;
		}
	}
</script>

<style scoped="scoped" lang="scss">
	.item_con{
		width: 96vw;
		height: 25vh;
		border-radius: 5vw;
		margin: 2vh 2vw;
		background-color: #fff;
		position: relative;
		
		.about_it{
			overflow: hidden;
			position: absolute;
			bottom: 0;
			width: 100%;
			text-align: center;
			color: #fff;
			height: 6vh;
			line-height: 6vh;
			background-color: rgba(0, 0, 0, 0.6);
			border-radius: 0vw 0vw 5vw 5vw;
		}
	}
</style>
