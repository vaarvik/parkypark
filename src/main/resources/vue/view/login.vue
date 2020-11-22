<template id="login">
    <div class="site-wrapper">
        <header class="site-header">
            <a href="/">
                <h2 class="site-branding">ParkyPark</h2>
            </a>
        </header>
        <main class="site-content">
            <div class="entry">
                <div class="entry-info">
                    <div class="entry-info__center">
                        <div class="entry-buttons">
                            <button class="btn big" @click='setSessionCookie("user", "{\"type\": \"lender\", \"id\": \"2\", \"name\": \"Frida\"}")'>Logg inn for å drive utleie av parkeringsplasser</button>
                            <button class="btn big" @click='setSessionCookie("user", "{\"type\": \"renter\", \"id\": \"1\", \"name\": \"Jon\"}")'>Logg inn for å leie en parkeringsplass</button>
                        </div>
                    </div>
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
    Vue.component("login", {
        template: "#login",
        methods: {
            setSessionCookie(cName, cValue) {
                document.cookie = cName + "=" + cValue + ";" + "path=/" + "; SameSite=Strict; Secure";
                window.location = "/";
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
            }
        },
        created(){
            if(this.getCookie("user"))
                window.location = "/"
        }
    });
</script>
