<template id="index">
    <ul>
        <li v-for="parkinglot in parkingslots" :key="parkinglot.id">
            {{ parkinglot.name }}
        </li>
    </ul>
</template>
<script>
    Vue.component("index", {
        template: "#index",
        data(){
            return {
                parkingslots: {}
            }
        },
        created(){
            fetch("api/parkinglots")
                .then(res => res.json())
                .then((res) => {
                    this.parkingslots = res;
                })
        }
    });
</script>
