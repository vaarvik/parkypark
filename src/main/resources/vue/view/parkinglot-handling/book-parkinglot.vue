<template id="book-parkinglot">
    <div>
        <h1>{{parkinglot.name}}</h1>
        <p>Book deg på denne parkeringsplassen.</p>
        <hr>
        <form @submit="onSubmit">
            <div class="field input">
                <label for="input-car">Velg bilen du skal bruke:</label>
                <input type="text" name="car" id="input-car" placeholder="Registreringsnummer...">
            </div>
            <div class="field input">
                <label for="input-checkin">Startdato på parkeringen</label>
                <input type="date" name="checkin" id="input-checkin">
            </div>
            <div class="field input">
                <label for="input-checkout">Sluttdato på parkeringen</label>
                <input type="date" name="checkout" id="input-checkout">
            </div>
            <input type="hidden" name="parkinglotId" :value="parkinglot.id">
            <input type="hidden" name="userId" :value="1">
            <input type="submit" value="Submit">
        </form>
    </div>
</template>
<script>
    Vue.component("book-parkinglot", {
        template: "#book-parkinglot",
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
            onSubmit(event) {
                event.preventDefault();
                fetch(`/api/parkinglots/${this.parkinglot.id}/book`, {
                    method: 'POST',
                    body: JSON.stringify(this.parkinglot),
                })
                .then(() => {
                    alert("Din reise er blitt booket!")
                })
                .catch((err) => {
                    alert("Booking failed.")
                });
            },
            checkForm:function(e) {

            }
        }
    });
</script>
