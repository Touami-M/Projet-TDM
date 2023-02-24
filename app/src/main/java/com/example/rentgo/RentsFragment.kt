package com.example.rentgo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rentgo.databinding.FragmentRentsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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
        binding.progressBar.visibility = View.VISIBLE
        // Get data from the server
        getRents()

    }

    private fun getRents() {
        CoroutineScope(Dispatchers.IO).launch {
            val pref = requireActivity().getSharedPreferences("users", Context.MODE_PRIVATE)
            val data: HashMap<String, Int> = HashMap()
            pref.getInt("idUser", 0)?.let { data.put("iduser", it) }
            val response =  RetrofitService.endpoint.getRents(data)
            withContext(Dispatchers.Main) {
                binding.progressBar.visibility = View.INVISIBLE
                if(response.isSuccessful) {
                    val rents = response.body()
                    if(rents!=null) {
                        rentModel.rents = rents
                        binding.rentsRecycleView.layoutManager = GridLayoutManager(requireActivity(),resources.getInteger(R.integer.nbcol))
                        binding.rentsRecycleView.adapter = RentAdapter(requireActivity(),rentModel.rents)
                        val itemDecor = DividerItemDecoration(requireActivity(),1)
                        binding.rentsRecycleView.addItemDecoration(itemDecor)
                    }
                    else{
                        binding.noRentsTextvie.visibility = View.VISIBLE
                    }
                }
                else {
                    Toast.makeText(requireActivity(),"Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }
}