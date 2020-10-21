import io.javalin.Javalin;
import no.parkypark.repository.ParkyparkRepository;

public class Main {
    public static void main(String[] args) {

        //App startup
        Javalin app = Javalin.create().start(7001);

        //Allows us to use gradle type dependencies for web dependencies
        app.config.enableWebjars();

        //Pages
        app.get("/", ctx -> ctx.html("<h1>Halla</h1>"));

        //APIs
        app.get("/api/parkinglots", ctx -> ctx.json(new ParkyparkRepository("src/main/resources/parkinglots.json")));

    }

}
