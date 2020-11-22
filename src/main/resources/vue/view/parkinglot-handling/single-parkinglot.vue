<template id="single-parkinglot">
    <div class="site-wrapper">
        <header class="site-header">
            <a href="/">
                <h2 class="site-branding">ParkyPark</h2>
            </a>
            <div class="site-navigation">
                <a v-if="!isRenter()" href="/parkinglots/add">Legg til parkeringsplass</a>
                <a :href='isRenter() ? `/user/${user.id}/bookings` : `/user/${user.id}/parkinglots`'>
                    {{isRenter() ? "Dine bookinger" : "Dine parkeringsplasser"}}</a>
                <a href="/login" @click="onLogout">Logg ut</a>
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
                        <p class="entry-header__sub-fact">Tilgjengelig utleieperiode: {{ getDateFormat(new Date(parkinglot.checkin)) }} - {{ getDateFormat(new Date(parkinglot.checkout)) }}</p>
                        <p class="entry-info__price">Dagspris: {{ parkinglot.price }}</p>
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
                        <input class="field input" type="date" :min="checkInDate(parkinglot.checkin)" name="checkin" id="input-checkin" v-model="booking.checkIn">
                        <br>
                    </div>
                    <div>
                        <label for="input-checkout">Sluttdato på parkeringen</label>
                        <input class="field input" type="date" :min="checkInDate(booking.checkIn)" name="checkout" id="input-checkout"
                        v-model="booking.checkOut">
                        <br>
                    </div>
                    <a :href="`/parkinglots/${parkinglot.id}/book`">
                        <button class="btn">Book</button>
                    </a>
                </form>
                <div v-if="user.id === parkinglot.ownerId && !isRenter()" class="entry-buttons">
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
        data(){
            return {
                parkinglot: {},
                user: JSON.parse(this.getCookie("user").value),
                booking: {
                    userId: 2,
                },
                title: '',
                loremIpsum: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam bibendum velit et maximus pulvinar. Nullam efficitur nulla volutpat dolor gravida, et eleifend nunc lobortis. Curabitur dapibus laoreet erat. Phasellus a libero nec est semper auctor. Pellentesque non risus vehicula, tempor dolor quis, pretium magna. Integer cursus pretium libero. Nulla imperdiet felis sed felis varius aliquet. Proin nec sodales tortor. Phasellus at vehicula augue. In varius odio elit, sit amet interdum odio mollis a. Nullam pharetra tortor nisi, et molestie orci tempor sit amet. Cras velit metus, sodales quis risus non, lobortis bibendum nulla.",
            }
        },
        methods: {
            getCookie(cName) {
                const cookies = document.cookie.split(";"); //split cookies into array
                let finalCookie = null;

                cookies.forEach(function (cookie) {
                    cookie = cookie.replace(/ /g, ""); //remove space after string split
                    cookie = cookie.split("=");
                    if (cookie[0] === cName) {
                        finalCookie = { name: cookie[0], value: cookie[1] };
                    }
                });
                return finalCookie;
            },
            getInputDateFormat(date) {
                // let today = new Date();
                let dd = String(date.getDate()).padStart(2, '0');
                let mm = String(date.getMonth() + 1).padStart(2, '0'); //January is 0!
                let yyyy = date.getFullYear();

                return `${yyyy}-${mm}-${dd}`;
            },
            checkInDate(dateNum) {
                return new Date().getTime() > dateNum ?
                    this.getInputDateFormat(new Date()) :
                    this.getInputDateFormat(new Date(dateNum));
            },
            onSubmit(event) {
                event.preventDefault();
                fetch(`/api/parkinglots/${this.parkinglot.id}/book`, {
                    method: 'POST',
                    body: JSON.stringify(this.booking),
                })
                .then((res) => {
                    alert("Her ville en betaling blitt håndtert.")
                    alert("Din reise er blitt booket!")
                })
                .catch((err) => {
                    alert("Booking failet.")
                });
            },
            getDateFormat(date) {
                return `${date.getDate()}.${date.getMonth() + 1}.${date.getFullYear()}`
            },
            deleteCookie(name) {
                document.cookie = name +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
            },
            onLogout(e) {
                e.preventDefault();
                this.deleteCookie("user");
                window.location = "/login";
            },
            isRenter() {
                return this.user.type === "renter";
            }
        },
        created(){
            fetch(`/api/parkinglots/${this.$javalin.pathParams["parkinglotid"]}`)
                .then(res => res.json())
                .then(res => {
                  this.title = res.name;
                  this.parkinglot = res;
                  this.booking.parkinglotId = res.id;
                  this.booking.checkIn = this.checkInDate(res.checkin);
                })
                .catch(this.title = "This parkinglot does not exist");
        },
    });
</script>
