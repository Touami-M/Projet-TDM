package com.example.rentgo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rentgo.databinding.CarLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SavedAdapter(val context: Context, var cars:List<Car>):
    RecyclerView.Adapter<ViewHolder4>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder4 {
        return ViewHolder4(CarLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder4, position: Int) {


        holder.binding.apply {

            Glide.with(context).load(url+cars[position].photo_list).into(carImage)
            marque.text = cars[position].marque +" "+ cars[position].model
            price.setText(cars[position].prix)
            if(cars[position].dispo==1) {
                availability.text = "Available"
                imageView6.setImageResource(R.drawable.available_true)
            }else {
                availability.text = "Not Available"
                imageView6.setImageResource(R.drawable.available_false)
            }
            imageView.setImageResource(R.drawable.ic_saved)
        }
        holder.binding.imageView.setOnClickListener{
            deleteSaved(cars[position].id,holder)
        }

        holder.itemView.setOnClickListener { view: View ->
            val data = bundleOf("position" to position)
            view.findNavController().navigate(R.id.action_savedFragment_to_carDetailsFragment,data)
        }
    }

    private fun deleteSaved(id: Int, holder: ViewHolder4) {
        CoroutineScope(Dispatchers.IO).launch {
            val pref = context.getSharedPreferences("users", Context.MODE_PRIVATE)
            val data: HashMap<String, Int> = HashMap()
            pref.getInt("idUser", 0)?.let { data.put("iduser", it) }
            data.put("idcar",id)
            val response =  RetrofitService.endpoint.deleteSaved(data)
            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    val response = response.body()
                    if(response!=null) {
                        holder.binding.imageView.setImageResource(R.drawable.save_icon)
                    }
                }
                else {
                    Toast.makeText(context,"An error has occurred", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }

    override fun getItemCount() = cars.size
}

class ViewHolder4(val binding: CarLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

}