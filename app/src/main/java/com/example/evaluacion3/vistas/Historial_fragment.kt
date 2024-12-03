package com.example.evaluacion3.vistas

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.evaluacion3.DatosDep
import com.example.evaluacion3.R


// Constantes para los argumentos del fragmento
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Un fragmento simple que muestra el historial.
 * Usa el método [Historial_fragment.newInstance] para crear una instancia de este fragmento.
 */
class Historial_fragment : Fragment() {

    // Variables para almacenar los parámetros del fragmento
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_historial_fragment, container, false)

        val btn_agregar_datos = view.findViewById<Button>(R.id.btn_agregar_datos)

        btn_agregar_datos.setOnClickListener {
            try {
                // Intent para abrir la actividad datosDep
                val intent = Intent(requireContext(), DatosDep::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                // Manejo de errores
                Toast.makeText(
                    requireContext(),
                    "Error al iniciar la actividad: ${e.localizedMessage}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        return view
    }

    companion object {
        /**
         * Método de fábrica para crear una nueva instancia del fragmento
         * utilizando los parámetros proporcionados.
         *
         * @param param1 Parámetro 1.
         * @param param2 Parámetro 2.
         * @return Una nueva instancia de Historial_fragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Historial_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
