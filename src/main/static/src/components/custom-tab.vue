<template>
    <div class="nav-tabs-custom">
        <ul class="nav nav-tabs" :class="tabsClass">
            <li v-for="(tab, index) in tabList" :data="tab" :key="index" :class="tab.class">
                <a :href="tab.id" data-toggle="tab">{{tab.title}}</a>
            </li>
            <slot name="nav"></slot>
        </ul>


        <!-- 下面的正文部分 -->
        <div class="tab-content">
            <slot name="body" class="tab-pane"></slot>
        </div>
    </div>

</template>

<script>
    export default {
        name: "v-custom",
        props:{
            tabClass:{
                type:String,
                default:''
            }
        },
        data() {
            return {
                tabList:[
                    {id:1,title:"html"},
                    {id:2,title:"js"}
                ]
            }
        },
        mounted(){
            for(const i in this.$slot.body){
                var vm = this.$slot.body[i]
                if(typeof vm !== 'object'){
                    continue
                }
                vm.elm.className += ' tab-pane'
                this.tabList.push({
                    id: '#' + vm.data.attrs.id,
                    class: vm.data.staticClass || '',
                    title: vm.data.attrs.title
                })
            }

        }
    }
</script>

<style scoped>

</style>