<template>
	<div class="all_container">
	<div class="category_title">{{title}}</div>
	<van-icon @click="showDetail(title)" class="more" name="arrow" />
	<div class="container">
	  <van-image
		class="img_show"
		fit="cover"
		v-for="(item,index) in itemList"
		:key="item.id"
		lazy-load
		@click="showImg(index)"
		:src="item.sm_src"
	  />
	</div>
	</div>
</template>

<script>
	import { useRouter } from 'vue-router';
	import { ref,onMounted,reactive,toRefs } from 'vue';
	import axios from 'axios';
	import { Image as VanImage,Lazyload,List,ImagePreview,Icon } from 'vant';
	export default{
		props:{
			title:{
				type:String,
				default:"未知"
			},
			cid:{
				type:Number,
				default:1
			}
		},
		data(){
			return{
				itemList:[],
				imgList:[],
				show:false
			}
		},
		mounted() {
			axios.get('http://42.240.141.211/WallpaperController/simpleQueryByCategory?cid='+this.cid)
				.then(res => {
					this.itemList = res.data;
					for(var i = 0 ; i < this.itemList.length ; i++){
						this.imgList.push(this.itemList[i].src);
					}
				});
		},
		methods:{
			showImg(index){
				ImagePreview({
					images:this.imgList,
					startPosition: index,
				});
			},
			onChange(newIndex){
				 index.value = newIndex;
			},
			showDetail(title){
				this.$router.push({path:'/detail', query:{category:title,flag:2,cid:this.cid}});
			}
		},
	}
</script>

<style scoped lang="scss">
	.all_container{
		width: 100%;
		height: 100%;
		padding:0vh 1vw;
		padding-right: 4vw;
		overflow: hidden;
		
	}
	.category_title{
		display: inline-block;
		width: 93%;
		height: 30px;
		padding: 0vh 2vw;
		line-height: 30px;
		font-weight: bold;
	}
	.more{
		display: inline-block;
		width: 2%;
		text-align: center;
	}
	.container{
		width: 100%;
		height: 80%;
		overflow-y: hidden;
		overflow-x:auto;
		white-space:nowrap;
		padding: 0vw 0.5vw;
		.img_show{
			display: inline-block;
			width: 100%;
			height: 100%;
			margin:0vh 0.5vw;
		}
	}
</style>
