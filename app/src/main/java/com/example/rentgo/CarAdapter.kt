package com.example.rentgo

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rentgo.databinding.CarLayoutBinding

class CarAdapter(val context: Context, var cars:List<Car>):
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CarLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.binding.apply {
            carImage.setImageResource(cars.get(position).carImage)
            marque.text = cars[position].marque
            price.setText(cars[position].price)
            if(cars[position].availability)
                availability.text = "Available"
            else {
                availability.text = "Not Available"
            }
        }
        holder.itemView.setOnClickListener { view: View ->
            val data = bundleOf("position" to position)
            view.findNavController().navigate(R.id.action_homeFragment_to_carDetailsFragment,data)
        }
    }

    override fun getItemCount() = cars.size
}

class ViewHolder(val binding: CarLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

}