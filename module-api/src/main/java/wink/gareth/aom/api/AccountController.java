package wink.gareth.aom.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wink.gareth.aom.api.response.AccountsResponse;
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
}
