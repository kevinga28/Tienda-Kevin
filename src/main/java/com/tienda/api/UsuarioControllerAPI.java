/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.api;

import com.tienda.entities.Usuario;
import com.tienda.service.IUserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/usuario")
public class UsuarioControllerAPI {
    private final IUserService userService;

    public UsuarioControllerAPI(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<Usuario> getUsuarios() {
        return this.userService.getAll();
    }

    @GetMapping()
    public Usuario getById(@RequestParam("idUsuario") int id) {
        var usuario = this.userService.getById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
    }

    @PostMapping()
    public Usuario save(@RequestBody Usuario usuario) {
        return this.userService.save(usuario);
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestBody Usuario usuario) {
        this.userService.delete(usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}