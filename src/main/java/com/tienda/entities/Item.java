
package com.tienda.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Item extends Product {
    private int cantidad; // Almacenar la cantidad de items de un articulo

    public Item() {
    }

    public Item(Product producto) {
        super.setId(producto.getId());
        super.setCategory(producto.getCategory());
        super.setDescripcion(producto.getDescripcion());
        super.setDetalle(producto.getDetalle());
        super.setPrecio(producto.getPrecio());
        super.setExistencias(producto.getExistencias());
        super.setActivo(producto.isActivo());
        super.setRuta_imagen(producto.getRuta_imagen());
        this.cantidad = 0;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
  
}