import io.javalin.Javalin;
import io.javalin.plugin.rendering.vue.VueComponent;
import no.parkypark.repository.ParkinglotsRepository;

public class Main {
    public static void main(String[] args) {

        //App startup
        Javalin app = Javalin.create(config -> {
            //Allows us to use gradle type dependencies for web dependencies
            config.enableWebjars();

            //Adds the stylesheet to the site
            config.addSinglePageRoot("/assets/styles/style", "vue/assets/styles/style.css");

        }).start(7001);

        /*
        Pages
        ==========
         */

        /*
         * shows all parkinglots
         * * Get title and picture of all parkinglots
         */
        app.get("/", new VueComponent("index"));

        /*
         * login page
         * * Get information that checks if user is signed in or not
         */
        app.get("/login", new VueComponent("login"));

        /*
         * shows a single parkinglot
         * * Get all information about a single parkinglot
         */
        app.get("/parkinglots/:parkinglotid", new VueComponent("parkinglot-handling/single-parkinglot"));

        /*
         * adds a parkinglot
         * * POST Request.
         * * Send all information about the new parkinglot.
         */
        app.get("/add-parkinglot", new VueComponent("parkinglot-handling/add-parkinglot"));

        /*
         * shows all parkinglots related to a single user
         * Get all parkingslots for a specific user
         */
        app.get("/user/:userid/parkinglots", new VueComponent("parkinglot-handling/user-parkinglots"));

        /*
         * edits a specific parkinglot for a logged in user
         * POST Request.
         * Update information about a parkinglot
         */
        app.get("/user/:userid/parkinglot/:parkinglotid/edit", new VueComponent("parkinglot-handling/edit-parkinglot"));

        /*
         * site for booking a specific parkinglot
         * POST Request.
         * Send all information about the new booking.
         */
        app.get("/parkinglot/:parkinglotid/book", new VueComponent("parkinglot-handling/book-parkinglot"));

        //APIs
        app.get("/api/parkinglots", ctx ->
                ctx.json(new ParkinglotsRepository("src/main/resources/data/parkinglots.json").getAllParkinglots()
                )
        );

    }

}
