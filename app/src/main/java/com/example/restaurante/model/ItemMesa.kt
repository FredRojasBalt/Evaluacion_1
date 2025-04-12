package com.example.restaurante.model

class ItemMesa (val itemMenu: ItemMenu, val cantidad: Int){

    fun calcularSubTotal(): Int{
        return (itemMenu.precio) * cantidad
    }
}