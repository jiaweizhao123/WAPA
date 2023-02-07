const vm = new Vue({
    el:'#app',
    data:{
        btn_list: ['首页','用户管理','图片管理','拓展管理','公告通知','信息审核'],
        accountCount: '---',
        accountChange: '---',
        clickCount: 223,
        keyWord: '',
        categoryData: [
            { value: 1048, name: '动漫' },
            { value: 735, name: '风景' },
            { value: 580, name: '唯美' },
            { value: 484, name: '影视' },
            { value: 300, name: '萌宠' }
        ],
        tempImg:{
            pid: '',
            heat: '',
            pdate: '',
            recommended: '',
            cid: '',
            size: '',
            src: '',
            sm_src: '',
            tag: '',
			id:'',
			tags:''
        },
        categoryList:[],
        userList: [],
        categoryCount: '---',
        pictureCount: '---',
        recentPicture: 22,
        AllHeat: '---',
        MaxHeat: '---',
        wallpaperData:[],
        keyWordTop: [],
        pictureCurrentPage:0,
        visitorCount: '---',
        popShow:false,
        updateImg:{},
        category: '',
        selectCategory: 0,
        filterTag: '',
        announcement: [],
        current_announcement: {},
        announcement_flag: false,
        announcement_content: '',
        announcement_date: '',
		article_flag: false,
		userPicList: [],
		popShow2: false,
		isRecommend: true,
		temp_announcement: {
			title: '',
			content: ''
		}
    },
    methods:{
        announcement_show(index){
            this.current_announcement = this.announcement[index];
            this.announcement_flag = !this.announcement_flag;
        },
        popTip(){
            console.log('pop');
            this.$refs.tip.style.display = 'block';
            this.$refs.tip.innerHTML = '复制成功';
            this.$refs.tip.style.animation = 'slow_show 0.8s';
            setTimeout(()=>{
                this.$refs.tip.style.display = 'none';
            },800);
        },
        copyUrl(e){
            console.log(e.target.textContent);
            var url = e.target.textContent;
            let transfer = document.createElement('input');
            document.body.appendChild(transfer);
            transfer.value = url;  // 这里表示想要复制的内容
            transfer.focus();
            transfer.select();
            if (document.execCommand('copy')) {
                document.execCommand('copy');
            }
            transfer.blur();
            this.popTip();
            document.body.removeChild(transfer);
        },
        getCategory(){
            axios.get('http://42.240.141.211/CategoryController/queryALl')
                .then(res => {
                    this.category = res.data;
                });
        },
		sel_2(){
			this.isRecommend = false;
			this.$refs.sel_1.classList.remove('selected');
			this.$refs.sel_2.classList.add('selected');
		},
		sel_1(){
			this.isRecommend = true;
			this.$refs.sel_2.classList.remove('selected');
			this.$refs.sel_1.classList.add('selected');
		},
        updatePic(index){
            axios({
                method: 'post',
                url: 'http://42.240.141.211/WallpaperController/update',
                data: this.wallpaperData[index],
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(res => {
                if(res.data == 1){
                    alert('修改成功');
                }else{
                    alert('修改失败');
                }
            });
        },
		updatePic1(index){
			console.log(this.userPicList[index]);
		    axios({
		        method: 'post',
		        url: 'http://42.240.141.211/WallpaperController/updateUw',
		        data: this.userPicList[index],
		        headers: {
		            'Content-Type': 'application/json'
		        }
		    }).then(res => {
				console.log(res.data)
		        if(res.data == 1){
		            alert('修改成功');
		        }else{
		            alert('修改失败');
		        }
		    });
		},
		insert_announcement(){
			axios({
				method: 'post',
				url: 'http://42.240.141.211/AnnouncementController/insert',
				data: this.temp_announcement,
				headers: {
				    'Content-Type': 'application/json'
				}
			}).then( res => {
				if(res.data == 1){
					alert('发表成功');
					axios({
						method: 'post',
						url: 'http://42.240.141.211/AnnouncementController/queryAll',
						data: this.temp_announcement,
						headers: {
						    'Content-Type': 'application/json'
						}
					}).then( res => {
						this.announcement = res.data;
					});
				}else{
					alert('发表失败');
				}
				this.article_flag = false;
			});
		},
        deletePic(index){
            this.tempImg = this.wallpaperData[index];
            this.tempImg.recommended = parseInt(this.tempImg.recommended);
            axios({
                method: 'post',
                url: 'http://42.240.141.211/WallpaperController/delete',
                data: this.tempImg
            }).then(res => {
                if(res.data == 1){
                    this.wallpaperData.splice(index,1);
                    alert('删除成功');
                }else{
                    alert('删除失败');
                }
            });
        },
		deletePic1(index){
		    this.tempImg = this.userPicList[index];
		    this.tempImg.recommended = parseInt(this.tempImg.recommended);
		    axios({
		        method: 'post',
		        url: 'http://42.240.141.211/WallpaperController/deleteUw?id='+this.tempImg.id,
		    }).then(res => {
		        if(res.data == 1){
		            this.userPicList.splice(index,1);
		            alert('删除成功');
		        }else{
		            alert('删除失败');
		        }
		    });
		},
        initPic(){
            axios.get('http://42.240.141.211/WallpaperController/picData')
                .then(res => {
                    this.pictureCount = res.data[0];
                    this.MaxHeat = res.data[1];
                    this.AllHeat = res.data[2];
                });
        },
        initData(){
            this.pictureCurrentPage = 0;
        },
        getkeyWord(){
            axios.get('http://42.240.141.211/WallpaperController/getTopRecord')
            .then(res => {
               for(var i = 0 ; i < 3 ; i++){
                   this.keyWord = this.keyWord + ' ' + res.data[i].keyword;
               }
               console.log(this.keyWord);
            });
        },
        getVisitor(){
            axios.get('http://42.240.141.211/util/queryVisitorCount')
            .then(res => {
                this.visitorCount = res.data;
            });
        },
        getAccountCount(){
            axios.get('http://42.240.141.211/accountController/queryAccountCount')
                .then(res => {
                        this.accountCount = res.data;
                        if(localStorage.getItem('accountCount')==null){
                            this.accountChange = '+' + this.accountCount + '';
                        }else{
                            var num = parseInt(localStorage.getItem('accountCount'));
                            this.accountChange = '+'+(this.accountCount - num)+'';
                        }
                        localStorage.setItem('accountCount',this.accountCount);
                });
        },
        closePop(){
            this.$refs.msg.style.animation = 'left-to-right 0.3s';
            _this = this;
            setTimeout(() => {
                this.popShow = false;
                _this.sm_src = '';
            },300);
        },
        picMsg(index){
            for( var attr in this.wallpaperData[index]){
                this.tempImg[attr] = this.wallpaperData[index][attr];
            }
            switch(this.tempImg.cid){
                case 1: this.tempImg.cid = '动漫';
                        break;
                case 2: this.tempImg.cid = '风景摄影';
                        break;
                case 3: this.tempImg.cid = '影视';
                        break;
                case 4: this.tempImg.cid = '动物萌宠';
                        break;
                case 5: this.tempImg.cid = '游戏壁纸';
                        break;
                case 6: this.tempImg.cid = '唯美壁纸';
                        break;
                case 7: this.tempImg.cid = '杂类';
                        break;
                case 8: this.tempImg.cid = '插图';
                        break;
                case 9: this.tempImg.cid = '表情包';
                        break;
            }
            this.$refs.msg.style.marginRight  = '0px';
            this.$refs.msg.style.animation = 'right-to-left 0.3s';
            this.popShow = !this.popShow;
        },
		pic2Msg(index){
			this.tempImg.pid = '';
			this.tempImg.tag = '';
			this.tempImg.cid = '';
			for( var attr in this.userPicList[index]){
			    this.tempImg[attr] = this.userPicList[index][attr];
			}
			this.$refs.msg.style.marginRight  = '0px';
			this.$refs.msg.style.animation = 'right-to-left 0.3s';
			this.popShow = !this.popShow;
		},
        change(index){
            var btns = document.getElementsByClassName('selection');
            var len = btns.length;
            for(var i = 0 ; i < len ; i++){
                btns[i].classList.remove('selected');
            }
            btns[index].classList.add('selected');
            var lis = document.getElementsByClassName('containers');
            var len = lis.length;
            this.getAccountCount();
            this.getVisitor();
            for(var i = 0 ; i < len; i++){
                lis[i].style.display = 'none';
            }
            lis[index].style.display = 'block';
            if(index == 1){
                axios.get('http://42.240.141.211/accountController/queryAll')
                    .then(res => {
                        console.log(res.data);
                        this.userList = res.data;
                        // if(res.data.length < 16){

                        // }
                    });
            }else if(index == 2){
                this.initData();
                this.initPic();
                axios.get('http://42.240.141.211/CategoryController/queryALl')
                .then(res => {
                    this.categoryList = res.data;
                    this.categoryCount = this.categoryList.length;
                        
                });
                axios.get('http://42.240.141.211/WallpaperController/queryAllByLimit?currentPage='+this.pictureCurrentPage)
                .then(res => {
                    this.wallpaperData = res.data;
                    console.log(this.wallpaperData);   
                    this.pictureCurrentPage = this.pictureCurrentPage + 1;
                    
                });
            }else if(index == 3){
                axios.get('http://42.240.141.211/WallpaperController/getTopRecord')
                .then(res => {
                    this.keyWordTop = res.data;
                });
            }else if(index == 4){
				axios({
					method: 'post',
					url: 'http://42.240.141.211/AnnouncementController/queryAll',
					data: this.temp_announcement,
					headers: {
					    'Content-Type': 'application/json'
					}
				}).then( res => {
					this.announcement = res.data;
				});
			}
			else{
				this.initData();
				this.initPic();
				http://42.240.141.211/WallpaperController/insertUserWallpaper
				axios.get('http://42.240.141.211/WallpaperController/getAll?currentPage='+this.pictureCurrentPage)
				.then(res => {
				    this.userPicList = res.data;
				    console.log(this.userPicList);   
				    this.pictureCurrentPage = this.pictureCurrentPage + 1;
				    
				});
			}
        },
        morepic(){
            axios.get('http://42.240.141.211/WallpaperController/queryAllByLimit?currentPage='+this.pictureCurrentPage)
            .then(res => {
                var len = res.data.length;
                for(var i = 0; i < len; i++){
                    this.wallpaperData.push(res.data[i]);
                }
                this.pictureCurrentPage = this.pictureCurrentPage + 1;
                
            });
        },
		put_article(){
			this.article_flag = !this.article_flag;
		}
    },
    beforeCreate () {
    },
    mounted () {
        this.getCategory();
        this.getkeyWord();
        this.getVisitor();
        this.getAccountCount();
        var btns = document.getElementsByClassName('selection');
        btns[0].classList.add('selected');
        var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);
        var option;
        
        option = {
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '2%',
            left: 'center'
          },
          series: [
            {
              name: 'Access From',
              type: 'pie',
              radius: ['40%', '70%'],
              avoidLabelOverlap: false,
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: '40',
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: this.categoryData
            }
          ],
        };
        
        option && myChart.setOption(option);

    }
})