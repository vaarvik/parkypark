import io.javalin.Javalin;
import io.javalin.plugin.rendering.vue.VueComponent;
import no.parkypark.controller.BookingController;
import no.parkypark.repository.BookingRepository;
import no.parkypark.controller.ParkinglotsController;
import no.parkypark.repository.ParkinglotsRepository;

public class Main {
    public static void main(String[] args) {

      ParkinglotsRepository parkinglotsRepository = new ParkinglotsRepository("src/main/resources/data/parkinglots.json");
      ParkinglotsController parkinglotsController = new ParkinglotsController(parkinglotsRepository);
      BookingRepository bookingRepository = new BookingRepository();
      BookingController bookingController = new BookingController(bookingRepository);

      //App startup
        Javalin app = Javalin.create(config -> {
            //Allows us to use gradle type dependencies for web dependencies
            config.enableWebjars();

            //Adds the stylesheet to the site
            config.addSinglePageRoot("/assets/styles/style", "vue/assets/styles/style.css");

        }).start(7048);

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
        app.get("/parkinglots/add", new VueComponent("parkinglot-handling/add-parkinglot"));

        /*
         * edits a specific parkinglot for a logged in user
         * POST Request.
         * Update information about a parkinglot
         */
        app.get("/parkinglots/:parkinglotid/edit", new VueComponent("edit-parkinglot"));

        /*
         * site for booking a specific parkinglot
         * POST Request.
         * Send all information about the new booking.
         */
        app.get("/parkinglots/:parkinglotid/book", new VueComponent("parkinglot-handling/book-parkinglot"));

        /*
         * shows all parkinglots related to a single user
         * Get all parkingslots for a specific user
         */
        app.get("/user/:userid/parkinglots", new VueComponent("parkinglot-handling/user-parkinglots"));

        //APIs
        app.get("/api/parkinglots", ctx ->
                ctx.json(new ParkinglotsRepository("src/main/resources/data/parkinglots.json").getAllParkinglots())
        );

        app.post("/api/booking/book", bookingController::bookParkinglot);


        /*
         * All parkinglots
         */
        app.get("/api/parkinglots", parkinglotsController::getAllParkinglots);

        /*
         * Single parkinglot
         */
        app.get("/api/parkinglots/:parkinglotid", parkinglotsController::getParkinglot);

        /*
         * API that receives the data after a form has been submitted on the edit parkinglot page
         */
        app.post("/api/parkinglots/:parkinglotid/edit", parkinglotsController::updateParkinglot);

    }

}
