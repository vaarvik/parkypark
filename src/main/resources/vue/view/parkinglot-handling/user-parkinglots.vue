<template id="user-parkinglots">
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
                    <h1 class="entry-header__heading">Dine parkeringsplasser</h1>
                </header>
            </div>
            <ul class="summary-list" v-if="parkinglots.length">
                <li class="summary-list__item" v-for="parkinglot in parkinglots" :key="parkinglot.id">
                    <a class="summary-list__item-anchor" :href="`/parkinglots/${parkinglot.id}`">
                        <div class="summary-list__info">
                            <header class="summary-header">
                                <h2 class="summary-header__heading">{{ parkinglot.name }}</h2>
                                <p class="summary-header__sub-fact">{{ parkinglot.address }}</p>
                                <p class="summary-header__sub-fact">{{ getDateFormat(new Date(parkinglot.checkin)) }} - {{ getDateFormat(new Date(parkinglot.checkout)) }}</p>
                            </header>
                            <div class="summary-info">
                                <p class="summary-info__price">{{ parkinglot.price }}</p>
                            </div>
                        </div>
                        <img class="summary-list__image" :src="parkinglot.image ? parkinglot.image : `https://cdna.artstation.com/p/assets/images/images/006/108/866/large/herdian-utama-parkinglot.jpg?1496113668`" alt="">
                    </a>
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
    Vue.component("user-parkinglots", {
        template: "#user-parkinglots",
        data(){
            return {
                parkinglots: [],
                user: this.getCookie("user") ? JSON.parse(this.getCookie("user").value) : null,
                fallback: "No parkinglots found"
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
            isRenter() {
                return this.user.type === "renter";
            }
        },
        created(){
            if(this.getCookie("user"))
                fetch(`/api/${this.$javalin.pathParams["userid"]}/parkinglots`)
                    .then(res => res.json())
                    .then(res => this.parkinglots = res)
                    .catch(res => console.log("Could not find any data."))
            else
                window.location = "/login";
        }
    });
</script>
