<template id="single-parkinglot">
    <div class="site-wrapper">
        <header class="site-header">
            <a href="/">
                <h2 class="site-branding">ParkyPark</h2>
            </a>
            <div class="site-navigation">
                <a href="/parkinglots/add">Add parkinglot</a>
                <a href="">Your parkinglots</a>
                <a href="">Log out</a>
            </div>
        </header>
        <main class="site-content">
            <div class="entry">
                <header class="entry-header">
                    <h1 class="entry-header__heading">{{ title }}</h1>
                </header>
                <div class="entry-info">
                    <div class="entry-info__text">
                        <p class="entry-header__sub-fact"><b>Adresse:</b> {{ parkinglot.address }}</p>
                        <p class="entry-header__sub-fact">{{ parkinglot.description ? parkinglot.description : loremIpsum }}</p>
                        <p class="entry-header__sub-fact">Tilgjengelig utleieperiode: {{ parkinglot.checkin }}-{{ parkinglot.checkout }}</p>
                        <p class="entry-info__price">Pris: {{ parkinglot.price }}</p>
                    </div>
                    <img class="entry-info__image" :src="parkinglot.image ? parkinglot.image : `https://picsum.photos/seed/${parkinglot.id}/300/300`" alt="">
                </div>
                <br>
                <form @submit="onSubmit">
                    <div>
                        <label for="input-car">Velg bilen du skal bruke:</label>
                        <input class="field input" type="text" name="carLicenceNumber" id="input-car" placeholder="Registreringsnummer..." v-model="booking.carLicenceNumber">
                        <br>
                    </div>
                    <div>
                        <label for="input-checkin">Startdato på parkeringen</label>
                        <input class="field input" type="date" :min="getToday()" name="checkin" id="input-checkin" v-model="booking.checkIn">
                        <br>
                    </div>
                    <div>
                        <label for="input-checkout">Sluttdato på parkeringen</label>
                        <input class="field input" type="date" name="checkout" id="input-checkout"
                        v-model="booking.checkOut">
                        <br>
                    </div>
                    <a :href="`/parkinglots/${parkinglot.id}/book`">
                        <button class="btn">Book</button>
                    </a>
                </form>
                <div class="entry-buttons">
                    <a :href="`/parkinglots/${parkinglot.id}/edit`">
                        <button class="btn">Endre</button>
                    </a>
                </div>
            </div>
        </main>
        <footer class="site-footer">
            <div class="site-footer__content">
                <p>© ParkyPark 2020</p>
                <a href="mailto:contact@parkypark.no">Kontakt oss</a>
            </div>
        </footer>
    </div>
</template>
<script>
    Vue.component("single-parkinglot", {
        template: "#single-parkinglot",
        data: () => ({
            parkinglot: {},
            booking: {},
            title: '',
            loremIpsum: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam bibendum velit et maximus pulvinar. Nullam efficitur nulla volutpat dolor gravida, et eleifend nunc lobortis. Curabitur dapibus laoreet erat. Phasellus a libero nec est semper auctor. Pellentesque non risus vehicula, tempor dolor quis, pretium magna. Integer cursus pretium libero. Nulla imperdiet felis sed felis varius aliquet. Proin nec sodales tortor. Phasellus at vehicula augue. In varius odio elit, sit amet interdum odio mollis a. Nullam pharetra tortor nisi, et molestie orci tempor sit amet. Cras velit metus, sodales quis risus non, lobortis bibendum nulla.",
        }),
        methods: {
            getToday() {
                let today = new Date();
                let dd = String(today.getDate()).padStart(2, '0');
                let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
                let yyyy = today.getFullYear();

                return `${yyyy}-${mm}-${dd}`;
            },
            onSubmit(event) {
                event.preventDefault();
                fetch(`/api/parkinglots/${this.parkinglot.id}/book`, {
                    method: 'POST',
                    body: JSON.stringify(this.booking),
                })
                .then((res) => {
                    // alert("Din reise er blitt booket!")
                })
                .catch((err) => {
                    alert("Booking failet.")
                });
            },
        },
        created(){
            fetch(`/api/parkinglots/${this.$javalin.pathParams["parkinglotid"]}`)
                .then(res => res.json())
                .then(res => {
                  this.title = res.name;
                  this.parkinglot = res;
                  this.booking.parkinglotId = res.id;
                  this.booking.userId = res.ownerId;
                })
                .catch(this.title = "This parkinglot does not exist");
        },
    });
</script>
