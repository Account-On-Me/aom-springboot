package wink.gareth.aom.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wink.gareth.aom.core.model.Account;
import wink.gareth.aom.core.model.AccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public List<Account> getAllAccountsInfo() throws Exception{
        return accountRepository.findAll();
    }
}
