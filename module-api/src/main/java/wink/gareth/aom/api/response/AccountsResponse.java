package wink.gareth.aom.api.response;

import wink.gareth.aom.core.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public record AccountsResponse(List<AccountResponse> accounts) {
    public static AccountsResponse from(List<Account> accounts) {
        return new AccountsResponse(accounts.stream().map(AccountResponse::new).collect(Collectors.toList()));
    }
}
