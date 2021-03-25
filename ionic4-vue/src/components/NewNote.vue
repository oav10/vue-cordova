<template>
 <section class="new-note">
 	<div class="label_title">
 		<label>Title</label>
 	</div>

 	<div class="input_title">
    	<input type="text" v-model="note.title"> 
    	<!-- <ion-input type="text" placeholder="Enter Title" v-model="note.title"></ion-input> -->
    </div>
    
    <div class="label_description">
    	<label>Description</label>
    </div>
    
    <div class="textare_description">		
    	<textarea v-model="note.descr"></textarea>
    </div>
    
    <priority :priorityList="priorityList" @priorityMethod="priorityMethod" />
    
    <ion-button @click="addNote" expand="full">New note</ion-button>
    	<!-- <button class="btn btnPrimary" @click="addNote"> New note</button> -->
    
  </section>
</template>

<script>
import priority from "@/components/PriorityNote.vue"
export default {
	components: {
		priority
	},
	props: {
		note: {
			type: Object,
			required: true
			//default: 'default message'
		},
	},
	data () {
		return {
	      priorityList: [
	        { text: 'Неважно', value: 'not' },
	        { text: 'Важно', value: 'yes' },
	        { text: 'Очень важно!', value: 'very' }
	      ]
	  	}
	},
	methods:{
        addNote () {
        	this.$emit('add-note', this.note) //Отправлять данные родительскому компоненту
        },
        priorityMethod (newPriotity) {
        	this.note.priority = newPriotity;
        }
      }
}
</script>

<style lang="scss" scoped>
	div{
		display:flex;	
		justify-content: center;
	}
	.new-note{
		text-align: center;
	}
	input {
	  margin-bottom: 0px;
	  font-size: 26px;
	  
	}
	textarea {
	  font-size: 26px;
	}
</style>