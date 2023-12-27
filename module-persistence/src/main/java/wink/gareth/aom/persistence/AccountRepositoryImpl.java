package wink.gareth.aom.persistence;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import wink.gareth.aom.core.model.Account;
import wink.gareth.aom.core.model.AccountRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountMongoRepository accountMongoRepository;
    @Override
    public Account save(Account account) throws Exception {
        if (account.getId() == null) throw new Exception("Saving an account with no id.");
        return accountMongoRepository.save(account);
    }

    @Override
    public List<Account> findAll() throws Exception {
        return accountMongoRepository.findAll();
    }

    @Override
    public Optional<Account> findById(ObjectId id) throws Exception {
        return accountMongoRepository.findById(id);
    }

    @Override
    public Optional<Account> findById(String id) throws Exception {
        ObjectId oid = new ObjectId(id);
        return accountMongoRepository.findById(oid);
    }

    @Override
    public void delete(Account account) throws Exception {
        accountMongoRepository.deleteById(account.getId());
    }

    @Override
    public void deleteById(ObjectId id) throws Exception {
        accountMongoRepository.deleteById(id);
    }

}
