<template>
	<div class="random" ref="random">
		<img :src="current_img" class="show_img" />
		<div class="toolBar">
			<div class="itemBar" @click="setWallpaper">设为壁纸</div>
			<div class="itemBar">收藏</div>
			<div class="itemBar" @click="download">下载</div>
			<div class="itemBar">分享</div>
		</div>
		<div class="flush">
			<van-icon name="replay" @click="flush" class="flush_icon" size="40" />
		</div>
	</div>
</template>

<script>
	import axios from 'axios'
	export default {
		data() {
			return {
				current_img: '',
				imgAll: []
			}
		},
		methods: {
			getAll() {
				axios.get('http://42.240.141.211/WallpaperController/queryPhoneAllOrderByTime')
					.then(res => {
						this.imgAll = res.data;
						this.flush();
					});
			},
			flush() {
				var index = 0;
				var len = this.imgAll.length;
				while (index == 0 || this.imgAll[index].cid == 7 || this.imgAll[index].cid == 8) {
					index = parseInt((Math.random() * len) + 1);
				}
				this.current_img = this.imgAll[index].src;
			},
			download() {
				var dtask = plus.downloader.createDownload(this.current_img, {
					method: 'GET',
					filename: '_downloads/' + name
				}, function(d, status) {
					if (status == 200) {
						var fileSaveUrl = plus.io.convertLocalFileSystemURL(d.filename);
						plus.gallery.save(fileSaveUrl, () => {
							alert('保存完成');
						})
					} else {
						alert('下载失败');
					}
				});
				//启动下载任务  
				dtask.start();
			},
			setWallpaper() {
				var that=this;
				setTimeout(() => {
					var WallpaperManager = plus.android.importClass('android.app.WallpaperManager');
					var Canvas = plus.android.importClass(' android.graphics.Canvas');
					var Matrix = plus.android.importClass(' android.graphics.Matrix');
					var Rect = plus.android.importClass(' android.graphics.Rect');
					var Config = plus.android.importClass(' android.graphics.Bitmap.Config');
					var Main = plus.android.runtimeMainActivity();
					var wallpaperManager = WallpaperManager.getInstance(Main);
					var BitmapFactory = plus.android.importClass('android.graphics.BitmapFactory');
					var dtask = plus.downloader.createDownload(this.current_img, {
						method: 'GET',
						filename: '_downloads/' + name
					}, function(d, status) {
						if (status == 200) {
							var fileSaveUrl = plus.io.convertLocalFileSystemURL(d.filename);
							var bitmap = BitmapFactory.decodeFile(fileSaveUrl);
							var h = plus.android.invoke(bitmap,"getHeight");
							var w = plus.android.invoke(bitmap,"getWidth");
							var h0 = h / plus.display.resolutionHeight;
							var w0 = h / plus.display.resolutionWidth;
							try{
								wallpaperManager.setBitmap(bitmap);
								wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTME); 
								alert('设置成功');
							} catch(e) {
								console.log(e);
								alert('设置失败');
							}
						} else {
							alert('下载失败');
						}
					});
					//启动下载任务  
					dtask.start();
				},100);
			}
		},
		mounted() {
			this.getAll();
		}
	}
</script>

<style lang="scss">
	@keyframes restart{
		from{
			opacity: 0;
		}
		to{
			opacity: 1;
		}
	}
	.random{
		animation: restart 1s;
	}
	.random {
		width: 100vw;
		height: 100vh;

		.show_img {
			position: absolute;
			z-index: 10;
			width: 100%;
			height: 100%;
		}

		.toolBar {
			position: fixed;
			top: 5vh;
			right: 0vw;
			width: 25vw;
			height: 50vh;
			// background-color: rgba($color: #000000, $alpha: 0.2);
			z-index: 11;
			display: flex;
			flex-direction: column;
			box-sizing: border-box;
			align-items: center;

			.itemBar {
				text-align: center;
				line-height: 5vh;
				width: 80%;
				height: 5vh;
				margin: 1vh 0vw;
				color: #fff;
				background-color: rgba($color: #000, $alpha: 0.5);
			}
		}

		.flush {
			position: fixed;
			bottom: 7vh;
			right: 4vw;
			width: 60px;
			height: 60px;
			z-index: 10;
			background-color: rgba($color: #000000, $alpha: 0.6);
			color: #fff;
			border-radius: 50px;

			.flush_icon {
				margin: 8.5px;
			}
		}
	}
</style>
