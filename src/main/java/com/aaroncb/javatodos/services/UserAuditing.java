/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */

package com.aaroncb.javatodos.services;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuditing implements AuditorAware<String>
{
    @Override
    public Optional<String> getCurrentAuditor() {
        String uname;

        uname = "SYSTEM";

        return Optional.of(uname);
    }
}
