<template>
	<section>
		<div class="label_priority">
			<label>Priority</label>
		</div>
		<div class="select_priority">
			<select v-model="value">
				<option v-for="option in options" :value="option.value">
			    	{{ option.text }}
			    </option>
			</select>
		</div>
	</section>
</template>

<script>
export default{
	props: {
		priorityList: {
			type: Array,
			require: true
		}
	},
	data () {
		return {
			options: this.priorityList,
			value: ''
		}
	},
	watch: {
		value (newValue) {
			this.$emit('priorityMethod', newValue)
		}
	}

}
</script>

<style lang="scss" scoped>
	div{
		display:flex;	
		justify-content: center;
	}
</style>