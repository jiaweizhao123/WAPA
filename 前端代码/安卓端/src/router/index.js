import { createRouter, createWebHistory,createWebHashHistory } from 'vue-router'

const routes = [
  {
	  path: '/homeView',
	  component: () => import(/*webpackChunkName: "homegroup"*/'../components/homeView.vue'),
	  children:[
		  {
			path: 'home',
			component: () => import(/*webpackChunkName: "homegroup"*/'../views/Home.vue'),
			name:'home'
		  },
		  {
			  path: 'about',
			  component: () => import(/*webpackChunkName: "homegroup"*/'../views/about.vue'),
				name:'about'
		  },
		  {
			path:'artcle',
			component: () => import(/*webpackChunkName: "homegroup"*/'../views/artcleList.vue'),
			name:'artcle'
		  },
		  {
		  	path: '',
		  	redirect: '/homeView/home'
		  }
	  ]
  },
  {
	  path: '/detail',
	  component: () => import(/*webpackChunkName: "detailGroup"*/'../components/wallpaperList.vue'),
	  children: [
		  {
				path: 'smshow',
				component: () => import(/*webpackChunkName: "detailGroup"*/'../components/sm_show.vue'),
				name:'smshow'
		  },
		  {
		  		path: 'lgshow',
		  		component: () => import(/*webpackChunkName: "detailGroup"*/'../components/Large_show.vue'),
				name:'lgshow'
		  }
	  ]
  },
  {
	  path: '/search',
	  component: () => import(/*webpackChunkName: "searchGroup"*/'../components/SearchPage.vue'),
	  name:'search'
  },
  {
  	  path: '/test',
  	  component: () => import(/*webpackChunkName: "searchGroup"*/'../components/test.vue'),
  },
  {
	path: '/login',
	component: () => import(/*webpackChunkName: "userGroup"*/'../components/login.vue'),
  },
  {
  	path: '/random',
  	component: () => import(/*webpackChunkName: "randomGroup"*/'../components/randomPic.vue'),
  },
  {
  	path: '/upload',
  	component: () => import(/*webpackChunkName: "uploadGroup"*/'../components/uploadImg.vue'),
  },
  {
  	path: '/circle',
  	component: () => import(/*webpackChunkName: "uploadGroup"*/'../components/PicCircle.vue'),
  },
  {
	path:"/:catchAll(.*)",
	redirect: "/homeView"
  },
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
  // history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
