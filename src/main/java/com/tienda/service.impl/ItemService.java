/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service.impl;

import com.tienda.db.IFacturaRepository;
import com.tienda.db.IProductRepository;
import com.tienda.db.IVentaRepository;
import com.tienda.entities.Factura;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.tienda.entities.Item;
import com.tienda.entities.Product;
import com.tienda.entities.Usuario;
import com.tienda.entities.Venta;
import com.tienda.service.IItemService;
import com.tienda.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class ItemService implements IItemService {

    @Override
    public List<Item> getItems() {
        return ListItems;
    }

    // Se usa en el addCarrito... agrega un elemento
    @Override
    public void save(Item item) {
        boolean existe = false;
        for (Item i : ListItems) {
            // Busca si ya existe el articulo en el carrito
            if (Objects.equals(i.getId(), item.getId())) {
                // Valida si aún puede colocar un item adicional -segun existencias-
                if (i.getCantidad() < item.getExistencias()) {
                    // Incrementa en 1 la cantidad de elementos
                    i.setCantidad(i.getCantidad() + 1);
                }
                existe = true;
                break;
            }
        }
        if (!existe) { // Si no está el articulo en el carrito entonces lo agrega cantidad =1.
            item.setCantidad(1);
            ListItems.add(item);
        }
    }

    // Se usa para eliminar un articulo del carrito
    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item i : ListItems) {
            ++posicion;
            if (Objects.equals(i.getId(), item.getId())) {
                existe = true;
                break;
            }
        }
        if (existe) {
            ListItems.remove(posicion);
        }
    }

    // Se obtiene la información de un articulo del carrito... para modificarlo
    @Override
    public Item getItem(Item item) {
        for (Item i : ListItems) {
            if (Objects.equals(i.getId(), item.getId())) {
                return i;
            }
        }
        return null;
    }

    // Se usa en la página para actualizar la cantidad de articulos
    @Override
    public void actualiza(Item item) {
        for (Item i : ListItems) {
            if (Objects.equals(i.getId(), item.getId())) {
                i.setCantidad(item.getCantidad());
                break;
            }
        }
    }

    /*
     * En un futuro sería el proceso de generar la facturación...
     * por ahora sólo borra los elementos del carrito
     */
       @Autowired
    private IUserService userService;

    @Autowired
    private IFacturaRepository facturaRepository ;
    @Autowired
    private IVentaRepository ventaRepository ;
    @Autowired
    private IProductRepository productRepository;

    @Override
    public void facturar() {
        System.out.println("Facturando");

        //Se obtiene el usuario autenticado
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }

        if (username.isBlank()) {
            return;
        }

        Usuario user = userService.getUsuarioPorUsername(username);

        if (user == null) {
            return;
        }

        Factura factura = new Factura(user.getIdUsuario());
        factura = facturaRepository.save(factura);

        double total = 0;
        for (Item i : ListItems) {
            System.out.println("Producto: " + i.getDescripcion()
                    + " Cantidad: " + i.getCantidad()
                    + " Total: " + i.getPrecio() * i.getCantidad());
            Venta venta = new Venta(factura.getIdFactura(), i.getId(), i.getPrecio(), i.getCantidad());
            ventaRepository.save(venta);
            Product producto = productRepository.getReferenceById(i.getId());
            producto.setExistencias(producto.getExistencias()-i.getCantidad());
            productRepository.save(producto);
            total += i.getPrecio() * i.getCantidad();
        }
        factura.setTotal(total);
        facturaRepository.save(factura);
        ListItems.clear();
    }
}
