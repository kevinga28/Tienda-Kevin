/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.db;

import com.tienda.entities.Rol;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author XPC
 */
public interface IRolRepository extends CrudRepository<Rol, Long> {
    
}
