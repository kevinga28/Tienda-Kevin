/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.db;
import org.springframework.data.repository.CrudRepository;
import com.tienda.entities.Userr;

public interface IUserRepository extends CrudRepository <Userr, Long> {
    Userr findByUsername(String username);

}
