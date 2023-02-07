const vm = new Vue({
	el: "#app",
	data: {
		phone_category:[],
		pc_category:[],
		category: [],
		tag_count: 1,
		BUCKET: 'sazkuyo',
		DOMAIN: 'http://qiniu-ultraer-icu-idvhk62.qiniudns.com',
		formData: {
			fileName: "",
			cid: "",
			tags: "",
		},
		img: {
			src: '',
			sm_src: '',
			token: '',
		}
	},
	created(){
		axios.get("http://42.240.141.211/CategoryController/queryALl")
		.then(res => {
			var len = res.data.length;
			this.pc_category = res.data;
			for(var i = 0;i < len;i++){
				this.category.push(res.data[i]);
			}
		});
		axios.get('http://42.240.141.211/CategoryController/queryAllPhoneWallpaper')
			.then(res => {
				this.phone_category = res.data;
			});
	},
	methods: {
		file_select(e){
			if(e.target.value!=""){
				var f = e.target.files[0];
				console.log(f);
				$('.file_tip')[0].innerHTML = e.target.files[0].name;
				var reader = new FileReader();
				reader.readAsDataURL(f);
				var temp = '';
				reader.onload = function() {
					temp = this.result;
					$('.fileUpload')[0].style.background = 'url('+reader.result+') no-repeat'
					$('.fileUpload')[0].style.backgroundSize = '100% 100%';
				}
				$('.fileUpload')[0].style.background = 'url('+reader.result+') no-repeat';
				console.log(reader);
			}
		},
		add_tag(index){
			if(index == 1){
				this.tag_count++;
			}
			else{
				if(this.tag_count > 1)
					this.tag_count--;	
			}
		},
		img_upload(e){
			var tags = '';
			var tagList = $('.tags');
			for(var i=0;i<this.tag_count;i++){
				if(i==0)
					this.formData.tags+=tagList[i].value;
				else
					this.formData.tags+='/'+tagList[i].value;
			}
			this.formData.fileName = event.target[0].value;
			this.formData.cid =  event.target[1].value;
			var formData = JSON.stringify(this.formData);
			var file = event.target[0].files[0];
			console.log(file);
			// var reader = new FileReader();
			// reader.readAsDataURL(file);
			// console.log(reader);
			// reader.
			var url = this.$refs.pc_select.checked ? 'http://42.240.141.211/WallpaperController/insert':'http://42.240.141.211/WallpaperController/insertPhone';
			
			axios({
				headers: {
				    'Content-Type': 'application/json;charset=UTF-8'
				},
				method:"post",
				url:url,
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
					console.log(this.img);
					const config = {
						//是否使用cdn加速域名
						useCdnDomain: true,
						//默认传输协议
						upprotocol: 'http',
						//上传区域，此处为华南
						region: qiniu.region.z2,
					};
					const putExtra1 = {
					  fname: this.img.src.split('/').slice(-1)[0],
					};
					const putExtra2 = {
					  fname: this.img.sm_src.split('/').slice(-1)[0],
					};
					const observable = qiniu.upload(file, this.img.src.split('/').slice(-1)[0], res.data.token, putExtra1, config);				
					const subscription = observable.subscribe(observer); // 上传开始
					const options = {
					  quality: 0.82,
					  noCompressIfLarger: true,
					  maxWidth: 720,
					  maxHeight: 560,
					};
					qiniu.compressImage(file, options).then(data => {
					  const observable = qiniu.upload(data.dist, this.img.sm_src.split('/').slice(-1)[0], res.data.token, putExtra2, config)
					  const subscription = observable.subscribe(observer) // 上传开始
					});
					alert('上传成功');
				}
			});
			this.formData.tags = '';
		},
		changeCategory(index){
			if(index == 0){
				this.category = this.pc_category;
			}else{
				this.category = this.phone_category;
			}
		}
	},
});