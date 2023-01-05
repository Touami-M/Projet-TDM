package com.example.rentgo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentgo.databinding.FragmentHomeBinding


class HomeFragment : Fragment(){
    lateinit var binding: FragmentHomeBinding
    lateinit var carModel: CarModel
    lateinit var brandModel: BrandModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        brandModel = ViewModelProvider(requireActivity()).get(BrandModel::class.java)
        binding.brandsRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
        binding.brandsRecyclerView.adapter = BrandAdapter(requireActivity(),brandModel.brands)
        val itemDecor1 = DividerItemDecoration(requireActivity(),1)
        binding.carsRecycleView.addItemDecoration(itemDecor1)

        carModel = ViewModelProvider(requireActivity()).get(CarModel::class.java)
        binding.carsRecycleView.layoutManager = GridLayoutManager(requireActivity(),resources.getInteger(R.integer.nbcol))
        binding.carsRecycleView.adapter = CarAdapter(requireActivity(),carModel.cars)
        val itemDecor = DividerItemDecoration(requireActivity(),1)
        binding.carsRecycleView.addItemDecoration(itemDecor)
    }
}