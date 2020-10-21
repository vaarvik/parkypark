import io.javalin.Javalin;
import io.javalin.plugin.rendering.vue.VueComponent;
import no.parkypark.repository.ParkyparkRepository;

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

        //shows all parkinglots
        app.get("/", new VueComponent("index"));

        //login page
        app.get("/login", new VueComponent("login"));

        //shows a single parkinglot
        app.get("/parkinglots/:parkinglotid", new VueComponent("parkinglot-handling/single-parkinglot"));
//
        //adds a parkinglot
        app.get("/add-parkinglot", new VueComponent("parkinglot-handling/add-parkinglot"));
//
        //shows all parkinglots related to a single user
        app.get("/user/:userid/parkinglots", new VueComponent("parkinglot-handling/user-parkinglots"));

        //edits a specific parkinglot for a logged in user
        app.get("/user/:userid/parkinglot/:parkinglotid/edit", new VueComponent("parkinglot-handling/edit-parkinglot"));

        //site for booking a specific parkinglot
        app.get("/parkinglot/:parkinglotid/book", new VueComponent("parkinglot-handling/book-parkinglot"));

        //APIs
        app.get("/api/parkinglots", ctx -> ctx.json(new ParkyparkRepository("src/main/resources/data/parkinglots.json")));

    }

}
