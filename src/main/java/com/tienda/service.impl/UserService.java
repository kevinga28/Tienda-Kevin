
package com.tienda.service.impl;

import com.tienda.db.IRolRepository;
import com.tienda.db.IUserRepository;
import com.tienda.entities.Rol;
import com.tienda.entities.Usuario;
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
import java.util.List;

@Service
public class UserService implements UserDetailsService, IUserService {

    private final IUserRepository userRepository;
    private final HttpSession session;

    private final IRolRepository roleRepository;

    public UserService(IUserRepository userRepository, HttpSession session, IRolRepository roleRepository) {
        this.userRepository = userRepository;
        this.session = session;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Busca el usuario por el username en la tabla
        Usuario usuario = this.userRepository.findByUsername(username);
        //Si no existe el usuario lanza una excepción
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        session.removeAttribute("usuarioImagen");
        session.setAttribute("usuarioImagen", usuario.getRutaImagen());
        //Si está acá es porque existe el usuario... sacamos los roles que tiene
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol : usuario.getRoles()) {   //Se sacan los roles
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        //Se devuelve User (clase de userDetails)
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }


    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return this.userRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return this.userRepository.findById(usuario.getIdUsuario()).orElse(null);
    }


    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsername(String username) {
        return this.userRepository.findByUsername(username);
    }


    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameYPassword(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username, password);
    }


    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameOCorreo(String username, String correo) {
        return this.userRepository.findByUsernameOrCorreo(username, correo);
    }


    @Transactional(readOnly = true)
    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo) {
        return this.userRepository.existsByUsernameOrCorreo(username, correo);
    }


    @Transactional
    public void save(Usuario usuario, boolean crearRolUser) {
        usuario = this.userRepository.save(usuario);
        if (crearRolUser) {//Si se está creando el usuario, se crea el rol por defecto "USER"
            Rol rol = new Rol();
            rol.setNombre("ROLE_USER");
            rol.setIdUsuario(usuario.getIdUsuario());
            this.roleRepository.save(rol);
        }
    }

    @Transactional
    public void delete(Usuario usuario) {
        this.userRepository.delete(usuario);
    }
}