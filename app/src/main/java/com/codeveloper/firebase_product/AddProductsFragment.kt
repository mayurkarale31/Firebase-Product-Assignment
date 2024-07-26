package com.codeveloper.firebase_product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.codeveloper.firebase_product.databinding.AddProductFragmentBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class AddProductsFragment : Fragment() {

    private lateinit var binding: AddProductFragmentBinding
    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddProductFragmentBinding.inflate(layoutInflater)

        initListener()
        return binding.root
    }

    private fun initListener(){

        binding.btnAddProduct.setOnClickListener{

        val productId = binding.productId.text.toString()
        val productName = binding.productName.text.toString()
        val productPrice = binding.productPrice.text.toString()

        /*val productMap = hashMapOf(
            "productId" to productId,
            "productName" to productName,
            "productPrice" to productPrice
        )*/
            val product = Product(productId, productName, productPrice)

        //val userId = FirebaseAuth.getInstance().currentUser!!.uid

        db.collection("user").document().set(product)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Added Successfully", Toast.LENGTH_SHORT).show()
                binding.productId.text.clear()
                binding.productName.text.clear()
                binding.productPrice.text.clear()

                parentFragmentManager.popBackStack()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed..", Toast.LENGTH_SHORT).show()
            }
        }
    }
}