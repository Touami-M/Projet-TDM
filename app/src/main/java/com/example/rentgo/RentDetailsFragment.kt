package com.example.rentgo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rentgo.databinding.FragmentCarDetailsBinding
import com.example.rentgo.databinding.FragmentRentDetailsBinding
import com.gtappdevelopers.kotlingfgproject.SliderAdapter
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView


class RentDetailsFragment : Fragment() {
    lateinit var binding: FragmentRentDetailsBinding
    lateinit var sliderView: SliderView
    lateinit var carModel:CarModel
    lateinit var specificationModel:SpecificationModel
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

        carModel = ViewModelProvider(requireActivity()).get(CarModel::class.java)
        val position = arguments?.getInt("position")
        if (position != null) {
            val car = carModel.cars.get(position)
            images = arrayOf(
                car.carImage,car.carImage,car.carImage,car.carImage,car.carImage,
            )
            binding.apply {
                marque.text = car.marque
            }
        }
        sliderView = binding.slider
        val sliderAdapter = SliderAdapter(images)

        sliderView.setSliderAdapter(sliderAdapter)
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM)
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        sliderView.startAutoCycle()

        binding.backButtonView.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_rentDetailsFragment_to_rentsFragment)
        }

    }
}