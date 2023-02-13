package com.example.rentgo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.rentgo.databinding.FragmentRentDetailsBinding
import com.smarteist.autoimageslider.SliderView


class RentDetailsFragment : Fragment() {
    lateinit var binding: FragmentRentDetailsBinding
    lateinit var sliderView: SliderView
    lateinit var rentModel:RentModel
    lateinit var images: Array<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rentModel = ViewModelProvider(requireActivity()).get(RentModel::class.java)
        val position = arguments?.getInt("position")
        if (position != null) {
            val rent = rentModel.rents.get(position)
            images = arrayOf(
                rent.photo1,rent.photo2,rent.photo3,
            )
            binding.apply {
                marque.text = rent.marque + rent.model
                startTimetextView.text = rent.dateres
                endTimetextView.text = rent.dateret
                costView.text = rent.cost.toString()
                codePinView.text = rent.code_pin.toString()
            }
        }


        /*sliderView = binding.slider
        val sliderAdapter = SliderAdapter(images)

        sliderView.setSliderAdapter(sliderAdapter)
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM)
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        sliderView.startAutoCycle()*/

        binding.backButtonView.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_rentDetailsFragment_to_rentsFragment)
        }

    }
}