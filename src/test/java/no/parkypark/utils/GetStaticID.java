package no.parkypark.utils;

import java.util.ArrayList;
import java.util.UUID;

public class GetStaticID {
    //create a static array with IDs for the userIds
    static ArrayList<String> staticIds = new ArrayList<>();

    public GetStaticID() {
    }

    public String getStaticId(int index, boolean isNew) {
        if(isNew) {
            staticIds.add(index, UUID.randomUUID().toString());
        }
        return staticIds.get(index);
    }

    public String getStaticId(int index) {
        return staticIds.get(index);
    }
}
