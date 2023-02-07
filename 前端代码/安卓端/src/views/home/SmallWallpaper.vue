<template>
	<div class="phone_container">
			<van-list
			  v-model:loading="loading"
			  :finished="finished"
			  finished-text="没有更多了"
			  @load="onLoad"
			>
			  <div class="category_cell" v-for="item in itemList" :key="item.cid" :title="item.cname" >
			  				  <CategoryItem :title="item.cname" :cid="item.cid"></CategoryItem>
			  </div>
			</van-list>
	</div>
</template>

<script>
	import { ref, onMounted,reactive } from 'vue';
	import axios from 'axios';
	import { Image as VanImage,Lazyload,List,ImagePreview } from 'vant';
	import CategoryItem from './subItem/categoryContainer.vue'
	export default{
		data(){
			return{
				itemList:[],
				loading: false,
				finished: false,
				currentPage: 0
			}
		},
		mounted() {
			
		},
		methods:{
			onLoad(){
				axios.get('http://42.240.141.211/CategoryController/queryPhoneByPage?page='+this.currentPage)
				.then((res) => {
					if (res.data.length == 0) {
						this.loading = false;
						this.finished = true;
					}else{
						for(var i=0;i < res.data.length ; i++){
							this.itemList.push(res.data[i]);
						}
						console.log(this.itemList)
						this.currentPage = this.currentPage + 1;
						this.loading = false;
					}
				});
			}
		},
		  setup() {
			
				
				const showImg = () => {
					ImagePreview(['https://tse1-mm.cn.bing.net/th/id/R-C.655e27314e0d2832106bba75be77eaf9?rik=46bqxcBhEvWC6A&riu=http%3a%2f%2fimg.keaitupian.cn%2fuploads%2fallimg%2f200513%2f2-2005131522543b.jpg&ehk=HUu6wM6vnJb8G6w3TSvp1ZY%2fDNCrDmAf%2b1ySWHvyy5c%3d&risl=&pid=ImgRaw&r=0']);
				}
			
			    return {
				  showImg
			    };
			  },
		components:{
			CategoryItem
		}
	}	
</script>

<style scoped="scoped" lang="scss">
	.phone_container{
		min-height: 90vh;
		margin-top: 0.5vh;
		margin-bottom: 7vh;
	}
	.category_cell{
		display: inline-flex;
		flex: 1;
		width: 100vw;
		height: 33vh;
		border-radius: 2vw;
		background-color: #fff;
		margin:0.5vh 0vw;
		overflow: hidden;
	}
	.img_show{
		width: 100%;
		height: 100%;
	}
</style>
