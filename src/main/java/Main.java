import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.rendering.vue.VueComponent;
import no.parkypark.controller.BookingController;
import no.parkypark.controller.ParkinglotsController;
import no.parkypark.model.Booking;
import no.parkypark.utils.JsonStorage;
import no.parkypark.model.Parkinglot;
import no.parkypark.repository.BookingRepository;
import no.parkypark.repository.ParkinglotsRepository;

public class Main {
    private static final String PARKINGLOT_JSON = "src/main/resources/data/parkinglots.json";
    private static final String BOOKING_JSON = "src/main/resources/data/bookings.json";

    public static void main(String[] args) {

      JsonStorage<Parkinglot> parkinglotJsonStorage = new JsonStorage<>(Parkinglot.class, PARKINGLOT_JSON);
      JsonStorage<Booking> bookingJsonStorage = new JsonStorage<>(Booking.class, BOOKING_JSON);
      ParkinglotsRepository parkinglotsRepository = new ParkinglotsRepository(parkinglotJsonStorage);
      ParkinglotsController parkinglotsController = new ParkinglotsController(parkinglotsRepository);
      BookingRepository bookingRepository = new BookingRepository(bookingJsonStorage);
      BookingController bookingController = new BookingController(bookingRepository);

      //App startup
        Javalin app = Javalin.create(config -> {
            //Allows us to use gradle type dependencies for web dependencies
            config.enableWebjars();

            //Adds all files from the assets folder to the site
            config.addStaticFiles("/assets", "src/main/resources/vue/assets", Location.EXTERNAL);

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
         * adds a parkinglot
         * * POST Request.
         * * Send all information about the new parkinglot.
         */
        app.get("/parkinglots/add", new VueComponent("add-parkinglot"));

        /*
         * shows a single parkinglot
         * * Get all information about a single parkinglot
         */
        app.get("/parkinglots/:parkinglotid", new VueComponent("single-parkinglot"));

        /*
         * edits a specific parkinglot for a logged in user
         * POST Request.
         * Update information about a parkinglot
         */
        app.get("/parkinglots/:parkinglotid/edit", new VueComponent("edit-parkinglot"));

        /*
         * shows all parkinglots related to a single user
         * Get all parkingslots for a specific user
         */
        app.get("/user/:userid/parkinglots", new VueComponent("user-parkinglots"));

        /*
         * shows all parkinglots related to a single user
         * Get all parkingslots for a specific user
         */
        app.get("/user/:userid/bookings", new VueComponent("user-bookings"));

        /*
        APIs
        ==========
         */

        /*
         * All parkinglots
         */
        app.get("/api/parkinglots", parkinglotsController::getAllParkinglots);

        /*
         * All parkinglots for a user
         */
        app.get("/api/:userid/parkinglots", parkinglotsController::getAllParkinglotsByUser);

        /*
         * All bookings for a user
         */
        app.get("/api/:userid/bookings", bookingController::getAllBookingsByUser);

        /*
         * Single parkinglot
         */
        app.get("/api/parkinglots/:parkinglotid", parkinglotsController::getParkinglot);

        /*
         * add parkinglot
         */
        app.post("/api/parkinglots/add", parkinglotsController::addParkinglot);

        /*
         * API that receives the data after a form has been submitted on the edit parkinglot page
         */
        app.post("/api/parkinglots/:parkinglotid/edit", parkinglotsController::updateParkinglot);

        /*
         * API that receives the data after a form has been submitted on the book parkinglot page
        */
        app.post("/api/parkinglots/:parkinglotid/book", bookingController::bookParkinglot);
    }

}
