package main.DAO;

import main.entity.Account;

import java.util.List;
import java.util.Map;

public interface AccountDAO {
    int append(Account account);
    int delete(String ac_id);
    int modify(Account account);
    String infoList(String ac_id);
}

