package com.example.evaluacion3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.evaluacion3.databinding.ActivityDatosDepBinding
import com.example.evaluacion3.models.dato
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DatosDep : AppCompatActivity() {

    private lateinit var binding: ActivityDatosDepBinding

    // Activar el firebase realtime database
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDatosDepBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Iniciar base de datos
        database = FirebaseDatabase.getInstance().getReference("DATOS")

        binding.btnGuardar.setOnClickListener {
            // Obtener los datos
            val tiempo = binding.etNombreProducto.text.toString()
            val temperatura = binding.etPrecioProducto.text.toString()
            val calorias = binding.etDescripcionProducto.text.toString()
            // Generar el id aleatorio
            val id = database.child("DATOS").push().key

            if (tiempo.isEmpty()) {
                binding.etNombreProducto.error = "Por favor ingresa un tiempo"
                return@setOnClickListener
            }
            if (temperatura.isEmpty()) {
                binding.etPrecioProducto.error = "Por favor ingresa su temperatura"
                return@setOnClickListener
            }
            if (calorias.isEmpty()) {
                binding.etDescripcionProducto.error = "Por favor ingresa las calorías"
                return@setOnClickListener
            }

            val dato = dato(id, tiempo, temperatura, calorias)

            database.child(id!!).setValue(dato)
                .addOnSuccessListener {
                    binding.etNombreProducto.setText("")
                    binding.etPrecioProducto.setText("")
                    binding.etDescripcionProducto.setText("")
                    Snackbar.make(binding.root, "Dato agregado", Snackbar.LENGTH_SHORT).show()
                }
        }
    }
}
