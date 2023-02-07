<template>
	<van-tabs v-model:active="active" sticky animated>
	  <van-tab v-for="index in 4" :title="'选项 ' + index">
	    内容 {{ index }}
	  </van-tab>
	</van-tabs>
</template>

<script>
	import { Tab, Tabs } from 'vant';
	import { ref } from 'vue';
	export default{
		setup() {
		    const active = ref(0);
		    const onClickTab = ({ title }) => Toast(title);
		    return {
		      active,
		      onClickTab,
		    };
		  },
	}
</script>

<style>
</style>
