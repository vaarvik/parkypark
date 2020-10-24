<template id="edit-parkinglot">
    <div>
        <h1>{{parkinglot.name}}</h1>
        <hr>
        <h4>{{parkinglot.address}}</h4>
        <form :action="`/api/parkinglots/${parkinglot.id}/edit`" method="POST">
            <input type="text" placeholder="name">
            <button type="submit">Sumbit</button>
        </form>
    </div>
</template>
<script>
    Vue.component("edit-parkinglot", {
        template: "#edit-parkinglot",
        data(){
            return {
                parkinglot: {}
            }
        },
        created(){
            fetch(`/api/parkinglots/${this.$javalin.pathParams["parkinglotid"]}`)
                .then(res => res.json())
                .then(res => this.parkinglot = res)
        }
    });
</script>
