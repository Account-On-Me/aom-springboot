package wink.gareth.aom.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wink.gareth.aom.api.request.ClaimPaymentRequest;
import wink.gareth.aom.api.response.AccountResponse;
import wink.gareth.aom.api.response.AccountsResponse;
import wink.gareth.aom.core.model.Account;
import wink.gareth.aom.core.service.AccountService;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private static final String BASE_URL = "/api/account";

    private final AccountService accountService;

    @GetMapping(BASE_URL + "/list")
    public AccountsResponse doGetAll() throws Exception {
        return AccountsResponse.from(accountService.getAllAccountsInfo());
    }

    @GetMapping(BASE_URL + "/{id}")
    public AccountResponse doGetById(@PathVariable String id) throws Exception {
        return new AccountResponse(accountService.getAccountById(id));
    }

    @PostMapping(BASE_URL + "/create")
    public AccountResponse createAccount(@RequestBody Account account) throws Exception {
        return new AccountResponse(accountService.createAccount(account));
    }

    @DeleteMapping(BASE_URL + "/{id}")
    public void deleteAccount(@PathVariable String id) throws Exception {
        accountService.deleteAccount(id);
    }

    @PostMapping(BASE_URL + "/claimPaid")
    public AccountResponse claimPaymentToTarget(@RequestBody ClaimPaymentRequest claimPaymentRequest) throws Exception{
        return new AccountResponse(accountService.claimPaymentToTarget(
                claimPaymentRequest.accountId(),
                claimPaymentRequest.targetId())
        );
    }
}
