package com.codeveloper.firebase_product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeveloper.firebase_product.databinding.ProductViewBinding

class ProductAdapter(
    private val products : ArrayList<Product>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private lateinit var binding : ProductViewBinding

    inner class ProductViewHolder(view : View) : RecyclerView.ViewHolder(view){

        init {
            binding = ProductViewBinding.bind(view)
        }
    }

    override fun getItemCount() = products.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_view, null)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        binding.productId.text = products[position].productId
        binding.productName.text = products[position].productName
        binding.productPrice.text = products[position].productPrice
    }
}