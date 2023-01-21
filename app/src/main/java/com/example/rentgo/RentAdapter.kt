package com.example.rentgo

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.*
import androidx.recyclerview.widget.RecyclerView
import com.example.rentgo.databinding.RentLayoutBinding

class RentAdapter(val context: Context, var rents:List<Rent>):
    RecyclerView.Adapter<ViewHolder3>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder3 {
        return ViewHolder3(RentLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder3, position: Int) {


        holder.binding.apply {
            rentImage.setImageResource(rents.get(position).photo_list)
            marque.text = rents[position].marque + rents[position].model
        }
        holder.itemView.setOnClickListener { view: View ->
            val data = bundleOf("position" to position)
            view.findNavController().navigate(R.id.action_rentsFragment_to_rentDetailsFragment,data)
        }
    }

    override fun getItemCount() = rents.size

}

class ViewHolder3(val binding: RentLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

}

