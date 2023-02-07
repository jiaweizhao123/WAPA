<template>
	<div class="login_con" ref="bk">
		<div class="title">用户登录表</div>
		<div class="main_form">
			<input class="msg" v-model="user.email" type="text" name='email' placeholder="请输入邮箱"/>
			<input class="msg" v-model="user.pwd" type="password" name='pwd' placeholder="请输入密码"/>
			<input class="btn"  type="submit" value="登录" @click="login"/>
		</div>
		<div class="toSign" @click="toRegister">暂无账号？点击注册</div>
	</div>
</template>

<script>
	import axios from 'axios';
	export default{
		data(){
			return{
				bk: '',
				user: {
					email: '',
					pwd: ''
				}
			}
		},
		mounted() {
			this.$store.state.toucherShow = false;
			axios.get('http://42.240.141.211/WallpaperController/getRandomPhoneWallpaper?pid=999999')
				.then(res => {
					this.bk = res.data;
					console.log("url('"+this.bk.src+"',no-repeat)");
					this.$refs.bk.style.background = "url('"+this.bk.src+"') no-repeat";
					this.$refs.bk.style.backgroundSize = 'cover';
				});
				
			
		},
		methods:{
			toRegister(){
				location.href = 'http://www.ultraer.icu/login.html';
			},
			login(){
				if(this.user.email === '' || this.user.pwd === ''){
						alert('各项不能为空');
						return;
				}
				axios.post('http://42.240.141.211/accountController/loginByEmailAndPwd',this.user)
					.then(res => {
						console.log(res.data == '');
						if(res.data != ''){
							console.log(res.data.uname);
							localStorage.setItem('user',res.data.uname);
							localStorage.setItem('userId',res.data.uid);
							this.$router.push('/home');
						}else{
							alert('登录信息错误');
						}
					});
				// this.user = JSON.stringify();
			}
		}
	}
</script>

<style scoped="scoped" lang="scss">
	.body{
		transition: all 0.4s;
	}
	.login_con{
		width: 100vw;
		height: 100vh;
		.title{
			color: #fff;
			font-size: 1.3rem;
			text-align: center;
			position: absolute;
			width: 60vw;
			height: 8vh;
			line-height: 8vh;
			border-radius: 20px;
			top: 21vh;
			left:20vw;
			background-color: rgba(35, 35, 35, 0.4);
		}
		.toSign{
			position: absolute;
			bottom: 23vh;
			text-align: center;
			font-size: 1.2rem;
			width: 80%;
			color: #fff;
			height: 5vh;
			left: 10vw;
		}
		.main_form{
			position: relative;
			width: 95vw;
			height: 40vh;
			background-color: rgba(255,255,255,0.75);
			margin: 0 auto;
			padding-top: 7vh;
			box-sizing: border-box;
			top:30vh;
			.msg{
				width: 80%;
				height: 12%;
				outline: none;
				margin:5% 10%;
			}
			.btn{
				width: 40%;
				height: 14%;
				text-align: center;
				margin:2% 30%;
				background-color: #2d80ce;
				color: #fff;
				outline: none;
				border: none;
			}
		}
	}
</style>
