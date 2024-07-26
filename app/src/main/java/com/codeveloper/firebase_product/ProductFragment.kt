package com.codeveloper.firebase_product

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeveloper.firebase_product.databinding.ProductFragmentBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class ProductFragment : Fragment() {

    private lateinit var binding: ProductFragmentBinding
    private lateinit var productAdapter : ProductAdapter
    private lateinit var products : ArrayList<Product>
    private var db = Firebase.firestore

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductFragmentBinding.inflate(layoutInflater)

        products = ArrayList()

        initViews()

        db = FirebaseFirestore.getInstance()

        db.collection("user").get().addOnSuccessListener {
            if(!it.isEmpty){
                for(data in it.documents){
                    val product : Product? = data.toObject(Product::class.java)
                    if(product != null){
                        products.add(product)
                    }
                }
                productAdapter.notifyDataSetChanged()
            }
        }
            .addOnFailureListener {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }

        initAdapter()
        initListeners()
        return binding.root
    }

    private fun initListeners(){
        binding.btnAddProduct.setOnClickListener{
            val addProductsFragment = AddProductsFragment()

            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer, addProductsFragment, null)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initAdapter(){
        productAdapter = ProductAdapter(products)
        binding.recyclerProducts.adapter = productAdapter
    }

    private fun initViews(){
        binding.recyclerProducts.layoutManager =
            LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,
                false)
    }
}