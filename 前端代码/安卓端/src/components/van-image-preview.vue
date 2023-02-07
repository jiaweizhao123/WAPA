<template>
	<van-image-preview v-model:show="show" :images="images" @change="onChange">
	  <template v-slot:index>第{{ index }}页</template>
	</van-image-preview>
</template>

<script>
	import { ref } from 'vue';
	
	export default {
	  setup() {
	    const show = ref(false);
	    const index = ref(0);
	    const images = [
	      'https://img.yzcdn.cn/vant/apple-1.jpg',
	      'https://img.yzcdn.cn/vant/apple-2.jpg',
	    ];
	    const onChange = (newIndex) => {
	      index.value = newIndex;
	    };
	
	    return {
	      show,
	      index,
	      images,
	      onChange,
	    };
	  },
	};
</script>

<style>
</style>
