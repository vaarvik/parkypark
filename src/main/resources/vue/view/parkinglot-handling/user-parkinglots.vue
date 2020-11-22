<template id="user-parkinglots">
    <div>
    </div>
</template>
<script>
    Vue.component("user-parkinglots", {
        template: "#user-parkinglots",
        data(){
            return {
                parkingslots: [],
                user: JSON.parse(this.getCookie("user").value),
            }
        }
    });
</script>
