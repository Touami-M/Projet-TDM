package com.example.rentgo

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rentgo.databinding.SpecificationLayoutBinding

class SpecificationAdapter(val context: Context, var specififcations:List<Specification>):
    RecyclerView.Adapter<ViewHolderSpec>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSpec {
        return ViewHolderSpec(SpecificationLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolderSpec, position: Int) {


        holder.binding.apply {
            specificationIconView.setImageResource(specififcations[position].specifiationIcon)
            specficationName.text = specififcations[position].specifiationName
            specificationValueView.text = specififcations[position].specificationValue
        }

    }

    override fun getItemCount(): Int {
        return specififcations.size
    }
}

class ViewHolderSpec(val binding: SpecificationLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

}