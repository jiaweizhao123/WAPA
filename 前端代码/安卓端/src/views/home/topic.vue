<template>
	<div class="topic_con">
		<topicitem v-for="i in imgList" :title="i.title" :bk="i.src"></topicitem>
	</div>
</template>

<script>
	import topicitem from './Topic/Topic_item.vue'
	export default{
		data(){
			return{
				imgList:[
					{
						title:'从这里开始——画师WLOP的作品专题...',
						src:'https://s4.ax1x.com/2021/12/04/o0fHDH.jpg'
					},
					{
						title:'每一帧都是壁纸——遇见动漫中的绝美场景...',
						src:'https://s4.ax1x.com/2021/12/04/o0hFVs.png'
					},
					{
						title:'星空专题，快来寻找属于你的一片星空吧...',
						src:'https://s4.ax1x.com/2021/12/04/o0hCrQ.jpg'
					},
				]
			}
		},
		components:{
			topicitem
		}
	}
</script>

<style scoped="scoped" lang="scss">
	.topic_con{
		width: 100vw;
		height: 100vh;
		background-color: #eeeeee;
	}
</style>
