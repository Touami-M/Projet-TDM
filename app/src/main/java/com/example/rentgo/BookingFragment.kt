package com.example.rentgo

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.rentgo.databinding.FragmentBookingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*


class BookingFragment : Fragment() {

    private lateinit var carModel: CarModel
    private lateinit var binding: FragmentBookingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idcar = arguments?.getInt("idcar")
        carModel = ViewModelProvider(requireActivity())[CarModel::class.java]
        val car = carModel.cars[idcar!!]
        val cost = car.prix
        binding.textView20.text = cost +" DA"

        binding.startTimetextView.inputType = InputType.TYPE_NULL
        binding.endTimetextView.inputType = InputType.TYPE_NULL

        // start date
        binding.startTimetextView.setOnClickListener {
            val cal = Calendar.getInstance()
            val picker =  DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,day)
                binding.startTimetextView.setText(SimpleDateFormat("dd/MM/yyyy").format(cal.time))
            }

            this.context?.let { it1 ->
                DatePickerDialog(
                    it1, picker, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(
                        Calendar.DAY_OF_MONTH)).show()
            }

        }

        // end date
        binding.endTimetextView.setOnClickListener {
            val cal = Calendar.getInstance()
            val picker =  DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,day)
                binding.endTimetextView.setText(SimpleDateFormat("dd/MM/yyyy").format(cal.time))


                //update the cost
                val dateres = binding.startTimetextView.text.toString()
                val dateret = binding.endTimetextView.text.toString()

                val dateFormatter: DateTimeFormatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy")

                val from = LocalDate.parse(dateres, dateFormatter)
                val to = LocalDate.parse(dateret, dateFormatter)

                val period = Period.between(from, to)
                var days = period.days
                if(days==0){
                    days=1
                }
                binding.textView20.text = (cost.toDouble() * days).toString() +" DA"
            }

            this.context?.let { it1 ->
                DatePickerDialog(
                    it1, picker, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(
                        Calendar.DAY_OF_MONTH)).show()
            }

        }






        binding.reserveButton.setOnClickListener {
            //val idcar = arguments?.getInt("idcar")
            val dateres = binding.startTimetextView.text.toString()
            val dateret = binding.endTimetextView.text.toString()
            val cost = binding.textView20.text.toString()
            val pref = requireActivity().getSharedPreferences("users", Context.MODE_PRIVATE)
            val iduser = pref.getInt("idUser",0).toString()
            val code_pin = generatePin()
            val progresbar = binding.progressBar
            progresbar.visibility = View.VISIBLE
            val data: HashMap<String, String> = HashMap()
            data.put("iduser",iduser)
            data.put("idcar",idcar.toString())
            data.put("dateres",dateres)
            data.put("dateret", dateret)
            data.put("cost", cost)
            data.put("code_pin", code_pin)
            createReservation(data)
        }

    }

    private fun generatePin(): String {
        val random = (1000..9999).random()
        return random.toString()
    }

    private fun createReservation(data: HashMap<String, String>) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitService.endpoint.createReservation(data)
            withContext(Dispatchers.Main) {
                binding.progressBar.visibility = View.INVISIBLE
                if (response.isSuccessful) {
                    val response = response.body()
                    if(response==null) {
                        val idcar = arguments?.getInt("idcar",0)
                        val idcar_map: HashMap<String, Int> = HashMap()
                        idcar?.let { idcar_map.put("id", it) }
                        rendreRes(idcar_map)
                        Toast.makeText(requireActivity(),"Reservation created succefully",Toast.LENGTH_SHORT).show()
                        view?.findNavController()?.navigate(R.id.action_bookingFragment_to_rentsFragment)
                    }
                    else {
                        Toast.makeText(requireActivity(),"Wrong information, retry",Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireActivity(), "An error has occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun rendreRes(idcar: HashMap<String, Int>) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =  RetrofitService.endpoint.rendreRes(idcar)
            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    val answer = response.body()
                    if(answer==null) {
                        Toast.makeText(requireActivity(),"Availability is updated",Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(requireActivity(),"Availability is not updated",Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    Toast.makeText(requireActivity(),"An error has occurred",Toast.LENGTH_SHORT).show()
                }
            }
        }    }


}