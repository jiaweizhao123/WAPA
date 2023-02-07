<template>
	<div class="leftMenu">
		<div class="topShow">
			xxxxxx,xxxx,xxxxx
		</div>
		<div class="bottom_con">
			<div v-if="flag" class="user">
				<van-image
				  class="tx"
				  round
				  width="6vh"
				  height="6vh"
				  src="https://img.yzcdn.cn/vant/cat.jpeg"
				/>
				<div class="tologin">
					{{username}}
				</div>
				<div class="none">
					<van-icon name="diamond-o" size="50" v-if="isAdmin"></van-icon>
					<van-icon name="smile-o" size="50" v-else></van-icon>
				</div>
			</div>
			<div v-else class="noUser">
				<van-image
				  class="tx"
				  round
				  width="6vh"
				  height="6vh"
				  src="https://img.yzcdn.cn/vant/cat.jpeg"
				/>
				<div class="tologin" @click="login">
					暂未登录，点击登录/注册
				</div>
			</div>
			<div class="myMsg">
				<div class="msgItem">
					<van-icon name="friends-o" size="8vw"></van-icon>
					<br/><span>我的好友</span>
				</div>
				<div class="msgItem">
					<van-icon name="todo-list-o" size="8vw"></van-icon>
					<br/><span>我的浏览</span>
				</div>
				<div class="msgItem">
					<van-icon name="thumb-circle-o" size="8vw"></van-icon>
					<br/><span>获赞数</span>
				</div>
				<div class="msgItem">
					<van-icon name="bar-chart-o" size="8vw"></van-icon>
					<br/><span>我的排名</span>
				</div>
			</div>
			
			<div class="list_item">
				<van-icon name="photo-o" size="6vw"></van-icon>
				手机壁纸
			</div>
			<div class="list_item">
				<van-icon name="desktop-o" size="6vw"></van-icon>
				电脑壁纸
			</div>
			<div class="list_item">
				<van-icon name="apps-o" size="6vw"></van-icon>
				分类页
			</div>
			<div class="list_item">
				<van-icon name="more-o" size="6vw"></van-icon>
				功能拓展
			</div>
			<div class="list_item">
				<van-icon name="manager-o" size="6vw"></van-icon>
				关于我们
			</div>
			<div class="bottom_set">
				<div class="btm_item">
					<van-icon name="setting-o" size="8vw"></van-icon>
					<br/><span>设置</span>
				</div>
				<div class="btm_item">
					<van-icon name="flower-o" size="8vw"></van-icon>
					<br/><span>好评</span>
				</div>
				<div class="btm_item">
					<van-icon name="envelop-o" size="8vw"></van-icon>
					<br/><span>联系我们</span>
				</div>
				<div class="btm_item">
					<van-icon name="flag-o" size="8vw"></van-icon>
					<br/><span>加入我们</span>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default{
		data(){
			return{
				flag: false,
				user: {
					username: '',
					img: '',
					isAdmin: false
				}
			}
		},
		methods:{
			login(){
				this.$router.push('/login');
			}
		},
		mounted() {
			if(localStorage.getItem('user') == null){
				this.flag = false;
			}else{
				this.user = localStorage.getItem('user');
				this.flag = true;
				this.username = localStorage.getItem('user');
				if(this.username === 'sazkuyo'){
					this.isAdmin = true;
				}
			}
		}
	}
</script>

<style scoped="scoped" lang="scss">
	.leftMenu{
		width: 100%;
		height: 100%;
		overflow-x: hidden;
		.topShow{
			width: 100%;
			height: 20vh;
			background-color: red;
			line-height: 20vh;
			background: url('topMenu.png') no-repeat;
			background-size: cover;
			color: #fff;
			text-align: center;
			font-size: 1.2rem;
		}
		.bottom_con{
			width: 100%;
			height: 80vh;
			border-radius:10vw;
			.user{
				width: 100%;
				height: 10vh;
				display: flex;
				flex-direction: row;
				width: 100%;
				height: 8vh;
				padding:0vh 3vw;
				border-bottom: 1px solid #000;
				margin-bottom: 2vh;
				.tx{
					margin-top: 1vh;
					flex: 2;
				}
				.tologin{
					flex: 4;
					line-height: 8vh;
					font-size: 1.3rem;
					text-align: left;
					box-sizing: border-box;
					text-align: center;
				}
				.none{
					flex: 4;
					text-align: center;
					color: #fff;
					font-size: 1.1rem;
					line-height: 11vh;
					 background-image: linear-gradient(to bottom right, black, white);
				}
				
			}
			.noUser{
				display: flex;
				flex-direction: row;
				width: 100%;
				height: 8vh;
				padding:0vh 2vw;
				.tx{
					margin-top: 1vh;
					flex: 1;
				}
				.tologin{
					flex: 4.5;
					line-height: 8vh;
					font-size: 0.9rem;
					text-align: center;
				}
			}
				
			.list_item{
				width:100%;
				height:6.5vh;
				line-height:6.5vh;
				padding:0vh 3vw;
			}
			.bottom_set{
				position:fixed;
				display: flex;
				flex-direction: row;
				width: 100%;
				height: 8vh;
				bottom: 0;
				border-top: 0.5px solid #000;
				.btm_item{
					flex: 1;
					text-align: center;
					margin-top:1vh;
				}
				span{
					text-align: center;
					font-size: 0.7rem;
				}
			}
			.myMsg{
				width: 100%;
				height: 10vh;
				border-bottom: 1px solid #000;
				display: flex;
				flex-direction: row;
				.msgItem{
					height: 8vh;
					margin:1vh 0vw;
					flex: 1;
					text-align: center;
				}
				span{
					font-size: 0.8rem;
				}
			}
		}
	}
</style>
