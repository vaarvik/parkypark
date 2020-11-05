<template id="edit-parkinglot">
    <div>
        <h1>{{ title }}</h1>
        <p>Endre parkeringplassen ved å fylle inn feltene nedenfor.</p>
        <hr>
        <form @submit="onSubmit">
            <div class="field input">
                <label for="input-name">Tittel</label>
                <input type="text" name="name" id="input-name" placeholder="Tittel..." v-model="parkinglot.name">
            </div>
            <div class="field input">
                <label for="input-address">Adresse</label>
                <input type="text" name="address" id="input-address" placeholder="Tittel..." v-model="parkinglot.address">
            </div>
            <div class="field input">
                <label for="input-price">Pris</label>
                <input type="text" name="price" id="input-price" placeholder="Tittel..." v-model="parkinglot.price">
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
            parkinglot: {},
            title: '',
        }),
        created(){
            fetch(`/api/parkinglots/${this.$javalin.pathParams["parkinglotid"]}`)
                .then(res => res.json())
                .then(res => {
                  this.title = res.name;
                  this.parkinglot = res;
                })
                .catch(() => alert("Error while fetching parkinglot"));
        },
        methods:{
          onSubmit(event) {
            event.preventDefault();
            fetch('/api/parkinglots/:parkinglotid/edit', {
              method: 'POST',
              body: JSON.stringify(this.parkinglot),
            });
          },
          checkForm:function(e) {}
        }
    });
</script>
