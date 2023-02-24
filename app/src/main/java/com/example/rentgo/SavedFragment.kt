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
import com.example.rentgo.databinding.FragmentSavedBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SavedFragment : Fragment() {
    lateinit var binding: FragmentSavedBinding
    lateinit var savedModel: SavedModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedModel = ViewModelProvider(requireActivity()).get(SavedModel::class.java)
        binding.progressBar.visibility = View.VISIBLE
        getSaved()
    }

    private fun getSaved() {
        CoroutineScope(Dispatchers.IO).launch {
            val pref = requireActivity().getSharedPreferences("users", Context.MODE_PRIVATE)
            val data: HashMap<String, Int> = HashMap()
            pref.getInt("idUser", 0)?.let { data.put("iduser", it) }
            val response =  RetrofitService.endpoint.getSaved(data)
            withContext(Dispatchers.Main) {
                binding.progressBar.visibility = View.INVISIBLE
                if(response.isSuccessful) {
                    val saved = response.body()
                    if(saved!=null) {
                        savedModel.savedcars = saved
                        binding.carsRecycleView.layoutManager = GridLayoutManager(requireActivity(),resources.getInteger(R.integer.nbcol))
                        binding.carsRecycleView.adapter = SavedAdapter(requireActivity(),savedModel.savedcars)
                        val itemDecor = DividerItemDecoration(requireActivity(),1)
                        binding.carsRecycleView.addItemDecoration(itemDecor)
                    }
                    else{
                        binding.noSavedTextvie.visibility = View.VISIBLE
                    }
                }
                else {
                    Toast.makeText(requireActivity(),"Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }
}