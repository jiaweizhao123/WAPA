<template>
  <div id="nav">
		<Toucher v-show="watchToucher"></Toucher>
		<router-view></router-view>
		
  </div>
</template>

<script>
	import { Toast } from 'vant';
	import Toucher from './components/toucher.vue'
	import axios from 'axios'
	export default {
		data(){
			return {
				toucherSHow:true,
				unaccept:['search','about']
			}
		},
		components: {
			Toucher
		},
		computed:{
			watchToucher(){
				return  this.$store.state.toucherShow;
			}
		},
		mounted() {
			document.addEventListener('plusready', function() {
			            var webview = plus.webview.currentWebview();
			            plus.key.addEventListener('backbutton', function() {
			                webview.canBack(function(e) {
			                    if(e.canBack) {
			                        webview.back();
			                    } else {
			                        //webview.close(); //hide,quit
			                        //plus.runtime.quit();
			                        //首页返回键处理
			                        //处理逻辑：1秒内，连续两次按返回键，则退出应用；
			                        var first = null;
			                        plus.key.addEventListener('backbutton', function() {
			                            //首次按键，提示‘再按一次退出应用’
			                            if (!first) {
			                                first = new Date().getTime();
			                                Toast({message:'已经是首页了,再按一次退出应用'});
			                                setTimeout(function() {
			                                    first = null;
			                                }, 1000);
			                            } else {
			                                if (new Date().getTime() - first < 1500) {
			                                    plus.runtime.quit();
			                                }
			                            }
			                        }, false);
			                    }
			                })
			            });
			        });

		},
		created() {
			axios({
				method: 'post',
				url: 'http://42.240.141.211/AnnouncementController/getLater',
				headers: {
				    'Content-Type': 'application/json'
				}
			}).then( res => {
				alert(res.data.content);
			});
		}
	}
</script>

<style lang="scss">
</style>
