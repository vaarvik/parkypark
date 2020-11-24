<template id="add-parkinglot">
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
                    <h1 class="entry-header__heading">Opprett parkeringsplass</h1>
                    <p>Hei {{user.name}}! Bruk skjemaet nedenfor for å legge til din parkeringsplass.</p>
                </header>
                <div class="entry-info">
                    <div class="entry-info__text">
                        <form @submit="onSubmit" :action="`/api/parkinglots/add`" method="post">
                            <label for="name">Navn</label>
                            <input class="field input" type="text" name="name" id="name" v-model="parkinglot.name">
                            <br>

                            <label for="address">Adresse</label>
                            <input class="field input" type="text" name="address" id="address" v-model="parkinglot.address">
                            <br>

                            <label for="price">Pris</label>
                            <input class="field input" type="number" step="1" name="price" id="price" v-model="parkinglot.price" min="0">
                            <br>

                            <label for="image">Bilde</label>
                            <input placeholder="Bildeurl..." class="field input" type="url" name="image" id="image" v-model="parkinglot.image">
                            <br>

                            <label for="checkin">Check in</label>
                            <input class="field input" type="date" @change="(e) => getDateInput(e, 'checkin')" :min="getToday()" name="checkin" id="checkin">
                            <br>

                            <label for="checkout">Check out</label>
                            <input class="field input" type="date" @change="(e) => getDateInput(e, 'checkout')" :min="parkinglot.checkin ? getInputDateFormat(new Date(parkinglot.checkin)) : getToday()" name="checkout" id="checkout">
                            <br>

                            <label for="description">Beskrivelse</label>
                            <textarea class="field" name="description" id="description" cols="30" rows="2" v-model="parkinglot.description"></textarea>
                            <br>

                            <button class="btn">Opprett parkeringsplass</button>
                        </form>
                    </div>
                    <img v-if="parkinglot.image" class="entry-info__image" :src="parkinglot.image" alt="">
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
    Vue.component("add-parkinglot", {
        template: "#add-parkinglot",
        data(){
            return {
                parkinglot: {
                    ownerId: JSON.parse(this.getCookie("user").value).id,
                },
                user: JSON.parse(this.getCookie("user").value)
            }
        },
        methods:{
            getDateInput(e, prop) {
                this.parkinglot[prop] = new Date(e.target.value).getTime();
            },
            getInputDateFormat(date) {
                // let today = new Date();
                let dd = String(date.getDate()).padStart(2, '0');
                let mm = String(date.getMonth() + 1).padStart(2, '0'); //January is 0!
                let yyyy = date.getFullYear();

                return `${yyyy}-${mm}-${dd}`;
            },
            onSubmit(event) {
                event.preventDefault();
                fetch(`/api/parkinglots/add`, {
                    method: 'POST',
                    body: JSON.stringify(this.parkinglot),
                })
                .then((res) => {
                    alert("Din parkeringsplass er blitt registrert")
                    window.location = "/parkinglots/add";
                })
                .catch((err) => {
                    alert("Booking failet.")
                });
            },

            getToday() {
                let today = new Date();
                let dd = String(today.getDate()).padStart(2, '0');
                let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
                let yyyy = today.getFullYear();

                return `${yyyy}-${mm}-${dd}`;
            },
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
        }
    });
</script>
