/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tienda.entities.Item;
import com.tienda.entities.Product;
import com.tienda.service.IItemService;
import com.tienda.service.IProductService;
import com.tienda.service.impl.ItemService;
import com.tienda.service.impl.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private IItemService itemService;
    @Autowired
    private IProductService articuloService;

    //Para ver el carrito
    @GetMapping("/carrito/listado")
    public String index(Model model) {
        var items = itemService.getItems();
        model.addAttribute("items", items);
        var carritoTotalVenta = 0;
        for (Item i : items) {
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return "/carrito/listado";
    }

//    Para Agregar un articulo al carrito
    @GetMapping("/carrito/agregar/{id}")
    public ModelAndView agregarItem(Model model, Item item) {
        Item item2 = itemService.getItem(item);
        if (item2 == null) {
            Product articulo = articuloService.getProduct(item);
            item2 = new Item(articulo);
        }
        itemService.save(item2);
        var lista = itemService.getItems();
        var totalCarritos = 0;
        var carritoTotalVenta = 0;
        for (Item i : lista) {
            totalCarritos += i.getCantidad();
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", totalCarritos);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return new ModelAndView("/carrito/fragmentosCarrito :: verCarrito");
    }

    //Para mofificar un articulo del carrito
    @GetMapping("/carrito/modificar/{id}")
    public String modificarItem(Item item, Model model) {
        item = itemService.getItem(item);
        model.addAttribute("item", item);
        return "/carrito/modificar";
    }

    //Para eliminar un elemento del carrito
    @GetMapping("/carrito/eliminar/{id}")
    public String eliminarItem(Item item) {
        itemService.delete(item);
        return "redirect:/carrito";
    }

    //Para actualizar un articulo del carrito (cantidad)
    @PostMapping("/carrito/guardar")
    public String guardarItem(Item item) {
        itemService.actualiza(item);
        return "redirect:/carrito";
    }
    
    //Para facturar los articulos del carrito... no implementado...
    @GetMapping("/facturar/carrito")
    public String facturarCarrito() {
        itemService.facturar();
        return "redirect:/";
    }
}  /* Fin de la clase CarritoController.Java */
