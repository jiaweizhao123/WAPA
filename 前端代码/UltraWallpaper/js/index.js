$(document).ready(function() {
	// alert($(window).width()); 
	$("#topMenu").css("width", $(window).width());
});
const vm = new Vue({
	el: "#app",
	data: {
		count: 11,
		img_array: [],
		temp_img: {
			src: '',
		},
		category_array: [],
		pop_flag: false,
		maxTop: 0,
		img_flag: true,
		about_flag: false,
		isAbout: false,
		msg_flag: false,
		current_page: 0,
		preload_index: 0,
		category_flag: false,
		flush: true,
		loadType: 1,
		current_category: 0,
		keyword: '',
		pop_flush: true,
		num: 0,
		Demoned: [true, false, false, false, false, false],
		img: {},
		open_flag: false,
		find_flag: false,
		isPhone: false,
	},
	created() {
		axios.get("http://42.240.141.211/WallpaperController/queryAllByLimit?currentPage=" + this.current_page)
			.then(res => {
				var len = res.data.length;
				for (var i = 0; i < len; i++) {
					this.img_array.push(res.data[i]);
				}
				this.current_page++;
				setTimeout(() => {
					this.preLoadImg(0)
				}, 4000);
			});
		axios.get("http://42.240.141.211/CategoryController/queryALl")
			.then(res => {
				var len = res.data.length;
				for (var i = 0; i < len; i++) {
					this.category_array.push(res.data[i]);
				}
				// console.log(this.category_array);
			});
	},
	methods: {

		phone_next(e) {
			var src = '';
			var index;
			if (this.loadType == 1) {
				src = "http://42.240.141.211/WallpaperController/queryAllByLimit?currentPage=" + this
					.current_page;
				index = 0;
			} else if (this.loadType == 2) {
				src = "http://42.240.141.211/WallpaperController/queryAllOrderByTime?currentPage=" + this
					.current_page;
				index = 1;
			} else if (this.loadType == 3) {
				src = "http://42.240.141.211/WallpaperController/queryAllOrderByHeat?currentPage=" + this
					.current_page;
				index = 2;
			} else if (this.loadType == 4) {
				src = "http://42.240.141.211/WallpaperController/queryByCategoryOrderByTime?cid=" + this
					.current_category + '&currentPage=' + this.current_page;
				index = 3;
			} else if (this.loadType == 5) {
				src = "http://42.240.141.211/WallpaperController/searchByKeyWord?keyword=" + this.keyword +
					'&currentPage=' + this.current_page;
				index = 5;
			} else {
				src = "";
			}
			axios.get(src)
				.then(res => {
					var len = res.data.length;
					if (len == 0) {
						alert('已经是底部了');
					}
					for (var i = 0; i < len; i++) {
						this.img_array.push(res.data[i]);
					}
					this.current_page++;
				});
			setTimeout(() => {
				this.preLoadImg(index)
			}, 2000);
			this.count += 11;
		},
		handleScroll(e) {
			if (!this.isAbout) {
				var scrollTop = window.pageYOffset || document.body.scrollTop;
				if (scrollTop > 0) {
					$('.toTop')[0].style.display = 'block';
				} else {
					$('.toTop')[0].style.display = 'none';
				}
				if (!this.isPhone) {
					if (e.detail > 0 || e.wheelDelta < 0) {
						$('#topMenu')[0].style.height = '4vw';
						$('#topMenu')[0].style.paddingTop = '0vw';
						$('#topMenu')[0].style.paddingBottom = '0vw';
					} else {
						$('#topMenu')[0].style.height = '5vw';
						$('#topMenu')[0].style.paddingTop = '0.5vw';
						$('#topMenu')[0].style.paddingBottom = '0.5vw';
					}
					if ((document.body.clientHeight + scrollTop >= document.body.scrollHeight - 600) && (this
							.img_array.length > this.count)) {

						var src = '';
						var index;
						if (this.loadType == 1) {
							src = "http://42.240.141.211/WallpaperController/queryAllByLimit?currentPage=" +
								this.current_page;
							index = 0;
						} else if (this.loadType == 2) {
							src = "http://42.240.141.211/WallpaperController/queryAllOrderByTime?currentPage=" +
								this.current_page;
							index = 1;
						} else if (this.loadType == 3) {
							src = "http://42.240.141.211/WallpaperController/queryAllOrderByHeat?currentPage=" +
								this.current_page;
							index = 2;
						} else if (this.loadType == 4) {
							src = "http://42.240.141.211/WallpaperController/queryByCategoryOrderByTime?cid=" +
								this.current_category + '&currentPage=' + this.current_page;
							index = 3;
						} else if (this.loadType == 5) {
							src = "http://42.240.141.211/WallpaperController/searchByKeyWord?keyword=" + this
								.keyword + '&currentPage=' + this.current_page;
							index = 5;
						} else {
							src = "";
						}
						// console.log(src);
						axios.get(src)
							.then(res => {
								var len = res.data.length;
								for (var i = 0; i < len; i++) {
									this.img_array.push(res.data[i]);
								}
								// console.log(this.img_array.length);
								this.current_page++;
							});
						setTimeout(() => {
							this.preLoadImg(index)
						}, 2000);
						this.count += 11;
					}
				}
			}
		},
		selected_1(e) {
			this.current_page = 0;
			this.img_array.length = 0;
			axios.get("http://42.240.141.211/WallpaperController/queryAllByLimit?currentPage=" + this.current_page)
				.then(res => {
					var len = res.data.length;
					for (var i = 0; i < len; i++) {
						this.img_array.push(res.data[i]);
					}
					this.flush = !this.flush;
					this.flush = !this.flush;
					this.current_page++;
					setTimeout(() => {
						this.preLoadImg(0)
					}, 2000);
				});
			for (var i = 0; i < $(".select").length; i++) {
				$(".select")[i].classList.remove('isSelected');
			}
			e.target.classList.add('isSelected');
			this.count = 11;
			this.num = 0;
			this.img_flag = true;
			this.about_flag = false;
			this.isAbout = false;
			if (this.isPhone)
				$('.toTop')[0].style.display = 'block';
			this.category_flag = false;
			this.find_flag = false;
			this.loadType = 1;
			this.toTop();
			this.reSetDemon(0);
			document.body.style.overflowY = 'auto';
			document.body.style.background = '#395668';
			document.getElementsByClassName('bottom_one')[0].style.display = 'none';
			setTimeout(function() {
				document.getElementsByClassName('bottom_one')[0].style.display = 'block';
			}, 100);
		},
		selected_2(e) {
			for (var i = 0; i < $(".select").length; i++) {
				$(".select")[i].classList.remove('isSelected');
			}
			e.target.classList.add('isSelected');
			this.isAbout = false;
			this.category_flag = false;
			this.about_flag = false;
			this.num = 0;
			this.img_flag = true;
			if (this.isPhone)
				$('.toTop')[0].style.display = 'block';
			this.find_flag = false;
			this.loadType = 2;
			document.body.style.overflowY = 'auto';
			document.body.style.background = '#395668';
			this.count = 11;
			this.current_page = 0;
			axios.get("http://42.240.141.211/WallpaperController/queryAllOrderByTime?currentPage=" + this
					.current_page)
				.then(res => {
					this.current_page++;
					this.img_array.length = 0;
					var len = res.data.length;
					for (var i = 0; i < len; i++) {
						this.img_array.push(res.data[i]);
						// console.log(res.data[i].src);
					}
					this.flush = !this.flush;
					this.flush = !this.flush;
					this.count = 11;
					this.reSetDemon(1);
					setTimeout(() => {
						this.preLoadImg(1)
					}, 2000);
					this.toTop();
				});
		},
		selected_3(e) {
			for (var i = 0; i < $(".select").length; i++) {
				$(".select")[i].classList.remove('isSelected');
			}
			e.target.classList.add('isSelected');
			this.isAbout = false;
			this.category_flag = false;
			this.about_flag = false;
			this.img_flag = true;
			this.find_flag = false;
			if (this.isPhone)
				$('.toTop')[0].style.display = 'block';
			this.loadType = 3;
			document.body.style.background = '#395668';
			this.count = 11;
			this.num = 0;
			this.current_page = 0;
			// console.log("all"+this.img_array.length);
			document.body.style.overflowY = 'auto';
			axios.get("http://42.240.141.211/WallpaperController/queryAllOrderByHeat?currentPage=" + this
					.current_page)
				.then(res => {
					this.current_page++;
					this.img_array.length = 0;
					var len = res.data.length;
					for (var i = 0; i < len; i++) {
						this.img_array.push(res.data[i]);
					}
					// console.log("all"+this.img_array.length);
					this.flush = !this.flush;
					this.flush = !this.flush;
					this.count = 11;
					this.reSetDemon(2);
					setTimeout(() => {
						this.preLoadImg(2)
					}, 2000);
					this.toTop();
				});
		},
		selected_4(e) {
			for (var i = 0; i < $(".select").length; i++) {
				$(".select")[i].classList.remove('isSelected');
			}
			e.target.classList.add('isSelected');
			// alert('haha');
			this.isAbout = false;
			this.category_flag = !this.category_flag;
		},
		selected_5(e) {
			for (var i = 0; i < $(".select").length; i++) {
				$(".select")[i].classList.remove('isSelected');
			}
			e.target.classList.add('isSelected');
			axios.get("http://42.240.141.211/WallpaperController/getRandomWallpaper?pid=1000")
				.then(res => {
					this.img = res.data;
				});
			this.isAbout = false;
			this.loadType = 5;
			this.img_flag = false;
			this.find_flag = true;
			this.about_flag = false;
			this.category_flag = false;
			if (this.isPhone)
				$('.toTop')[0].style.display = 'none';
			this.reSetDemon(4);
			this.toTop();
			$('#topMenu')[0].style.width = '100%';
			document.body.style.overflowY = 'hidden';
		},
		selected_6(e) {
			for (var i = 0; i < $(".select").length; i++) {
				$(".select")[i].classList.remove('isSelected');
			}
			e.target.classList.add('isSelected');
			this.img_flag = false;
			this.about_flag = true;
			this.isAbout = true;
			this.toTop();
			$('#topMenu')[0].style.width = '100%';
			document.body.style.overflowY = 'auto';
			if (this.isPhone)
				$('.toTop')[0].style.display = 'block';
			document.body.style.background = '#fff';
			this.category_flag = false;
			this.find_flag = false;
		},
		mouseOver(e, index) {
			var temp = Math.round((parseFloat($(window).width()) / parseFloat($('.img_item')[0].offsetWidth)));
			// console.log(e.currentTarget.firstChild.firstChild.src);
			// e.currentTarget.firstChild.firstChild.src = this.img_array[index].src;
			var left = ($(window).width() / temp - parseFloat(e.currentTarget.lastChild.style.width))
			.toString() + 'px';
			e.currentTarget.lastChild.style.right = '0vw';
			e.currentTarget.style.zIndex = '2';
		},
		mouseLeave(e, index) {
			e.currentTarget.firstChild.firstChild.src = this.img_array[index].sm_src;

			var temp = Math.round((parseFloat($(window).width()) / parseFloat($('.img_item')[0].offsetWidth)));
			var temp_left = '';
			e.currentTarget.style.zIndex = '1';
			switch (temp) {
				case 1:
					temp_left = '100vw'
					break;
				case 2:
					temp_left = '50vw'
					break;
				case 3:
					temp_left = '33vw'
					break;
			}
			e.currentTarget.lastChild.style.right = '-6vw';
		},
		toTop() {
			window.scrollTo({
				top: 0,
				behavior: "smooth"
			});
			if (!this.isPhone) {
				$('.toTop')[0].style.display = 'none';
				$('#topMenu')[0].style.height = '5vw';
				$('#topMenu')[0].style.paddingTop = '0.5vw';
				$('#topMenu')[0].style.paddingBottom = '0.5vw';
			}
		},
		imgClick(index) {
			if (this.pop_flag) {
				this.pop_flag = !this.pop_flag;
				this.temp_img.src = '';
				document.body.style.overflowY = 'scroll';
			} else {
				this.temp_img.src = this.img_array[index].src;
				this.pop_flag = !this.pop_flag;
				document.body.style.overflowY = 'hidden';

			}
		},

		love_increase(index) {
			axios.get("http://42.240.141.211/WallpaperController/favorite?pid=" + this.img_array[index].pid)
				.then(res => {
					if (res.data == '不能重复操作') {
						alert('不能重复操作');
					} else if (res.data == 'error') {
						alert('系统错误');
					} else {
						alert('操作成功');
					}
				});
		},
		dataURLtoFile(dataurl, filename = 'file') {
			let arr = dataurl.split(',')
			let mime = arr[0].match(/:(.*?);/)[1]
			let suffix = mime.split('/')[1]
			let bstr = atob(arr[1])
			let n = bstr.length
			let u8arr = new Uint8Array(n)
			while (n--) {
				u8arr[n] = bstr.charCodeAt(n)
			}
			return new File([u8arr], `${filename}.${suffix}`, {
				type: mime
			})
		},
		base64ToBlob(code) {
			let parts = code.split(';base64,');
			let contentType = parts[0].split(':')[1];
			let raw = window.atob(parts[1]);
			let rawLength = raw.length;

			let uInt8Array = new Uint8Array(rawLength);

			for (let i = 0; i < rawLength; ++i) {
				uInt8Array[i] = raw.charCodeAt(i);
			}
			return new Blob([uInt8Array], {
				type: contentType
			});
		},
		download(index) {
			let a = document.createElement('a');
			var fileName = this.img_array[index].src.split('/').slice(-1)[0]
			var url = this.img_array[index].src;
			a.href = url;
			a.download = fileName;
			// a.click();
			let x = new XMLHttpRequest()
			x.open('GET', url, true)
			x.responseType = 'blob'
			x.onload = function() {
				let url = window.URL.createObjectURL(x.response)
				let a = document.createElement('a')
				a.href = url;
				a.download = fileName;
				a.click();
			}
			x.send();
		},
		preLoadImg(index) {
			// console.log('开始预加载'+index);
			var len = this.img_array.length;
			var img = new Image();
			var num = parseInt(len / 13) * 12;
			img.src = this.img_array[num].src;
			_this = this;
			var f = this.Demoned[index];
			img.onload = function() {
				// console.log(num);
				if (num >= len || !_this.Demoned[index]) {
					// console.log('预加载完成'+index);
					return;
				} else {
					// console.log('开始下一张');
					img.src = _this.img_array[num++].src;
				}
			}
		},
		downloadFile(url, fileName) {
			var x = new XMLHttpRequest();
			x.open("GET", url, true);
			x.responseType = 'blob';
			x.onload = function(e) {
				//会创建一个 DOMString，其中包含一个表示参数中给出的对象的URL。这个 URL 的生命周期和创建它的窗口中的 document 绑定。这个新的URL 对象表示指定的 File 对象或 Blob 对象。
				var url = window.URL.createObjectURL(x.response)
				var a = document.createElement('a');
				a.href = url
				a.download = fileName;
				a.click()
			}
			x.send();
		},
		close_category(e) {
			this.category_flag = false;
		},
		select_category(index) {
			this.img_flag = true;
			this.about_flag = false;
			this.isAbout = false;
			this.category_flag = false;
			this.find_flag = false;
			document.body.style.overflowY = 'auto';
			document.body.style.background = '#395668';
			this.current_page = 0;
			this.current_category = this.category_array[index].cid;
			this.loadType = 4;
			// axios.get(').then();
			axios.get("http://42.240.141.211/WallpaperController/queryByCategoryOrderByTime?cid=" + this
					.current_category + '&currentPage=' + this.current_page)
				.then(res => {
					this.current_page++;
					this.img_array.length = 0;
					var len = res.data.length;
					for (var i = 0; i < len; i++) {
						this.img_array.push(res.data[i]);
					}
					this.flush = !this.flush;
					this.flush = !this.flush;
					this.num = 0;
					this.reSetDemon(3);
					this.count = 11;
					setTimeout(() => {
						this.preLoadImg(3);
					}, 2000);
				});
		},
		search(e) {
			this.current_page = 0;
			var formData = new FormData(e.target);
			this.keyword = formData.get('keyword');
			this.toTop();
			formData.append("currentPage", 0);
			for (var i = 0; i < $(".select").length; i++) {
				$(".select")[i].classList.remove('isSelected');
			}
			$(".select")[0].classList.add('isSelected');
			axios({
				method: "post",
				url: "http://42.240.141.211/WallpaperController/searchByKeyWord",
				data: formData
			}).then((res) => {
				var len = res.data.length;
				this.img_array.length = 0;
				for (var i = 0; i < len; i++) {
					this.img_array.push(res.data[i]);
				}
				this.num = 0;
				this.flush = !this.flush;
				this.flush = !this.flush;
				this.current_page++;
				this.loadType = 5;
				this.reSetDemon(5);
				this.count = 11;
				setTimeout(() => {
					this.preLoadImg(5)
				}, 2000);
			});
		},
		reSetDemon(index) {
			for (var i = 0; i < 6; i++) {
				this.Demoned[i] = false;
			}
			this.Demoned[index] = true;
		},
		openMenu() {
			if (!this.open_flag) {
				$(".btns")[0].style.right = $(".rightMenu")[0].offsetWidth + 'px';
				$(".rightMenu")[0].style.right = '0vw';
			} else {
				$(".btns")[0].style.right = '0vw';
				$(".rightMenu")[0].style.right = '-' + $(".rightMenu")[0].offsetWidth + 'px';
			}
			this.open_flag = !this.open_flag;
		},
		getImage() {
			axios.get("http://42.240.141.211/WallpaperController/getRandomWallpaper?pid=" + this.img.pid)
				.then(res => {
					// console.log(res.data);
					this.img = res.data;
				});
		},
		favorite() {

			axios.get("http://42.240.141.211/WallpaperController/favorite?pid=" + this.img.pid)
				.then(res => {
					if (res.data == '不能重复操作') {
						alert('不能重复操作');
					} else if (res.data == 'error') {
						alert('系统错误');
					} else {
						alert('操作成功');
					}
				});
		},
		find_download() {
			var fileName = this.img.src.split('/').slice(-1)[0]
			var url = this.img.src;
			var timestamp = (new Date()).valueOf();
			var downLoader = plus.downloader.createDownload(url, {
				method: 'GET',
				filename: '_downloads/image/' + timestamp + '.png'
			}, function(download, status) {
				if (status == 200) {
					var fileName = download.filename;
					/**
					 * 保存至本地相册
					 */
					plus.gallery.save(fileName, function() {
						mui.toast("保存成功");
					}, function() {
						mui.toast("保存失败，请重试");
					});
				} else {
					mui.toast("下载失败，请重试");
				}
			});
			downLoader.start();
		},
	},
	mounted() {
		axios.get('http://42.240.141.211/util/visit');
		this.imgHeight = document.documentElement.clientHeight || document.body.clientHeight;
		window.addEventListener('mousewheel', this.handleScroll, false);
		window.addEventListener("DOMMouseScroll", this.handleScroll, false);
		var sUserAgent = navigator.userAgent;
		if (sUserAgent.indexOf('Android') > -1 || sUserAgent.indexOf('iPhone') > -1 || sUserAgent.indexOf(
				'iPad') > -1 || sUserAgent.indexOf('iPod') > -1 || sUserAgent.indexOf('Symbian') > -1) {
			location.href = "http://m.ultraer.icu";
			this.isPhone = true;
		}
		window.onresize = function() {
			document.getElementById('topMenu').style.width = '100vw';
		}
	},
	destroyed() {
		document.body.onmousedown = null;
		document.body.onkeydown = null;
		window.onresize = null;
	}
});
