<template>
	<div class="content">
		<div class="topMenu_title">
			<van-icon @click="back" name="arrow-left" size="25" class="ret"/>
			<h4 class="title">好图推荐</h4>
		</div>
		<div class="swiper">
			<van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
			  <van-swipe-item class="swipe-item" v-for="(item,index) in prominantImgList" :key="index">
				  <van-image
					class="swipe-img"
				    fit="scale-down"
				    :src="item" 
					rel="external nofollow"/>
			  </van-swipe-item>
			</van-swipe>
		</div>
		<div class="topMenu">
			<div class="topMenu-item selected" @click="select(0)">热度倒序</div>
			<div class="topMenu-item" @click="select(1)">时间倒序</div>
		</div>
		<div class="con_1">
			<van-pull-refresh v-model="refreshing" @refresh="onRefresh">
				<van-list
				class="list"
				  v-model:loading="loading"
				  :finished="finished"
				  finished-text="没有更多了"
				  @load="onLoad">
				  <div class="img_cell" v-for="(item,index) in imgList" :key="index" >
					  <div class="item_top">
						  <div class="username">sazkuyo</div>
						  <div class="date_title">2021-12-19</div>
					  </div>
					  <van-image
					  		class="img_show"
					  		fit="scale-down"
					  		lazy-load
					  		@click="showImg(index)"
					  		:src="item.sm_src"/>
							<div class="item_msg"></div>
				  </div>
				</van-list>
			</van-pull-refresh>
		</div>
		<div class="con_2">
			<van-pull-refresh v-model="refreshing" @refresh="onRefresh">
				<van-list
				class="list"
				  v-model:loading="loading"
				  :finished="finished"
				  finished-text="没有更多了"
				  @load="onLoad">
				  <div class="img_cell" v-for="(item,index) in itemList" :key="item.pid" >
					  <van-image
					  		class="img_show"
					  		fit="cover"
					  		lazy-load
					  		@click="showImg(index)"
					  		:src="item.sm_src"
					  />
				  </div>
				</van-list>
				</van-pull-refresh>
		</div>
		<van-image-preview  class="img_item_show" :show-index="false" :start-position="index" v-if="isShow" v-model:show="show" :images="imgList" @change="onChange" :before-close="closeImg">
		  <template v-slot:cover>
			  <div class="todo">
				  <div class="msg"></div>
				  <div class="concelltion">
					  <van-icon name="like-o" size="6vw"></van-icon>
				  </div>
				  <div class="download" @click="down">
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
	</div>
</template>

<script>
	import axios from 'axios';
	import { Swipe, SwipeItem,Image,Lazyload,List,ImagePreview,PullRefresh } from 'vant';
	export default{
		data(){
			return{
				prominantImgList: [
					'http://qiniu.ultraer.icu/0a5feb86-11c8-4064-a0d0-281fc2d2eef2.jpg',
					'http://qiniu.ultraer.icu/8359ffb3-e963-4db9-9cd8-e9c32abad6ed.png',
					'http://qiniu.ultraer.icu/5145d5eb-07fd-4c4d-b850-1555de715c52.png',
					'http://qiniu.ultraer.icu/8ea5c11c-77f8-424a-9bda-7dca47ebc557.png',
				],
				cid:'',
				itemList:[],
				imgList:[],
				currentPage:0,
				loading: false,
				finished: false,
				show:true,
				refreshing:false,
				index:0,
				isShow: false,
				imgList: [
					{
						src:'http://qiniu.ultraer.icu/45f62654-d968-4974-94cf-957c37a9be23.png',
						sm_src:'http://qiniu.ultraer.icu/22532533-402a-4add-be31-8f47bb31be03.png'
					},
					{
						src:'http://qiniu.ultraer.icu/35aaa9dc-baa9-4b87-8668-9533fdc50035.jpg',
						sm_src:'http://qiniu.ultraer.icu/921f6ed2-c580-46ad-8a22-f92714995e43.jpg'
					},
					{
						src:'http://qiniu.ultraer.icu/40787418-8910-4a3c-a206-2ad78d204c28.jpg',
						sm_src:'http://qiniu.ultraer.icu/1e44267c-0c9f-4b52-8938-fa9ff2f02475.jpg'
					},
					{
						src:'http://qiniu.ultraer.icu/c352016c-f75e-4efd-881a-d176e8a3ca20.png',
						sm_src:'http://qiniu.ultraer.icu/7c833a17-05a8-4ffe-af36-3af13cfc0f81.png'
					},
					{
						src:'http://qiniu.ultraer.icu/74a892c4-a202-4050-966c-3e8809ee173d.png',
						sm_src:'http://qiniu.ultraer.icu/a9b225f5-f202-4944-b259-5a376672bb5e.png'
					},
				],
				index:0,
				value: ''
			}
		},
		created() {
			this.$store.state.toucherShow = false;
		},
		destroyed() {
			this.$store.state.toucherShow = true;
		},
		methods:{
			select(index){
				var lis = document.getElementsByClassName('topMenu-item');
				lis[0].classList.remove('selected');
				lis[1].classList.remove('selected');
				lis[index].classList.add('selected');
				if(index == 0){
					var con1 = document.getElementsByClassName('con_1')[0];
					var con2 = document.getElementsByClassName('con_2')[0];
					con2.style.display = 'none';
					con1.style.display = 'block';
				}else{
					var con1 = document.getElementsByClassName('con_1')[0];
					var con2 = document.getElementsByClassName('con_2')[0];
					con2.style.display = 'block';
					con1.style.display = 'none';
				}
				window.scrollTo({
				    top: 0, 
				    behavior: "smooth" 
				});
			},
			back(){
				history.back();
			},
			onLoad(){
				if(this.$route.query.cid != null){
					axios.get('http://42.240.141.211/WallpaperController/queryPhoneByCategoryOrderByTime?cid='+this.cid+'&currentPage='+this.currentPage)
						.then(res => {
							if(res.data.length == 0){
								 this.loading = false;
								 this.finished = true;
							}else{
								for(var i = 0 ; i < res.data.length; i++){
									this.itemList.push(res.data[i]);
									this.imgList.push(res.data[i].src);
								}
								this.loading = false;
								this.currentPage = this.currentPage + 1;
								setInterval( ()=> {
									if(this.itemList.length>0){
										this.refreshing = false;
										return;
									}
								},2000)
							}
						});
				}else{
					this.value = this.$route.query.category;
					console.log('http://42.240.141.211/WallpaperController/searchPhoneByKeyWord?keyword='+this.value+'currentPage='+this.currentPage);
					axios.get('http://42.240.141.211/WallpaperController/searchPhoneByKeyWord?keyword='+this.value+'&currentPage='+this.currentPage)
						.then(res => {
							if(res.data.length == 0){
								 this.loading = false;
								 this.finished = true;
							}else{
								for(var i = 0 ; i < res.data.length; i++){
									this.itemList.push(res.data[i]);
									this.imgList.push(res.data[i].src);
								}
								this.loading = false;
								this.currentPage = this.currentPage + 1;
								setInterval( ()=> {
									if(this.itemList.length>0){
										this.refreshing = false;
										return;
									}
								},2000)
							}
						});
				}
			},
			onChange(newIndex){
				this.currentImg = newIndex;
			},
			onRefresh(){
				this.finished = false;
				this.itemList.length = 0;	
				// 重新加载数据
				// 将 loading 设置为 true，表示处于加载状态
				this.loading = true;
				this.currentPage = 0;
				this.onLoad();
			},
			closeImg(){
				this.isShow = !this.isShow;
			},
			showImg(index){
			  this.index = index;
			  this.isShow = !this.isShow;
			  return false;
			},
			down(){
				console.log(this.imgList[this.index]);
				var name = this.imgList[this.index].split('/').slice(-1)[0];
				this.downloadIamge(this.imgList[this.index],name);
			}, 
			moveFile(fileEntry,dstEntry){
				// move the file to a new directory and rename it
				fileEntry.moveTo( dstEntry, "newfile.txt", function( entry ){
					plus.console.log("New Path: " + entry.fullPath);
				}, function( e ){
					alert( e.message );
				} );
			},
			downloadIamge(imgsrc, name) {
				var relativePath = "/Pictures/WAPA/"+name;
				var dtask = plus.downloader.createDownload(imgsrc, {
					method:'GET',
					filename:'_downloads/'+name
				}, function(d, status) {  
				                if (status == 200) {
									var fileSaveUrl = plus.io.convertLocalFileSystemURL(d.filename);
									plus.gallery.save(fileSaveUrl,() => {
										alert('保存完成');
									})
									// var environment = plus.android.importClass('android.os.Environment');
									// var sdRoot = environment.getExternalStorageDirectory();
									// var File = plus.android.importClass('java.io.File');
									// var fileIn = plus.android.importClass('java.io.FileInputStream');
									// var fileOut = plus.android.importClass('java.io.FileOutputStream');
									// // var inStream = plus.android.importClass('java.io.InputStream');
									// // var out = plus.android.importClass('java.io.OutputStream');
									// console.log(sdRoot+'/Pictures/WAPA');
									// var fd = new File(sdRoot+'/Pictures/WAPA');
									// var byte = plus.android.importClass('java.lang.Byte');
									
									// var temp = new File(fileSaveUrl);
									// if(!fd.exists()){
									// 	fd.mkdirs();
									// }
									// streamFrom = new fileIn(fileSaveUrl);
									// streamTo = new fileOut(fd+'/'+name);
									// var len = 0;
									// while((len = streamFrom.read()) != -1){
									// 	streamTo.write(len);
									// }
									// streamFrom.close();
									// streamTo.close();
									
								} else { 
				                    alert('下载失败');
								}
				            });
				            //启动下载任务  
				            dtask.start();  
			},
		}
	}
</script>

<style scoped="scoped" lang="scss">
	.content{
		width: 100vw;
		height: auto;
		background-color: #dfdfdf;
		.swiper{
			width: 100%;
			height: 25vh;
			margin: 0;
			
		}
		.topMenu_title{
			width: 100%;
			height: 7vh;
			background-color: #000;
			display: flex;
			flex-direction: row;
			justify-content: center;
			align-items: center;
			.ret{
				padding-left: 10px;
				flex: 6;
				color: #fff;
			}
			.title{
				flex: 9;
				color: #fff;
			}
		}
		
		.topMenu{
			width: 100%;
			height: 7vh;
			position: sticky;
			top:0;
			background-color: #fff;
			display: flex;
			flex-direction: row;
			justify-content: center;
			align-items: center;
			box-sizing: border-box;
			z-index: 999;
			padding: 0px 0vw;
			.topMenu-item{
				flex: 1;
				line-height: 5vh;
				text-align: center;
				font-weight: 510;
				margin: 0 1vw;
				border-bottom: 3px solid #eeeeee;
				transition: all 0.2s;
			}
			.selected{
				border-bottom: 3px solid #ffa361;
			}
		}
	}
	.my-swipe .van-swipe-item {
	  margin:0;
	  color: #fff;
	  line-height: 25vh;
	  text-align: center;
	  background-color: #2d2d2d;
	}
	.swipe-img{
		width: 100%;
		height: 25vh;
	}
	.swipe-item{
		width: 100%;
		height: 25vh;
		overflow: hidden;
	}
	.con_1{
		width: 100%;
		height: auto;
		box-sizing: border-box;
	}
	.list{
		width: 100%;
		height: auto;
		margin-top: -1vh;
		.img_cell{
			width: 100%;
			height: auto;
			margin: 2vh 0px;
			background-color: #fff;
			position: relative;
			.item_top{
				width: 100%;
				height: 4vh;
				background-color: #fff;
				display: flex;
				flex-direction: row;
				.username{
					flex: 1;
					line-height: 4vh;
					color: #fff;
					font-size: 1.1rem;
					margin-left: 3vw;
					color:#000;
				}
				.date_title{
					padding-right: 3vw;
					flex:4;
					line-height: 4vh;
					text-align: right;
					font-size: 0.8rem;
					color: #3b9ce6;
				}
			}
			.img_show{
				width: 100%;
				height: 33vh;
				background-color: #000;
			}
			.item_msg{
				width: 100%;
				height: 6vh;
				background-color: rgba($color: #fff, $alpha: 1);
				bottom: 0vh;
			}
		}
	}
	.con_2{
		width: 100%;
		height: 180vh;
		display: none;
		background-color: #4aff5f;
	}
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
	.img_item_show{
		z-index: 11;
	}
</style>
