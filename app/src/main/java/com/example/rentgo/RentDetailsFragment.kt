package com.example.rentgo

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.rentgo.databinding.FragmentRentDetailsBinding
import com.psp.bluetoothlibrary.Connection
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import java.util.*


class RentDetailsFragment : Fragment() {
    lateinit var binding: FragmentRentDetailsBinding
    lateinit var sliderView: SliderView
    lateinit var rentModel:RentModel
    lateinit var imageUrl: ArrayList<String>


    private val tag1 = "bluetooth"
    private lateinit var connection: Connection


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rentModel = ViewModelProvider(requireActivity()).get(RentModel::class.java)
        val position = arguments?.getInt("position")
        if (position != null) {
            val rent = rentModel.rents.get(position)
            imageUrl = ArrayList(listOf(rent.photo1,rent.photo2,rent.photo3))
            val marque_model = rent.marque +" "+ rent.model
            binding.apply {
                marque.text = marque_model
                startTimetextView.text = rent.dateres
                endTimetextView.text = rent.dateret
                costView.text = rent.cost.toString()
                codePinView.text = rent.code_pin.toString()
            }
            binding.locationButtonView.setOnClickListener{
                var latitude = rent.lat
                var longitude = rent.longitude
                val place = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude&z=10")
                val intent = Intent(Intent.ACTION_VIEW, place)
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent)
            }
        }


        sliderView = binding.slider
        val sliderAdapter = SliderAdapter(imageUrl)

        sliderView.setSliderAdapter(sliderAdapter)
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM)
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        sliderView.startAutoCycle()


        binding.backButtonView.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_rentDetailsFragment_to_rentsFragment)
        }

        //Partie bluetooth qui n'a pas marche mais on a essaie

        /*val  bluetooth =   Bluetooth(context)
        bluetooth.turnOnWithPermission(requireActivity())

        //logMsg("initialize connection object")
        connection =  Connection(context)

        // set UUID ( optional )
        //connection.setUUID(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"))

        getDeviceAddressAndConnect()*/
        binding.lock.setOnClickListener {
                /*val msg = "Hello from app one"
                if (connection.send(msg)) {
                    logMsg("[TX] $msg")
                    Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
                } else {
                    logMsg("[TX] $msg")
                    Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
                }*/
        }
    }

    /*override fun onStart() {
        super.onStart()
        if (connection.isConnected) {
            logMsg("initialize receive listener")
            connection.setOnReceiveListener(receiveListener)
        }
        logMsg("onStart")
    }

    override fun onDestroy() {
        super.onDestroy()
        logMsg("onDestroy")
        disconnect()
    }
    private val receiveListener =
        BluetoothListener.onReceiveListener { receivedData ->
            logMsg("[RX] $receivedData")

        }

    private fun disconnect() {

        connection.disconnect()
        logMsg("Disconnect manual")

    }

    private fun getDeviceAddressAndConnect() {
        // create dialog box
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Select device")
        val modeList = ListView(context)
        val listPaired: ArrayList<String> = ArrayList()
        getPairedDevices(listPaired) // get paired devices
        val modeAdapter =
            context?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    listPaired
                )
            }
        modeList.adapter = modeAdapter
        builder.setView(modeList)
        val dialog: Dialog = builder.create()
        dialog.show()
        modeList.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val device: List<String> = listPaired[position].split("\n")

                // Connect Bluetooth Device --- device[1] = device mac address
                if (connection.connect(device[1], true, connectionListener, receiveListener)) {
                    logMsg("Start connection process")
                } else {
                    logMsg("Start connection process failed")
                }
                dialog.dismiss()
            }
    }

    @SuppressLint("MissingPermission")
    private fun getPairedDevices(list: ArrayList<String>) {
        // initialize bluetooth object
        val bluetooth = Bluetooth(context)
        val deviceList = bluetooth.pairedDevices
        if (deviceList.size  > 0) {
            for (device in deviceList) {
                list.add(
                    """
                    ${device.name}
                    ${device.address}
                    """.trimIndent()
                )
                Log.d(tag1, "Paired device is " + device.name)
            }
        }
    }

    private val connectionListener: BluetoothListener.onConnectionListener = object :
        BluetoothListener.onConnectionListener {
        override fun onConnectionStateChanged(socket: BluetoothSocket?, state: Int) {
            when (state) {
                Connection.CONNECTING -> {
                    logMsg("Connecting...")
                }
                Connection.CONNECTED -> {
                    logMsg("Connected")
                }
                Connection.DISCONNECTED -> {
                    logMsg("Disconnected")

                    disconnect()
                }
            }
        }

        override fun onConnectionFailed(errorCode: Int) {
            when (errorCode) {
                Connection.SOCKET_NOT_FOUND -> {
                    logMsg("Socket not found")

                }
                Connection.CONNECT_FAILED -> {
                    logMsg("Connect Failed")

                }
            }
            disconnect()
        }}

    private fun logMsg(msg: String) {
        Log.d(tag1, msg)
    }*/


}