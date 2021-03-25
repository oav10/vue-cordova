<template>
  <div class="wrapper">
    <div class="wrapper-content">
      <section>
        <div class="container">

          <!-- message -->
          <message v-if="message" :message="message" />

          <!-- new note -->
          <newNote 
            :note="note"
            @add-note="addNote" />

          
          <div class="note-header" style="margin: 36px">

            <!-- title  -->
            <!-- <h1>{{title}}</h1> -->

            <!-- search -->
            <search 
              :value="search"
              placeholder="Find your note" 
              @search="search = $event" />

            <!-- icons controls -->
            <div class="icons">
              <svg :class="{active: grid}" @click="grid = false" style="cursor: pointer;" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" ><rect x="3" y="3" width="7" height="7"></rect><rect x="14" y="3" width="7" height="7"></rect><rect x="14" y="14" width="7" height="7"></rect><rect x="3" y="14" width="7" height="7"></rect></svg>
              <svg :class="{active: !grid}" @click="grid = true"  style="cursor: pointer;" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="8" y1="6" x2="21" y2="6"></line><line x1="8" y1="12" x2="21" y2="12"></line><line x1="8" y1="18" x2="21" y2="18"></line><line x1="3" y1="6" x2="3" y2="6"></line><line x1="3" y1="12" x2="3" y2="12"></line><line x1="3" y1="18" x2="3" y2="18"></line></svg>
            </div>
          </div>

          <!-- note list -->
          <notes :notes="notesFilter" :grid="grid" @remove="removeNote"/>

        </div>
      </section>
    </div>
  </div>
</template>

<script>
import  message from "@/components/Message.vue"
import  newNote from "@/components/NewNote.vue"
import  notes from "@/components/Notes.vue"
import  search from "@/components/Search.vue"

export default {
  name: 'App',
  components: {
    message,
    newNote,
    notes,
    search
  },
  created () {
    document.addEventListener('backbutton', this.onBackKeyDown, false)
    document.addEventListener('deviceready', function () {
      // cordova.plugins.backgroundMode is now available
    }, false)
  },
   callNative () {
     
    },
  data () {
    return {
      title: 'Notes App',
      search: '',
      grid: true,
      note: {
        title: '',
        descr: ''
      },
      notes: [
        {
          title: "1st Note",
          descr: 'Descr for first note',
          date: new Date(Date.now()).toLocaleString()
        },
        {
          title: "2nd Note",
          descr: 'Descr for Second note',
          date: new Date(Date.now()).toLocaleString()
        },
        {
          title: "3rd Note",
          descr: 'Descr for Third note',
          date: new Date(Date.now()).toLocaleString()
        },
      ],
      message: null
    }
  },
  computed: {
    notesFilter () {
      let array = this.notes
      let search = this.search
      if(!search) return array

      search = search.trim().toLowerCase()
      array = array.filter(function(item){
        if(item.title.toLowerCase().indexOf(search) !== -1)
          return item
      })
      return array;
    }
  },  
  methods: {
    addNote (note) {
      console.log(note)
      let {title, descr, priority} = note;
      if(!title) {
        this.message = "title can't be blank"
        return false
      }
      this.notes.push({
        title,
        descr,
        priority,
        date: new Date(Date.now()).toLocaleString()
      })
      this.message = null
      this.note.title = ''
      this.note.descr = ''
      setInterval(() =>{this.text2 = " " + this.text++ }, 1000)
      
      cordova.exec(() => {}, () => {}, 'TestPlugin', 'showToast', 'Запись сделана')
    },
    removeNote (index) {
      this.notes.splice(index, 1);
    }
  }
}
</script>

<style>
</style>

