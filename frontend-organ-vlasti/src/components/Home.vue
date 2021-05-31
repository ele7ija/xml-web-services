<template>
  <div class="container-fluid">
    <div 
      v-for="(row, index) in options"
      :key="index"
      class="row my-4"
    >
        <div
          v-for="(col, index) in row"
          :key="index"
          class="col-6"
        >
          <div class="card">
            <div class="card-body">
              <h4 class="card-title">{{col.fullName}}</h4>
              <p class="card-text">{{col.description}}</p>
            </div>
            <div class="card-footer m-0 p-1">
                <a class="btn btn-link btn-sm mr-2" :style="{'float': 'right'}" @click="navigate(col.name)">Continue</a>
            </div>
          </div>
        </div>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex';
import router from './../vue-router';
export default {
  name: 'Home',
  data: () => {
    return {
      perRow: 2
    };
  },
  computed: {
    ...mapGetters({
      userOptions: 'userOptions/getOptions',
      role: 'userOptions/getRole'
    }),
    options() {
      const retval = [];
      for(const option of this.userOptions[this.role]) {
        if (option.hasChildren) {
          retval.push(...option.children);
        } else {
          retval.push(option);
        }
      }
      return retval.reduce((resultArray, item, index) => { 
        const chunkIndex = Math.floor(index / this.perRow)
      
        if(!resultArray[chunkIndex]) {
          resultArray[chunkIndex] = [] // start a new chunk
        }
      
        resultArray[chunkIndex].push(item)
      
        return resultArray
      }, []);
    }
  },
  methods: {
    navigate (name) {
      router.push({name});
    }

  }
}
</script>

<style>

</style>