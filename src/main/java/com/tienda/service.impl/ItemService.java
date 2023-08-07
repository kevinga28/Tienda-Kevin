/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.tienda.entities.Item;
import com.tienda.service.IItemService;

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
    @Override
    public void facturar() {
        for (Item i : ListItems) {
            // Proceso de facturación...
        }
        ListItems.clear();
    }
} /* Fin de la clase CarritoServiceImpl */
