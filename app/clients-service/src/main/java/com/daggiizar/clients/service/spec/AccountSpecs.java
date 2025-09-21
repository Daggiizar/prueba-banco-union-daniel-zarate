package com.daggiizar.clients.service.spec;

import com.daggiizar.clients.domain.Account;
import com.daggiizar.clients.domain.AccountType;
import com.daggiizar.clients.domain.Bank;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;

public class AccountSpecs {

    public static Specification<Account> active() {
        return (root, q, cb) -> cb.equal(root.get("estado"), "ACTIVO");
    }

    public static Specification<Account> matches(String term) {
        return (root, q, cb) -> {
            String like = "%" + term.toLowerCase() + "%";
            Join<Account, Bank> bank = root.join("bank");
            Join<Account, AccountType> type = root.join("accountType");
            Expression<String> aliasOrHolder = cb.lower(cb.coalesce(root.get("alias"), root.get("holderFullName")));
            return cb.or(
                    cb.like(aliasOrHolder, like),
                    cb.like(cb.lower(bank.get("name")), like),
                    cb.like(cb.lower(type.get("name")), like),
                    cb.like(cb.lower(root.get("accountNumber")), like)
            );
        };
    }
}
