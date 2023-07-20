
package com.tienda.service.impl;
import com.tienda.db.IUserRepository;
import com.tienda.entities.Rol;
import com.tienda.entities.Userr;
import com.tienda.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.ArrayList;



@Service
public class UserService implements UserDetailsService, IUserService {



private final IUserRepository userRepository;
private final HttpSession session;



public UserService(IUserRepository userRepository, HttpSession session) {
this.userRepository = userRepository;
this.session = session;
}



@Override
@Transactional(readOnly = true)
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//Busca el usuario por el username en la tabla
Userr usuario = this.userRepository.findByUsername(username);
//Si no existe el usuario lanza una excepción
if (usuario == null) {
throw new UsernameNotFoundException(username);
}
session.removeAttribute("usuarioImagen");
session.setAttribute("usuarioImagen", usuario.getRutaImagen());
//Si está acá es porque existe el usuario... sacamos los roles que tiene
var roles = new ArrayList();
for (Rol rol : usuario.getRoles()) { //Se sacan los roles
roles.add(new SimpleGrantedAuthority(rol.getNombre()));
}
//Se devuelve User (clase de userDetails)
return new User(usuario.getUsername(), usuario.getPassword(), roles);
}



}



