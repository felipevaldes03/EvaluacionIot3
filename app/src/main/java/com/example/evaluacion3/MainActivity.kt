package com.example.evaluacion3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.evaluacion3.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    // Configuración de viewBinding
    private lateinit var binding: ActivityMainBinding

    // Configurar Firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Manejo de insets (barras del sistema)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar Firebase Auth
        auth = Firebase.auth

        // Programar el botón de login
        binding.loginButton.setOnClickListener {
            val email = binding.correo.text.toString()
            val password = binding.clave.text.toString()

            if (email.isEmpty()) {
                binding.correo.error = "Por favor, ingrese un correo"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.clave.error = "Por favor, ingrese la contraseña"
                return@setOnClickListener
            }

            signIn(email, password)
        }

        //Programar el enlace para ir al registro
        binding.registrarse.setOnClickListener {
            try {
                val intent = Intent(this,registro::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)
                    finish() // Cierra la actividad actual
                } else {
                    val errorMessage = task.exception?.message ?: "Error desconocido"
                    Toast.makeText(this, "Error al iniciar sesión: $errorMessage", Toast.LENGTH_LONG).show()
                }
            }
    }
}
