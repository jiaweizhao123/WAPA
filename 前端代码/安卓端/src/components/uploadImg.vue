<template>
	<div class="upload">
		<div class="topBar">
			<van-icon @click="back" name="arrow-left" size="25" class="ret"/>
			<h4 class="title">好图推荐</h4>
		</div>
		<div class="content">
			<div class="upload_content">
				<h4 class="pic_title">图片选择</h4>
				<van-uploader class="fileUpload" v-model="fileList" multiple :max-count="1"  :max-size="10000 * 1024" @oversize="onOversize" />
			</div>
			<div class="tag_content">
				<h4>标签选择</h4>
				<div v-for="(item,index) in tags" :key="index"  :class="tags_flag[index] ? 'tag_item_active' : 'tag_item'"  @click="addTag(index)">{{item}}</div>
				<div class="tag_item" @click="showPopup">
					<van-icon name="plus"></van-icon>
				</div>
			</div>
			<div class="btn" @click="upload">图片上传</div>
		</div>
		<van-popup
		  @close="close"
		  v-model:show="show"
		  round
		  position="bottom"
		  :style="{ height: '20%' }"
		  class="pop"
		>
			<h4 class="pop_title">请输入添加标签名</h4>
			<input ref="tag_input" class="tag_input" v-model="current_item" />
		</van-popup>
	</div>
</template>

<script>
	import { Uploader,Popup } from 'vant';
	import axios from 'axios';
	import * as qiniu from 'qiniu-js';
	export default{
		data(){
			return{
				tags: ['动漫','人物','景色','摄影','影视','唯美','游戏','宠物','植物','森林','天空'],
				tags_flag: [false,false,false,false,false,false,false,false,false,false,false],
				currentTag: [],
				fileList: [],
				current_item:'',
				show:false,
				control: false,
				current_file: '',
				formData: {
					fileName: '',
					tags: '',
					uid:'',
				}
			}
		},
		methods:{
			addTag(index){
				if(this.tags_flag[index]){
					var arr_index = this.currentTag.indexOf(this.tags[index]);
					this.currentTag.splice(arr_index,1);
					this.tags_flag[index] = false;
				}else{
					this.currentTag.push(this.tags[index]);
					this.tags_flag[index] = true;
				}
			},
			showPopup(){
				this.show = true;
				this.control = true;
			},
			onOversize(file){
				 console.log('图片大小不能超过10m');
			},
			close(){
				if(this.control){
					if(this.current_item != ''){
						this.tags.push(this.current_item);
						this.current_item='';
						this.control = false;
					}
				}
			},
			afterRead(file){
				this.current_file = file;
				console.log(file);
			},
			back(){
				this.$router.push('/home');
			},
			upload(){
					var tags = '';
					var len = this.currentTag.length;
					for(var i = 0; i < len; i++){
						if(i != (len-1) ){
							tags = tags + this.currentTag[i]+'/';
						}else{
							tags = tags + this.currentTag[i]
						}
					}
					this.formData.fileName = this.fileList[0].file.name;
					this.formData.tags = tags;
					this.formData.uid = 1;
					var formData = JSON.stringify(this.formData);
					var file = this.fileList[0];
					axios({
						headers: {
						    'Content-Type': 'application/json;charset=UTF-8'
						},
						method:"post",
						url: 'http://42.240.141.211/WallpaperController/insertUserWallpaper',
						dataType:'json',
						contentType: 'application/json',
						data: formData,
					}).then(res=>{
						if(res.data == '图片上传失败'){
							alert('图片上传失败');
						}else{
							// console.log(qiniu);
							    const observer = {
							      next(res){
									
							      },
							      error(err){
							        alert('上传错误');
							      },
							      complete(res){
							      }
							    }
							this.img = res.data;
							const config = {
								//是否使用cdn加速域名
								useCdnDomain: true,
								//默认传输协议
								upprotocol: 'http',
							};
							const putExtra1 = {
							  fname: this.img.src.split('/').slice(-1)[0],
							};
							const putExtra2 = {
							  fname: this.img.sm_src.split('/').slice(-1)[0],
							};
							const observable = qiniu.upload(this.fileList[0].file, this.img.src.split('/').slice(-1)[0], res.data.token, putExtra1, config);				
							const subscription = observable.subscribe(observer); // 上传开始
							const options = {
							  quality: 0.82,
							  noCompressIfLarger: true,
							  maxWidth: 720,
							  maxHeight: 560,
							};
							qiniu.compressImage(this.fileList[0].file, options).then(data => {
							  const observable = qiniu.upload(data.dist, this.img.sm_src.split('/').slice(-1)[0], res.data.token, putExtra2, config)
							  const subscription = observable.subscribe(observer) // 上传开始
							});
							alert('上传成功');
						}
				})
			}
		},
		created() {
			this.$store.state.toucherShow = false;
		},
		destroyed() {
			this.$store.state.toucherShow = true;
		},
	}
</script>

<style scoped="scoped" lang="scss">
	.pop{
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	.pop_title{
		flex: 1;
		text-align: center;
	}
	.tag_input{
		flex: 1;
		width: 80%;
		height: 5vh;
		margin-left: 9vw;
	}
	.upload{
		width: 100vw;
		height: 100vh;
		background-color: #e7e7e7;
		.topBar{
			padding: 0 10px;
			box-sizing: border-box;
			width: 100vw;
			height: 5.5vh;
			background-color: #000;
			color: #fff;
			display: flex;
			flex-direction: row;
			justify-content: center;
			align-items: center;
			.ret{
				flex: 6;
			}
			.title{
				flex: 9;
			}
		}
		.content{
			position: relative;
			width: 100vw;
			height: 50vh;
			display: flex;
			flex-direction: column;
			box-sizing: border-box;
			.upload_content{
				flex: 1;
				background-color: #fff;
				margin:1vh 0;
				margin-top: 0;
				padding: 0 4.5vw;
				display: flex;
				flex-direction: column;
				justify-content: center;
				.pic_title{
					flex: 1;
				}
				.fileUpload{
					--van-uploader-size: 100px;
					flex: 4;
				}
			}
			.tag_content{
				flex: 1;
				background-color: #fff;
				padding: 0 4.5vw;
				padding-bottom: 1vh;
				.tag_item{
					display: inline-block;
					margin: 0.5vh 1.5vw;
					width: 15vw;
					line-height: 5vh;
					text-align: center;
					color: #fff;
					border-radius: 10px;
					height: 5vh;
					background-color: #8c8c8c;
				}
				.tag_item_active{
					display: inline-block;
					margin: 0.5vh 1.5vw;
					width: 15vw;
					line-height: 5vh;
					text-align: center;
					color: #fff;
					border-radius: 10px;
					height: 5vh;
					// background-image: linear-gradient(to bottom right,#5fa0f0, #aa00ff);
					background-color: #5fa0f0;
				}
			}
			.btn{
				background-color: #577ed8;
				position: absolute;
				text-align: center;
				width: 90%;
				height: 6vh;
				line-height: 6vh;
				color: #fff;
				margin-top: 3vh;
				font-size: 1.3rem;
				align-self: center;
				bottom: -8vh;
			}
		}
	}
</style>
