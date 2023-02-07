<template>
	<button @click="showImg">click</button>
	<van-image-preview v-if="isShow" v-model:show="show" :images="images" @change="onChange" :before-close="showImg">
	  <template v-slot:cover>
		  <div class="todo">
			  <div class="msg">{{msg}}</div>
			  <div class="concelltion">
				  <van-icon name="like-o" size="6vw"></van-icon>
			  </div>
			  <div class="download">
				  <van-icon name="down" size="6vw"></van-icon>
			  </div>
			  <div class="share">
				  <van-icon name="share-o" size="6vw"></van-icon>
			  </div>
			  <div class="more">
				  <van-icon name="ellipsis" size="6vw"></van-icon>
			  </div>
		  </div>
	  </template>
	</van-image-preview>
</template>

<script>
	import { ref } from 'vue';
	export default {
		data(){
			return{
				isShow: false,
				msg: '1242x2680'
			}
		},
	  setup() {
	    const show = ref(true);
	    const index = ref(0);
	    const images = [
	      'https://tse1-mm.cn.bing.net/th/id/R-C.655e27314e0d2832106bba75be77eaf9?rik=46bqxcBhEvWC6A&riu=http%3a%2f%2fimg.keaitupian.cn%2fuploads%2fallimg%2f200513%2f2-2005131522543b.jpg&ehk=HUu6wM6vnJb8G6w3TSvp1ZY%2fDNCrDmAf%2b1ySWHvyy5c%3d&risl=&pid=ImgRaw&r=0',
	      'https://tse1-mm.cn.bing.net/th/id/R-C.655e27314e0d2832106bba75be77eaf9?rik=46bqxcBhEvWC6A&riu=http%3a%2f%2fimg.keaitupian.cn%2fuploads%2fallimg%2f200513%2f2-2005131522543b.jpg&ehk=HUu6wM6vnJb8G6w3TSvp1ZY%2fDNCrDmAf%2b1ySWHvyy5c%3d&risl=&pid=ImgRaw&r=0',
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
	methods: {
		showImg(){
			console.log('lala')
			this.isShow = !this.isShow;
			return false;
		}
	}
	}
</script>

<style scoped="scoped" lang="scss">
	.todo{
		position: fixed;
		bottom: 0;
		width: 100vw;
		height: 7vh;
		line-height: 7vh;
		color: #fff;
		 background-color:rgba(0, 0, 0, 0.6);
		z-index: 999;
		display: flex;
		flex-direction: row;
		.msg{
			flex: 4;
			text-align: center;
		}
		.concelltion{
			flex: 1;
			text-align: center;
		}
		.download{
			text-align: center;
			flex: 1;
		}
		.share{
			text-align: center;
			flex:1;
		}
		.more{
			flex: 1;
			text-align: center;
		}
	}
</style>
