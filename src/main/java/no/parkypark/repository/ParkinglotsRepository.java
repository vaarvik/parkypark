package no.parkypark.repository;
import no.parkypark.model.IStorage;
import no.parkypark.model.Parkinglot;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ParkinglotsRepository implements IParkinglotsRepository {
    private IStorage storage;
    private List<Parkinglot> parkinglots;

    public ParkinglotsRepository(IStorage storage) {
       this.storage = storage;
       this.parkinglots = storage.read();
    }

    @Override
    public List<Parkinglot> getAllParkinglots() {
        return this.parkinglots;
    }

    @Override
    public Parkinglot getParkinglot(String name) {
        return null;
    }

    @Override
    public Parkinglot getParkinglotById(String id) {
        for (Parkinglot i: this.parkinglots) {
            if(i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    public Parkinglot getParkinglotByOwnerId(String ownerId) {
        for(Parkinglot i : this.parkinglots ) {
            if(i.getOwnerId().equals(ownerId)) {
                return i;
            }
        }
        return null;
    }

    /**
     * Add Parkinglot
     * ----------
     * Adds a parkinglot to the repository and in the JSON file.
     *
     * @param parkinglot The parkinglot to be added
     * @return The parkinglot that was added.
     */
    @Override
    public Parkinglot addParkinglot(Parkinglot parkinglot) {
        this.parkinglots.add(parkinglot);
        this.storage.write(this.parkinglots);
        this.parkinglots = this.storage.read();
        return parkinglot;
    }

    /**
     * Update Parkinglot
     * ----------
     * Updates a parkinglot in the repository and in the JSON file.
     *
     * @param changes The parkinglot to be added. A Map object.
     * @return The parkinglot that was updated.
     */
    public Parkinglot updateParkinglot(Map<String, List<String>> changes) {
        Parkinglot parkinglot = getParkinglotById(changes.get("id").get(0));

        parkinglot.setName(changes.get("name").get(0));
        parkinglot.setAddress(changes.get("address").get(0));
        parkinglot.setPrice(Double.parseDouble(changes.get("price").get(0)));

        this.storage.write(this.parkinglots);
        this.parkinglots = this.storage.read();
        return parkinglot;
    }
}
