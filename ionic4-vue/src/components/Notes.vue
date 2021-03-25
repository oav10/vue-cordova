<template>
	<div class="notes">
        <div class="note" :class="[{full: !grid}, note.priority]" v-for="(note, index) in notes" :key="index">
          <div class="note-header" :class="{full: !grid}">
          	<div class="note-edit" style="cursor: pointer;" @click = "editTitle(index)">
	          	<input ref="ip" type="text" class="invisible" v-model="note.title">
	            <p ref="pi">{{note.title}}</p>
            </div>
            <p style="cursor: pointer;" @click="removeNote(index)">x</p>
          </div>        
          <div class="note-body" @click="saveTitle(index)">
            <p>{{note.descr}}</p>
            <span>{{note.date}}</span>
          </div>
        </div>
     </div>
</template>

<script>
export default{
	props: {
		notes: {
			type: Array,
			require: true
		},
		grid: {
			type: Boolean,
			require: true
		}
	},
	methods: {
		removeNote (index) {
			console.log(`Note id - ${index} removed`)
			this.$emit('remove', index)
		},
		editTitle (index) {
			this.$refs.ip[index].classList.remove('invisible')
			this.$refs.pi[index].classList.add('invisible')
		},
		saveTitle (index) {
			this.$refs.ip[index].classList.add('invisible')
			this.$refs.pi[index].classList.remove('invisible')
		}
	},
	data () {
		return {
			visibleEdit: false
		}
	},
}
</script>

<style lang='scss'>
.notes {
	display: flex;
	align-items: center;
	justify-content: space-between;
	flex-wrap: wrap;
	padding: 40px 0;
}
.invisible {
	display:none;
}
.note{
	width: 38%;
	padding: 18px 20px;
	margin-bottom: 20px;
	background-color: #fff;
	transition: all .25s cubic-bezier(.02,.01,.47,1);
	box-shadow: 0 30px 30px rgba(0, 0, 0, .02);
	&:hover{
		box-shadow: 0 30px 30px rgba(0, 0, 0, .04);
		transform: translate(0, -6px);
		transition-delay: 0s !important;
	}
	&.full {
		width: 100%;
		text-align:center;
	}
	&.not{
		background-color: #c9c;
	}
	&.yes{
		background-color: #9cc;
	}
	&.very{
		background-color: #cc9;
	}
}
.note-header{
	display: flex;
	justify-content: space-between;

	h1{
		font-size: 32px;
	}
	p {
		font-size: 22px;
		color: #402caf;
	}
	svg {
		margin-right: 12px;
		color: #999;
		&.active {
			color: #402caf;
		}
		&:last-child {
			margin-right: 0;
		}
	}
	&.full {
		justify-content: center;
		p{
			margin-right:16px;
			
		}
	}
}
.note-body{
	p {
		margin: 20px 0;
	}
	span {
		font-size: 14px;
		color: #999
	}
}

</style>
