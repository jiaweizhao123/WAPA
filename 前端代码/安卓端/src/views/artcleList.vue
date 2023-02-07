<template>
	<div class="artcle">
		<van-sidebar class="SideBar" v-model="active" @change="onChange">
		  <van-sidebar-item title="实用工具" />
		  <van-sidebar-item title="用户功能" />
		  <van-sidebar-item title="图片处理" />
		</van-sidebar>
		<div v-if="flag == 1" class="rightMenu">
			<van-grid :gutter="7" column-num="3">
			  <van-grid-item to="/random" class="gridItem"  icon="photo-o" text="淘图" />
			  <van-grid-item class="gridItem"  icon="user-o" text="自定义头像" />
			  <van-grid-item class="gridItem"  icon="edit" text="图片编辑" />
			  <van-grid-item class="gridItem"  icon="add-o" text="添加功能" />
			</van-grid>
		</div>
		<div v-else-if="flag == 2" class="rightMenu">
			<van-grid :gutter="7" column-num="3">
			  <van-grid-item class="gridItem" to="/upload"  icon="good-job-o" text="好图推荐" />
			  <van-grid-item class="gridItem" to="/circle"  icon="smile-o" text="推荐圈" />
			  <van-grid-item class="gridItem"  icon="user-circle-o" text="信息修改" />
			  <van-grid-item class="gridItem"  icon="add-o" text="添加功能" />
			</van-grid>
		</div>
		<div v-else class="rightMenu">
			<van-grid :gutter="7" column-num="3">
			  <van-grid-item class="gridItem"  icon="add-o" text="添加功能" />
			</van-grid>
		</div>
	</div>
</template>

<script>
	import { ref,reactive } from 'vue';
	
	export default {
	  setup() {
	    const active = ref(0);
	    return {
	      active
	    };
	  },
	  data(){
		  return{
			  flag:1,
		  }
	  },
	  methods:{
		  onChange(index){
			  this.flag = index+1;
		  }
	  }
	};
</script>

<style scoped="scoped" lang="scss">
	.artcle{
		display: flex;
		flex-direction: row;
	}
	.SideBar{
		height: 88vh;
		background-color: #f0f0f0;
		--van-sidebar-width:22vw;
	}
	.rightMenu{
		width: 88vw;
		height: 88vh;
		padding: 1vh 0vw;
		background-color: #e1e1e1;
		position: relative;
		right: 0;
	}
	.gridItem{
		
	}
</style>
