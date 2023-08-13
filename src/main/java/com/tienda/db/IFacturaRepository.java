/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.db;

import com.tienda.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFacturaRepository extends JpaRepository <Factura,Long> {
     
}