package com.example.rentgo

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Typeface
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.rentgo.databinding.FragmentSignupBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


@Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
class SignupFrag : Fragment() {

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    lateinit var binding: FragmentSignupBinding
    lateinit var imageBitmap:Bitmap
    lateinit var phone_pass:HashMap<String,String>
    lateinit var image: MultipartBody.Part
    lateinit var userBody: MultipartBody.Part
    val requestCode = 400



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.textView12).setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_signupFrag_to_loginFrag)
        }

        var cpt = 0
        binding.eyesignup.setOnClickListener{
            if(cpt==0)
            {
                cpt = 1
                binding.eyesignup.setBackgroundResource(R.drawable.eye_off)
                binding.passwdsignup.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
            else
            {
                cpt = 0
                binding.eyesignup.setBackgroundResource(R.drawable.password_eye)
                binding.passwdsignup.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val intent = result.data
            if (result.resultCode == RESULT_OK && intent != null) {
                imageBitmap = intent.extras?.get("data") as Bitmap
                //val resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, 300, 300, true)
                binding.imageView.setImageBitmap(imageBitmap)
                binding.uploadimageView.text = "Driving License uploaded"
                binding.uploadimageView.setTypeface(null, Typeface.BOLD)
            }
        }

        binding.uploadimageView.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)  {
                openCameraIntent()

            }
            else {
                checkPermission()
            }
        }

        binding.button2.setOnClickListener {
            val telephone = binding.editTextPhone.text.toString().toInt()
            val password = binding.passwdsignup.text.toString()
            val name = binding.name.text.toString()
            val credit_card_num = binding.editTextCreditCard.text.toString()
            val progresbar = binding.progressBar
            progresbar.visibility = View.VISIBLE

            val data: HashMap<String, String> = HashMap()
            data.put("telephone", telephone.toString())

            //CHECK USER BEFORE PASSING TO REGISTER

            data.put("password",password)
            phone_pass = data
            data.put("name",name)
            data.put("credit_card_num", credit_card_num)

            binding.button2.isEnabled  = false
            // convert Bitmap to File
            val filesDir = requireContext().getFilesDir()
            val file = File(filesDir, "image" + ".png")
            val bos = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
            val bitmapdata = bos.toByteArray()
            val fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
            image = MultipartBody.Part.createFormData("image", file.getName(), reqFile)
            userBody =  MultipartBody.Part.createFormData("user", Gson().toJson(data))
            binding.progressBar.visibility = View.VISIBLE
            checkuser(phone_pass)
        }

    }

    private fun checkuser(data: HashMap<String, String>) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =  RetrofitService.endpoint.verifyUser(data)
            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    val user = response.body()
                    if(user!=null) {
                        Toast.makeText(requireActivity(),"Cet utilisateur existe deja, Log IN",Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.button2.isEnabled = true
                    }
                    else {
                        //Toast.makeText(requireActivity(),"Cet utilisateur n'existe pas deja, Sign UP",Toast.LENGTH_SHORT).show()
                        signup(image,userBody)
                    }
                }
                else {
                    Toast.makeText(requireActivity(),"An error has occurred",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun openCameraIntent() {
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        activityResultLauncher.launch(pictureIntent)
    }




    // Request permission

    private fun checkPermission() {
        val perms = arrayOf(Manifest.permission.CAMERA)

        ActivityCompat.requestPermissions(requireActivity(),perms, requestCode)

    }

    override fun onRequestPermissionsResult(permsRequestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(permsRequestCode, permissions, grantResults)
        if (permsRequestCode== requestCode && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCameraIntent()
        }
    }

    private fun signup(image: MultipartBody.Part, data: MultipartBody.Part) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =  RetrofitService.endpoint.createUser(image,data)
            withContext(Dispatchers.Main) {
                binding.progressBar.visibility = View.INVISIBLE
                if(response.isSuccessful) {
                    val response = response.body()
                    if(response==null) {
                        Toast.makeText(requireActivity(),"Account created succefully, yoou can now login",Toast.LENGTH_SHORT).show()
                        //view?.findNavController()?.navigate(R.id.action_signupFrag_to_loginFrag)
                        login(phone_pass)
                    }
                    else {
                        Toast.makeText(requireActivity(),"Wrong information, retry",Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    Toast.makeText(requireActivity(),"An error has occurred",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun login(data: HashMap<String,String>) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =  RetrofitService.endpoint.getUserMap(data)
            withContext(Dispatchers.Main) {
                binding.progressBar.visibility = View.INVISIBLE
                if(response.isSuccessful) {
                    val user = response.body()
                    if(user!=null) {
                        val pref = requireActivity().getSharedPreferences("users",Context.MODE_PRIVATE)
                        pref.edit {
                            putInt("idUser",user.telephone)
                            putBoolean("connected",true)
                        }
                        val intent = Intent(requireActivity(),MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }
                    else {
                        Toast.makeText(requireActivity(),"Wrong email or password",Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    Toast.makeText(requireActivity(),"An error has occurred",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}





