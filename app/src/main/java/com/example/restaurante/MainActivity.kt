package com.example.restaurante

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restaurante.model.CuentaMesa
import com.example.restaurante.model.ItemMenu
import com.example.restaurante.model.ItemMesa
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    //fields
    private var etn_plato1: EditText? = null
    private var etn_plato2: EditText? = null
    private var tv_subvalorplato1: TextView? = null
    private var tv_subvalorplato2: TextView? = null
    private var tv_subtotal: TextView? = null
    private var tv_totalvalor: TextView? = null
    private var tv_propina: TextView? = null
    private var sw_propina: Switch? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Obtencion de EditText para las Cantidades
        etn_plato1 = findViewById<EditText>(R.id.etn_plato1)
        etn_plato2 = findViewById<EditText>(R.id.etn_plato2)

        //Obtencion de TextView para el subtotal de un plato
        tv_subvalorplato1 = findViewById<TextView>(R.id.tv_subvalorplato1)
        tv_subvalorplato2 = findViewById<TextView>(R.id.tv_subvalorplato2)

        //Obtencion de los Totales
        tv_subtotal = findViewById<TextView>(R.id.tv_subtotal)
        tv_propina = findViewById<TextView>(R.id.tv_propina)
        tv_totalvalor = findViewById<TextView>(R.id.tv_totalvalor)
        sw_propina = findViewById<Switch>(R.id.sw_propina)

        val tw_total:TextWatcher = object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                mostrarTotales(sw_propina?.isChecked)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        etn_plato1?.addTextChangedListener(tw_total)
        etn_plato2?.addTextChangedListener(tw_total)

        sw_propina?.setOnCheckedChangeListener { buttonview, isChecked ->
            mostrarTotales(sw_propina?.isChecked)
        }

    }

    private fun mostrarTotales(aceptarPropina: Boolean?)
    {
        val cuentaMesa = CuentaMesa(1)
        if (aceptarPropina != null) {
            cuentaMesa.aceptaPropina = aceptarPropina
        }
        val mesa1 = ItemMesa(ItemMenu("Ceviche", 12000),etn_plato1?.text.toString().toIntOrNull() ?: 0)
        val mesa2 = ItemMesa(ItemMenu("Carne", 10000),etn_plato2?.text.toString().toIntOrNull() ?: 0)
        tv_subvalorplato1?.setText(formatearCLP(mesa1.calcularSubTotal()))
        tv_subvalorplato2?.setText(formatearCLP(mesa2.calcularSubTotal()))
        cuentaMesa.agregarItem(mesa1)
        cuentaMesa.agregarItem(mesa2)
        tv_subtotal?.setText(formatearCLP(cuentaMesa.calcularSinPropina()))
        tv_propina?.setText(formatearCLP(cuentaMesa.calcularPropina()))
        tv_totalvalor?.setText(formatearCLP(cuentaMesa.calcularTotal()))
    }

    private fun formatearCLP(valor: Int): String {
        val formatclp = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        return formatclp.format(valor)
    }

}