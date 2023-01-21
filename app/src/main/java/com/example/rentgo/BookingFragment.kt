package com.example.rentgo

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.rentgo.databinding.FragmentBookingBinding
import java.text.SimpleDateFormat
import java.util.*


class BookingFragment : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextDate = binding.startTimetextView
        val editTextTime = binding.endTimetextView

        editTextDate.inputType = InputType.TYPE_NULL
        editTextTime.inputType = InputType.TYPE_NULL

        editTextDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val picker =  DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,day)
                editTextDate.setText(SimpleDateFormat("dd/MM/yyyy").format(cal.time))
            }

            this.context?.let { it1 ->
                DatePickerDialog(
                    it1, picker, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(
                        Calendar.DAY_OF_MONTH)).show()
            }

        }

        // TIme
        editTextTime.setOnClickListener {
            val cal = Calendar.getInstance()
            val picker =  DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,day)
                editTextDate.setText(SimpleDateFormat("dd/MM/yyyy").format(cal.time))
            }

            this.context?.let { it1 ->
                DatePickerDialog(
                    it1, picker, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(
                        Calendar.DAY_OF_MONTH)).show()
            }
        }

        binding.reserveButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_bookingFragment_to_rentsFragment)
        }

    }


}