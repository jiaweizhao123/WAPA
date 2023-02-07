<template>
	<div class="con">
		<div class="search">
			<van-icon class="left" @click="onClickLeft" name="arrow-left"></van-icon>
			<van-search class="input" v-model="value"  placeholder="请输入搜索关键词" background="#000000"
				@search="onSearch">
			</van-search>
		</div>
		<div class="top">
			<div class="title">
				<van-icon name="chart-trending-o" class="title_icon" size="7vw"></van-icon>
				<span>热门搜索</span>
			</div>
			<div class="top_list">
				<div class="top_item" v-for="(item,index) in TopList" @click="topSearch(item.keyword)">{{item.keyword}}</div>
			</div>
			
		</div>
		<div class="record">
			<div class="title">
				<van-icon name="clock-o" class="title_icon" size="7vw"></van-icon>
				<span>搜索记录</span>
			</div>
			<div class="record_li" style="text-align: center;padding: 2vh 0;" v-if="recordList.length == 0">暂无搜索记录</div>
			<div v-else>
				<div class="record_li" v-for="(item,index) in recordList" :key="index">
					<div class="record_item">
					<div class="word"  @click="search(index)">{{item}}</div>
					<van-icon class="del" name="cross" @click="del(index)"></van-icon>
					</div>
				</div>
				<div class="clear" @click="clearRecord">清空记录</div>
			</div>
		</div>
	</div>
</template>

<script>
	import {
		Search
	} from 'vant';
	import axios from 'axios';
	import { useRouter } from 'vue-router';
	export default {
		data(){
			return{
				value:'',
				recordList: [],
				TopList:[]
			}
		},
		setup() {
			const onClickLeft = () => history.back();
			return {
				onClickLeft,
			};
		},
		methods:{
			onSearch(){
				this.recordList.push(this.value.toString());
				localStorage.setItem('search_record',JSON.stringify(this.recordList));
				this.$router.push({path:'/detail', query:{category:this.value,flag:1}});
			},
			clearRecord(){
				localStorage.setItem('search_record',JSON.stringify([]));
				this.recordList = []
			},
			search(index){
				this.recordList.push(this.recordList[index].toString());
				localStorage.setItem('search_record',JSON.stringify(this.recordList));
				this.$router.push({path:'/detail', query:{category:this.recordList[index],flag:1}});
			},
			del(index){
				this.recordList.splice(index,1);
				localStorage.setItem('search_record',JSON.stringify(this.recordList));
			},
			topSearch(keyword){
				this.recordList.push(keyword);
				localStorage.setItem('search_record',JSON.stringify(this.recordList));
				this.$router.push({path:'/detail', query:{category:keyword,flag:1}});
			}
		},
		mounted() {
			axios.get('http://42.240.141.211/WallpaperController/getTopRecord')
				.then(res => {
					this.TopList = res.data;
				});
			this.$store.state.toucherShow = false;
			//网页端读取缓存
			if(localStorage.getItem('search_record')!=null)
				this.recordList = JSON.parse(localStorage.getItem('search_record'));
		}
	}
</script>

<style scoped="scoped" lang="scss">
	.con{
		position: absolute;
		width: 100%;
		height: 100%;
		background-color: #e3e3e3;
	}

	.search {
		width: 100%;
		height: 6.5vh;
		line-height: 6.5vh;
		display: flex;
		flex-direction: row;
		background-color: #000000;
	}

	.left {
		flex: 1;
		text-align: center;
		line-height: 6.5vh;
		display: inline-block;
		color: #fff;
	}

	.input {
		flex: 10;
		--van-search-action-text-color: #fff;
	}
	.top{
		width: 96%;
		height: auto;
		background-color: #fff;
		margin: 0vh 0%;
		padding: 0 2vw;
		padding-bottom: 2vh;
		margin-bottom: 0.5vh;
		.title {
			width: 100%;
			height: 5vh;
			display: flex;
			flex-direction: row;
		
			.title_icon {
				line-height: 5vh;
			}
		
			span {
				margin-left: 2vw;
				font-size: 0.9rem;
				font-weight: bold;
				line-height: 5vh;
			}
		}
		.top_list{
			.top_item{
				display: inline-block;
				width: 17vw;
				height: 30px;
				line-height: 30px;
				background-color: #5a7eff;
				color: #fff;
				text-align: center;
				margin:0.5vh 1vw;
			}
		}
	}
	.record {
		width: 96%;
		height: auto;
		background-color: #fff;
		margin: 0vh 0%;
		padding: 0 2vw;
		margin-bottom: 2vh;

		.clear{
			width: 100%;
			height: 4vh;
			line-height: 4vh;
			text-align: center;
		}
		
		.title {
			width: 100%;
			height: 5vh;
			display: flex;
			flex-direction: row;

			.title_icon {
				line-height: 5vh;
			}

			span {
				margin-left: 2vw;
				font-size: 0.9rem;
				font-weight: bold;
				line-height: 5vh;
			}
		}

		.record_li {
			width: 96%;
			height: 3.5vh;
			line-height: 3.5vh;
			border-bottom: 1px solid lightgray;
			background-color: #fff;
			font-size: 0.9rem;
			padding: 0 2vw;
			margin: 0.5vh 0;
		}
	}
	.record_item{
		display: flex;
		flex-direction: row;
		.word{
			flex: 20;
		}
		.del{
			text-align: center;
			line-height: 3.5vh;
			flex: 2;
		}
	}
</style>
