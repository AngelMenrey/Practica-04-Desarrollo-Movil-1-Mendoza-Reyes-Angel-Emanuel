package com.example.practica04mendozareyesangelemanuel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var motocicletas: Array<Motocicleta>
    private lateinit var titulo: TextView
    private lateinit var peso: EditText
    private lateinit var color: EditText
    private lateinit var modelo: EditText
    private lateinit var costo: EditText

    private lateinit var agregar: Button
    private lateinit var buscar: Button
    private lateinit var limpiar: Button

    private lateinit var mostrar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        motocicletas = Array(10) { Motocicleta(0, "", 0, "") }

        titulo = findViewById(R.id.idTitulo)
        peso = findViewById(R.id.idPeso)
        color = findViewById(R.id.idColor)
        modelo = findViewById(R.id.idModelo)
        costo = findViewById(R.id.idCosto)

        agregar = findViewById(R.id.btnAgregar)
        buscar = findViewById(R.id.btnBuscar)
        limpiar = findViewById(R.id.btnLimpiar)

        mostrar = findViewById(R.id.idMostrar)

        agregar.setOnClickListener { agregarMotocicleta() }
        buscar.setOnClickListener { buscarMotocicleta() }
        limpiar.setOnClickListener { limpiarCampos() }
    }

    private fun agregarMotocicleta() {
        val nuevaMotocicleta = Motocicleta(
            peso = peso.text.toString().toInt(),
            color = color.text.toString(),
            costo = costo.text.toString().toInt(),
            modelo = modelo.text.toString()
        )

        var posicionDisponible = -1
        for (i in motocicletas.indices) {
            if (motocicletas[i].modelo.isEmpty()) {
                posicionDisponible = i
                break
            }
        }

        if (posicionDisponible != -1) {
            motocicletas[posicionDisponible] = nuevaMotocicleta
            Toast.makeText(this, "Motocicleta agregada correctamente", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No hay posiciones disponibles en el arreglo de objetos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun buscarMotocicleta() {
        val modeloBuscado = modelo.text.toString()
        var motocicletaEncontrada: Motocicleta? = null

        for (moto in motocicletas) {
            if (moto.modelo == modeloBuscado) {
                motocicletaEncontrada = moto
                break
            }
        }

        if (motocicletaEncontrada != null) {
            peso.setText(motocicletaEncontrada.peso.toString())
            color.setText(motocicletaEncontrada.color)
            costo.setText(motocicletaEncontrada.costo.toString())
            mostrar.text = "Motocicleta encontrada"
        } else {
            mostrar.text = "Motocicleta no encontrada"
            peso.setText("")
            color.setText("")
            costo.setText("")
        }
    }

    private fun limpiarCampos() {
        peso.setText("")
        color.setText("")
        modelo.setText("")
        costo.setText("")
        mostrar.text = ""
    }
}
