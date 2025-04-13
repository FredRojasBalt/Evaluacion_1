package com.example.restaurante.model

class CuentaMesa(val mesa: Int) {

    //Propiedades
    private val _items = mutableListOf<ItemMesa>()
    var aceptaPropina:Boolean = true

    //Metodos
    fun agregarItem(itemMenu: ItemMenu, cantidad: Int){
        _items.add(ItemMesa(itemMenu,cantidad))
    }

    fun agregarItem(itemMesa: ItemMesa){
        _items.add(itemMesa)
    }

    fun calcularSinPropina(): Int{
        return _items.sumOf { it.calcularSubTotal() }
    }

    fun calcularPropina(): Int{
        return if(aceptaPropina) (calcularSinPropina() * 0.1).toInt() else 0
    }

    fun calcularTotal(): Int{
        return calcularSinPropina() + calcularPropina()
    }

}