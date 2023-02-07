import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import 'vant/lib/index.css';
import van from './components/van-image-preview.vue';
import mui from  '../public/mui/js/mui.js';
import { Tabbar, TabbarItem,NavBar,Icon,Popup,Skeleton,Tab,Tabs,Image as VanImage,Lazyload,List,ImagePreview,Cell, CellGroup,PullRefresh,Toast,Search,Sidebar, SidebarItem,Grid, GridItem,Collapse, CollapseItem,Uploader, Swipe, SwipeItem  } from 'vant';

createApp(App)
	.use(store)
	.use(mui)
	.use(Uploader)
	.use(Collapse)
	.use(CollapseItem)
	.use(Swipe)
	.use(SwipeItem)
	.use(Grid)
	.use(GridItem)
	.use(Sidebar)
	.use(SidebarItem)
	.use(router)
	.use(Tabbar)
	.use(TabbarItem)
	.use(Toast)
	.use(Search)
	.use(NavBar)
	.use(PullRefresh)
	.use(Icon)
	.use(Cell)
	.use(CellGroup)
	.use(Popup)
	.use(Skeleton)
	.use(Tab)
	.use(Tabs)
	.use(VanImage)
	.use(Lazyload)
	.use(List)
	.use(van)
	.use(ImagePreview)
	.mount('#app')
