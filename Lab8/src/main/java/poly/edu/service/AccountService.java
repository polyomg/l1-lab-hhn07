package poly.edu.service;

import java.util.Optional;
import poly.edu.model.Account;

public interface AccountService {
    Account findById(String username);
}
