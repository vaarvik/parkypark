<template id="user-bookings">
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
                    <h1 class="entry-header__heading">Dine bookinger</h1>
                    <p>Nedenfor finner du alle dine bookinger. Ta kontakt med kundeservice dersom noe skulle være feil eller du ønsker å avbestille.</p>
                </header>
            </div>
            <ul class="summary-list" v-if="bookings.length">
                <li class="summary-list__item suddle" v-for="booking in bookings" :key="booking.id">
                    <div class="summary-list__item-anchor">
                        <div class="summary-list__info">
                            <header class="summary-header">
                                <h2 class="summary-header__heading">{{ booking.parkinglot.name }}</h2>
                                <p class="summary-header__sub-fact">{{ booking.parkinglot.address }}</p>
                                <p class="summary-header__sub-fact">Booket fra/til:<br>
                                {{ getDateFormat(new Date(booking.checkIn)) }} - {{ getDateFormat(new Date(booking.checkOut)) }}</p>
                            </header>
                            <div class="summary-info">
                                <p class="summary-info__text">Kostnad:</p>
                                <p class="summary-info__price">
                                    {{ booking.parkinglot.price * (getDateDifference(new Date(booking.checkOut), new Date(booking.checkIn)) + 1) }}
                                </p>
                                <a :href="`/parkinglots/${booking.parkinglotId}`">
                                    <button class="btn">Sjekk ut parkeringsplassen</button>
                                </a>
                            </div>
                        </div>
                        <img class="summary-list__image" :src="booking.parkinglot.image ? booking.parkinglot.image : `https://cdna.artstation.com/p/assets/images/images/006/108/866/large/herdian-utama-parkinglot.jpg?1496113668`" alt="">
                    </div>
                </li>
            </ul>
            <p v-else>{{fallback}}</p>
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
    Vue.component("user-bookings", {
        template: "#user-bookings",
        data(){
            return {
                bookings: [],
                user: this.getCookie("user") ? JSON.parse(this.getCookie("user").value) : null,
                fallback: "Sjekk ut forsiden og book din første parkeringsplass!"
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
            deleteCookie(name) {
                document.cookie = name +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
            },
            onLogout(e) {
                e.preventDefault();
                this.deleteCookie("user");
                window.location = "/login";
            },
            getDateFormat(date) {
                return `${date.getDate()}.${date.getMonth() + 1}.${date.getFullYear()}`
            },
            getDateDifference(checkOut, checkIn) {
                const timeDif = checkOut.getTime() - checkIn.getTime();
                return timeDif / (1000 * 3600 * 24)
            },
            isRenter() {
                return this.user.type === "renter";
            }
        },
        created(){
            if(this.getCookie("user"))
                fetch(`/api/${this.$javalin.pathParams["userid"]}/bookings`)
                    .then(res => res.json())
                    .then((res) => {
                        res.map(booking => {
                            fetch(`/api/parkinglots/${booking.parkinglotId}`)
                                .then(pRes => pRes.json())
                                .then(pRes => booking.parkinglot = pRes)
                                .then(pRes => {
                                    this.bookings.push({...booking, parkinglot: pRes});
                                    return pRes;
                                })
                                .catch(pRes => console.log("Could not find the belonging parkinglot."))
                        });
                        return res;
                    })
                    .catch(res => console.log("Could not find any data."))
            else
                window.location = "/login";
        }
    });
</script>
