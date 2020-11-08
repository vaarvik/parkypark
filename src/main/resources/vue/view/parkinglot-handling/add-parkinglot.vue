<template id="add-parkinglot">
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
                    <h1 class="entry-header__heading">Opprett parkeringsplass</h1>
                </header>
                <div class="entry-info">
                    <div class="entry-info__text">
                        <form @submit="checkForm" :action="`/api/parkinglots/add`" method="post">
                                <label for="name">Navn</label>
                                <input class="field input" type="text" name="name" id="name" v-model="name">
                                <br>

                                <label for="address">Adresse</label>
                                <input class="field input" type="text" name="address" id="address" v-model="address">
                                <br>

                                <label for="price">Pris</label>
                                <input class="field input" type="number" step="1" name="price" id="price" v-model="price" min="0">
                                <br>

                                <label for="image">Bilde</label>
                                <input placeholder="Bildeurl..." class="field input" type="url" name="image" id="image" v-model="image">
                                <br>

                                <label for="checkin">Check in</label>
                                <input class="field input" type="date" :min="getToday()" name="checkin" id="checkin" v-model="checkin">
                                <br>

                                <label for="checkout">Check out</label>
                                <input class="field input" type="date" :min="checkin ? checkin : getToday()" name="checkout" id="checkout" v-model="checkout">
                                <br>

                                <label for="description">Beskrivelse</label>
                                <textarea class="field" name="description" id="description" cols="30" rows="2" v-model="description"></textarea>
                                <br>

                                <input type="hidden" name="userid" id="userid" value="1" v-model="userid">

                                <button class="btn">Opprett parkeringsplass</button>
                        </form>
                    </div>
                    <img v-if="image" class="entry-info__image" :src="image" alt="">
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
        data: () => ({
            name: null,
            address: null,
            image: null,
            checkin: null,
            checkout: null,
            userid: null,
            price: null,
            description: null,
        }),
        methods:{
            checkForm:function(e) {
            },

            getToday() {
                let today = new Date();
                let dd = String(today.getDate()).padStart(2, '0');
                let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
                let yyyy = today.getFullYear();

                return `${yyyy}-${mm}-${dd}`;
            }
        }
    });
</script>
