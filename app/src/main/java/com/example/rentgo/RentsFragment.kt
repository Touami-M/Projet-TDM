package com.example.rentgo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rentgo.databinding.FragmentRentsBinding


class RentsFragment : Fragment() {
    lateinit var binding: FragmentRentsBinding
    lateinit var rentModel: RentModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRentsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rentModel = ViewModelProvider(requireActivity()).get(RentModel::class.java)
        binding.rentsRecycleView.layoutManager = GridLayoutManager(requireActivity(),resources.getInteger(R.integer.nbcol))
        binding.rentsRecycleView.adapter = RentAdapter(requireActivity(),rentModel.rents)
        val itemDecor = DividerItemDecoration(requireActivity(),1)
        binding.rentsRecycleView.addItemDecoration(itemDecor)
    }
}