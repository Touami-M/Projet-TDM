package com.example.rentgo

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.rentgo.databinding.FragmentCarDetailsBinding
import com.gtappdevelopers.kotlingfgproject.SliderAdapter
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView


class CarDetailsFragment : Fragment() {
    lateinit var binding: FragmentCarDetailsBinding
    lateinit var sliderView: SliderView
    lateinit var carModel:CarModel
    lateinit var images: Array<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carModel = ViewModelProvider(requireActivity()).get(CarModel::class.java)
        val position = arguments?.getInt("position")
        if (position != null) {
            val car = carModel.cars.get(position)
            images = arrayOf(
                car.carImage,car.carImage,car.carImage,car.carImage,car.carImage,
            )
            binding.apply {
                marque.text = car.marque
                detailsView.text = car.detail
                if(car.availability)
                    availability.text = "Available"
                else {
                    availability.text = "Not Available"
                }
                priceView.text = car.price+"/Day"
            }
        }
        sliderView = binding.slider
        val sliderAdapter = SliderAdapter(images)

        sliderView.setSliderAdapter(sliderAdapter)
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM)
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        sliderView.startAutoCycle()


        binding.backButtonView.setOnClickListener { view: View ->
            view.findNavController().navigate(com.example.rentgo.R.id.action_carDetailsFragment_to_homeFragment)
        }

        binding.extendedFab.setOnClickListener { view: View ->
            view.findNavController().navigate((com.example.rentgo.R.id.action_carDetailsFragment_to_bookingFragment))
        }

    }

}