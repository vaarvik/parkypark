import io.javalin.Javalin;
import io.javalin.plugin.rendering.vue.VueComponent;
import no.parkypark.repository.ParkyparkRepository;

public class Main {
    public static void main(String[] args) {

        //App startup
        Javalin app = Javalin.create().start(7001);

        //Allows us to use gradle type dependencies for web dependencies
        app.config.enableWebjars();

        //Pages
        app.get("/", new VueComponent("index"));

        //APIs
        app.get("/api/parkinglots", ctx -> ctx.json(new ParkyparkRepository("src/main/resources/data/parkinglots.json")));

    }

}
