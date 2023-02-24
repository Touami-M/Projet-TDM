package com.example.rentgo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.rentgo.databinding.FragmentCarDetailsBinding
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView


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
                val marque_model = car.marque +" "+car.model
                marque.text = marque_model
                detailsView.text = car.description
                if(car.dispo==1) {
                    availability.text = "Available"
                    imageView6.setImageResource(R.drawable.available_true)
                }else {
                    availability.text = "Not Available"
                    imageView6.setImageResource(R.drawable.available_false)
                }
                priceView.text = car.prix+"/Day"
                getViewById(colorValueView).text = car.couleur
                getViewById(powerValueView).text = car.puissance
                getViewById(yearValueView).text = car.année.toString()
                getViewById(seatsValueView).text = car.seats.toString()
                getViewById(classValueView).text = car.classe
                getViewById(boxValueView).text = car.gearbox
            }
            sliderView = binding.slider
            val sliderAdapter = SliderAdapter(this.imageUrl)

            sliderView.setSliderAdapter(sliderAdapter)
            sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM)
            sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
            sliderView.startAutoCycle()


            binding.locationButtonView.setOnClickListener{
                var latitude = carModel.cars[position].lat
                var longitude = carModel.cars[position].longitude
                val place = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude&z=10")
                val intent = Intent(Intent.ACTION_VIEW, place)
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent)
            }
        }



        binding.backButtonView.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_carDetailsFragment_to_homeFragment)
        }

        binding.extendedFab.setOnClickListener { view: View ->
            if (carModel.cars[position!!].dispo==1){
                val data = bundleOf("idcar" to carModel.cars[position!!].id)
                view.findNavController().navigate(R.id.action_carDetailsFragment_to_bookingFragment,data)
            }
            else{
                Toast.makeText(context,"This car isn't available, you can not rent it!!",Toast.LENGTH_LONG).show()
                binding.extendedFab.isEnabled  = false
            }
        }

    }

}

private fun getViewById(textView: TextView): TextView {
    return textView
}
