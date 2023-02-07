<template>
	<div class="about">
		<van-cell-group>
		  <van-cell title="项目名称"  label="WAPA壁纸" />
		  <van-cell title="项目团队" label="WAPA壁纸团队" />
		  <van-cell title="联系我们" label="Email:sazkuyo@163.com" />
		  <van-cell title="公告" label="暂无公告..." />
		  <van-cell title="版本更新" label="当前版本:v1.0.0 最新版本:v1.0.0" />
		  <van-cell title="更新日志" label="更新了简单功能...." />
		</van-cell-group>
	</div>
</template>

<script>
	import { ref } from 'vue';
	
	export default {
	  setup() {
	    const activeName = ref('1');
	    return { activeName };
	  },
	};
</script>

<style scoped="scoped" lang="scss">

</style>
