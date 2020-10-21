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

        //Pages
        app.get("/", new VueComponent("index"));

        //APIs
        app.get("/api/parkinglots", ctx -> ctx.json(new ParkyparkRepository("src/main/resources/data/parkinglots.json")));

    }

}
