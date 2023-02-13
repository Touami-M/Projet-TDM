package com.example.rentgo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.rentgo.databinding.FragmentCarDetailsBinding
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import java.util.List


class CarDetailsFragment : Fragment() {
    lateinit var binding: FragmentCarDetailsBinding
    lateinit var sliderView: SliderView
    lateinit var carModel:CarModel
    lateinit var imageUrl: ArrayList<String>

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

        carModel = ViewModelProvider(requireActivity())[CarModel::class.java]
        val position = arguments?.getInt("position")
        if (position != null) {
            val car = carModel.cars[position]
            imageUrl = ArrayList(listOf(car.photo1,car.photo2,car.photo3))

            binding.apply {
                marque.text = car.marque
                detailsView.text = car.description
                if(car.dispo==1)
                    availability.text = "Available"
                else {
                    availability.text = "Not Available"
                }
                priceView.text = car.prix+"/Day"
                cardView1.getViewById(colorValueView).text = car.couleur
                cardView2.getViewById(powerValueView).text = car.puissance
                cardView3.getViewById(yearValueView).text = car.année.toString()
                cardView4.getViewById(seatsValueView).text = car.seats.toString()
                cardView5.getViewById(classValueView).text = car.classe
                cardView6.getViewById(boxValueView).text = car.gearbox
            }
            sliderView = binding.slider
            val sliderAdapter = SliderAdapter(this.imageUrl)

            sliderView.setSliderAdapter(sliderAdapter)
            sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM)
            sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
            sliderView.startAutoCycle()
        }



        binding.backButtonView.setOnClickListener { view: View ->
            view.findNavController().navigate(com.example.rentgo.R.id.action_carDetailsFragment_to_homeFragment)
        }

        binding.extendedFab.setOnClickListener { view: View ->
            view.findNavController().navigate((com.example.rentgo.R.id.action_carDetailsFragment_to_bookingFragment))
        }

    }

}

private fun ConstraintLayout.getViewById(textView: TextView): TextView {
    return textView
}
