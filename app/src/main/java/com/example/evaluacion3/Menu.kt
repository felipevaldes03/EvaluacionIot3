package com.example.evaluacion3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.evaluacion3.databinding.ActivityMenuBinding
import com.example.evaluacion3.vistas.Actividad_fragment
import com.example.evaluacion3.vistas.Historial_fragment
import com.example.evaluacion3.vistas.Perfil_fragment

class Menu : AppCompatActivity() {

    //configurar viewBinding
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Cargar fragment por defecto
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,Perfil_fragment()).commit()
        }
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.item1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, Perfil_fragment()).commit();true
                }
                R.id.item2 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, Actividad_fragment()).commit();true
                }
                R.id.item3 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, Historial_fragment()).commit();true
                }
                else -> false
            }
        }

        binding.bottomNavigation.setOnItemReselectedListener{
            when(it.itemId){
                R.id.item1 -> {
                    true
                }
                R.id.item2 -> {
                    true
                }
                R.id.item3 -> {
                    true
                }
                else -> false

            }
        }
    }
}