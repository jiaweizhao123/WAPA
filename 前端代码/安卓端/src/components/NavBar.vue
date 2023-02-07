<template>
	<van-nav-bar title="WAPA" class="topMenu"  left-arrow>
	 <template #left>		
		<div is-link @click="showPopup">
			<van-icon name="wap-nav" size="18" />
		</div>
	  </template>
	  <template #right>
		<router-link to="/search">
			<van-icon name="search" size="18" />
		</router-link>
	  </template>
	</van-nav-bar>
	<van-popup class="pop" v-model:show="show" position="left" :style="{ height: '100%',width: '70%' }" >
		<leftmenu></leftmenu>
	</van-popup>
</template>
<script>
	import { Toast } from 'vant';
	import { useStore } from "vuex";
	import { ref } from 'vue';
	import Leftmenu from './LeftMenu'
	export default{
		 setup() {
			const store = useStore();
		    const onClickLeft = () => history.back();
		    const onClickRight = () => Toast('按钮');
			const show = ref(false);
			const showPopup = () => {
				show.value = true;
			};
		    return {
		      onClickLeft,
		      onClickRight,
			  show,
			  showPopup,
		    };
		  },
		  components:{
			  leftmenu: Leftmenu
		  }
	}
</script>

<style scoped lang="scss">
	body{
		.topMenu{
			background-color: #000000;
			--van-nav-bar-title-text-color: #fff;
			--van-nav-bar-icon-color: #fff;
			--van-nav-bar-z-index: 9;
		}
		.pop{
			z-index: 99;
		}
	}
</style>
