package no.parkypark.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class JSONFile {
    private String file;
    private GetStaticID idRepo;
    private ArrayList<String> userIds = new ArrayList<>();

    public JSONFile(GetStaticID idRepo, String file) {
        this.idRepo = idRepo;
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public String getUserId(int index){
        return userIds.get(index);
    }

    public String generateUserId(){
        String newId = UUID.randomUUID().toString();
        userIds.add(newId);
        return newId;
    }

    public void prepare() {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(
                    "[ {\n" +
                            "  \"name\" : \"Smith\",\n" +
                            "  \"address\" : \"21 2nd Street\",\n" +
                            "  \"id\" : \"" + generateUserId() + "\",\n" +
                            "  \"userId\" : \"" + idRepo.getStaticId(0) + "\",\n" +
                            "  \"price\" : 50.0\n" +
                            "}, {\n" +
                            "  \"name\" : \"John\",\n" +
                            "  \"address\" : \"Matrix Street\",\n" +
                            "  \"id\" : \"" + generateUserId() + "\",\n" +
                            "  \"userId\" : \"" + idRepo.getStaticId(1) + "\",\n" +
                            "  \"price\" : 30.0\n" +
                            "} ]"
            );
            writer.close();
            System.out.println("@BeforeEach: Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("@BeforeEach: Could not write to file.");
            e.printStackTrace();
        }
    }
}
