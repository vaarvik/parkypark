package no.parkypark.repository;
import no.parkypark.model.IStorage;
import no.parkypark.model.Parkinglot;

import java.util.List;

public class ParkinglotsRepository implements IParkinglotsRepository {
    private final IStorage<Parkinglot> storage;
    private List<Parkinglot> parkinglots;

    public ParkinglotsRepository(IStorage<Parkinglot> storage) {
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
     * @param updatedLot A Parkinglot object containing the changes to be made.
     * @return The parkinglot that was updated.
     */
    public Parkinglot updateParkinglot(Parkinglot updatedLot) throws Exception {
        Parkinglot parkinglot = this.getParkinglotById(updatedLot.getId());

        if (parkinglot == null) {
            throw new Exception("Parkinglot not found");
        }

        parkinglot.update(updatedLot);

        this.storage.write(this.parkinglots);
        this.parkinglots = this.storage.read();
        return parkinglot;
    }
}
