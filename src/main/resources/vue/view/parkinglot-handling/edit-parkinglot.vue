<template id="edit-parkinglot">
    <div>
        <h1>{{parkinglot.name}}</h1>
        <p>Endre parkeringplassen ved å fylle inn feltene nedenfor.</p>
        <hr>
        <form @submit="checkForm" :action="`/api/parkinglots/${parkinglot.id}/edit`" method="POST">
            <div class="field input">
                <label for="input-name">Tittel</label>
                <input type="text" name="name" id="input-name" placeholder="Tittel..." :value="parkinglot.name">
            </div>
            <div class="field input">
                <label for="input-address">Adresse</label>
                <input type="text" name="address" id="input-address" placeholder="Tittel..." :value="parkinglot.address">
            </div>
            <div class="field input">
                <label for="input-price">Pris</label>
                <input type="text" name="price" id="input-price" placeholder="Tittel..." :value="parkinglot.price">
            </div>
            <input type="hidden" name="id" :value="parkinglot.id">
            <input type="hidden" name="userid" :value="parkinglot.userid">
            <input type="submit" value="Submit">
        </form>
    </div>
</template>
<script>
    Vue.component("edit-parkinglot", {
        template: "#edit-parkinglot",
        data: () => ({
            parkinglot: {}
        }),
        created(){
            fetch(`/api/parkinglots/${this.$javalin.pathParams["parkinglotid"]}`)
                .then(res => res.json())
                .then(res => this.parkinglot = res)
                .catch(() => alert("Error while fetching parkinglot"));
        },
        methods:{
            checkForm:function(e) {

            }
        }
    });
</script>
