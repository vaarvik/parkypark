package no.parkypark.repository;

import no.parkypark.model.Lender;
import no.parkypark.model.Renter;
import no.parkypark.model.User;

import java.util.List;

public interface IUsersRepository {
    List<Lender> getAllLenders();

    List<Renter> getAllRenters();

    List<User> getAllUsers();

    User getUserById();

    User addUser();

    User updateUser();

    User deleteUser();
}
