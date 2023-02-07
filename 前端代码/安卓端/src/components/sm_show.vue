<template>
	<div class="con">
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
	import { ref,onMounted,reactive,toRefs } from 'vue';
	import {useRoute,useRouter} from 'vue-router';
	import axios from 'axios';
	import { Image as VanImage,Lazyload,List,ImagePreview,PullRefresh  } from 'vant';
	export default{
		data(){
			return{
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
				imgList: [],
				index:0,
				value: ''
			}
		},
		mounted() {
			this.cid = this.$route.query.cid;
		},
		methods:{
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
		},
	}
</script>
<style scoped lang="scss">
	.img_show{
		width: 100%;
		height:  100%;
		padding: 0vh 0vw;
	}
	.img_cell{
		display: inline-block;
		width: 32.93vw;
		height:  32vh;
		margin: 0 0.2vw;
		margin-top: -0.4vh;
		padding: 0;
	}
	.list{
		letter-spacing: 0;
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
