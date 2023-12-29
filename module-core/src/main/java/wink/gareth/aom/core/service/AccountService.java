package wink.gareth.aom.core.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import wink.gareth.aom.core.model.Account;
import wink.gareth.aom.core.model.AccountRepository;
import wink.gareth.aom.core.model.Paycheck;
import wink.gareth.aom.core.model.PaymentRecord;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public List<Account> getAllAccountsInfo() throws Exception{
        return accountRepository.findAll();
    }

    public Account getAccountById(String id) throws Exception{
        return accountRepository.findById(id).orElseThrow(() -> new Exception("Account not found"));
    }

    public Account createAccount(Account account) throws Exception{
        return accountRepository.save(account);
    }

    public void deleteAccount(String id) throws Exception{
        ObjectId objectId = new ObjectId(id);
        accountRepository.deleteById(objectId);
    }

    public Account claimPaymentToTarget(String accountId, String targetId) throws Exception{
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new Exception("Account not found"));
        Account target = accountRepository.findById(targetId).orElseThrow(() -> new Exception("Target not found"));

        // find the first paycheck that has the target
        // if found, remove the target from the paycheck
        // if not found, throw exception
        Paycheck paycheckToTarget = account.getRemainingPaychecks().stream()
                .filter(paycheck -> paycheck.getCandidate().getId().equals(target.getId()))
                .findFirst()
                .orElseThrow(() -> new Exception("Target not found in any paycheck"));
        account.deletePaycheck(paycheckToTarget);

        // update payment record
        PaymentRecord paymentRecord = new PaymentRecord(paycheckToTarget.getShouldPay(), target);
        account.addPaymentRecord(paymentRecord);

        // sync to database
        return accountRepository.save(account);
    }
}
