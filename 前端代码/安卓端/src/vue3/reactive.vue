<template>
	<div>
		{{state.myname}} -- {{state,myage}}
	</div>
	<button @click="changeState">click</button>
</template>

<script>
	import { reactive } from 'vue';
	export default {
		setup () {
			const state = reactive({
				myname: 'sazkuyo',
				myage: 20,
			})
			const changeState = () => {
				state.myname = 'zhao'
				state.myage = 21
			}
			return {
				state,
				changeState,
			}
		}
	}
</script>

<style>
</style>
