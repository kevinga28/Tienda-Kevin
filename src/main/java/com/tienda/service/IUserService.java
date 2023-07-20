
package com.tienda.service;

import org.springframework.security.core.userdetails.*;

/**
 *
 * @author LABORATORIO 04
 */
public interface IUserService {
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
