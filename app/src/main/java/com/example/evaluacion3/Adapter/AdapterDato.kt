import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluacion3.R
import com.example.evaluacion3.models.dato

class AdapterDato(
    private var datos: ArrayList<dato>,
    private val onItemClick: (dato) -> Unit
) : RecyclerView.Adapter<AdapterDato.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tiempo: TextView = itemView.findViewById(R.id.tvTiem)
        val temperatura: TextView = itemView.findViewById(R.id.tvTem)
        val calorias: TextView = itemView.findViewById(R.id.tvCal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_datos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dato = datos[position]
        holder.tiempo.text = dato.tiempo
        holder.temperatura.text = dato.temperatura
        holder.calorias.text = dato.calorias

        holder.itemView.setOnClickListener {
            onItemClick(dato)
        }
    }

    override fun getItemCount(): Int = datos.size
}
