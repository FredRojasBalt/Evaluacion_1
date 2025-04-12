package com.example.restaurante

import android.os.Bundle
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        
    }
}