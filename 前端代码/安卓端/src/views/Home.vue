<template>
	<div>
		<van-tabs class="tabs" v-model:active="active" @click-tab="onClickTab" sticky animated>
		  <van-tab title="手机壁纸">
			  <SmallWallpaper></SmallWallpaper>
		  </van-tab>
		  <van-tab title="电脑壁纸">
			  <LargeWallpaper></LargeWallpaper>
		  </van-tab>
		  <van-tab title="专题">
			  <Topic></Topic>
		  </van-tab>
		</van-tabs>
	</div>
</template>

<script>
	import { ref, onMounted,toRefs,reactive } from 'vue';
	import { Tab, Tabs } from 'vant';
	import {useRouter} from 'vue-router';
	import SmallWallpaper from './home/SmallWallpaper.vue';
	import LargeWallpaper from './home/LargeWallpaper.vue';
	import Topic from './home/topic.vue'
	export default{
		name:"home",
		 setup() {
			 const state = reactive({
				tabList: ['手机壁纸','电脑壁纸','专题']
			})
		    const loading = ref(true);
			const active = ref(0);
			const router = useRouter();
			const onClickTab = ({ title }) => {
			}
		    onMounted(() => {
		      loading.value = false;
		    });

		    return {
		      // loading,
			  active,
			  onClickTab,
			  ...toRefs(state)
		    };
		  },
		  components:{
			  Tab,
			  SmallWallpaper,
			  LargeWallpaper,
			  Topic
		  },
		  mounted() {
		  }
	}
</script>`

<style scoped="scoped" lang="scss">
	.tabs{
		background-color: #e7e7e7;
	}
</style>
