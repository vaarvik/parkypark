<template id="index">
    <div>
        <header class="site-header">
            <h1 class="site-branding">ParkyPark</h1>
            <div class="site-navigation">
                <a href="">Add parkinglot</a>
                <a href="">Your parkinglots</a>
                <a href="">Logout</a>
            </div>
        </header>
        <main class="site-content">
            <ul class="summary-list" v-if="parkinglots.length">
                <li class="summary-list__item" v-for="parkinglot in parkinglots" :key="parkinglot.id">
                    <a class="summary-list__item-anchor" :href="`/parkinglots/${parkinglot.id}`">
                        <div class="summary-list__info">
                            <header class="summary-header">
                                <h2 class="summary-header__heading">{{ parkinglot.name }}</h2>
                                <p class="summary-header__sub-fact">{{ parkinglot.address }}</p>
                            </header>
                            <div class="summary-info">
                                <p class="summary-info__price">{{ parkinglot.price }}</p>
                            </div>
                        </div>
                        <img class="summary-list__image" :src="`https://picsum.photos/seed/${parkinglot.id}/300/100`" alt="">
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
    Vue.component("index", {
        template: "#index",
        data(){
            return {
                parkinglots: [],
                fallback: "No parkinglots found"
            }
        },
        created(){
            fetch("api/parkinglots")
                .then(res => res.json())
                .then(res => this.parkinglots = res)
                .catch(res => console.log("Could not find any data."))
        }
    });
</script>
