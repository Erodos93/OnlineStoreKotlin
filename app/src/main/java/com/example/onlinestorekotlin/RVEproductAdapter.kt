package com.example.onlinestorekotlin

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_row.view.*

class RVEproductAdapter(var context: Context, var arrayList: ArrayList<EProducts>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val productView = LayoutInflater.from(context).inflate(R.layout.rv_row, parent, false)
        return ProductViewHolder(productView)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductViewHolder).inicializationUI(
            arrayList.get(position).id,
            arrayList.get(position).name,
            arrayList.get(position).price,
            arrayList.get(position).pictureName
        )
    }

    inner class ProductViewHolder(myView: View) : RecyclerView.ViewHolder(myView) {


        fun inicializationUI(pId: Int, pName: String, p: Int, picName: String) {
            itemView.txtId.text = pId.toString()
            itemView.txtName.text = pName
            itemView.txtPrice.text = p.toString()
            var picUrl = "http://10.1.3.228/OnlineStoreApp/osimages/"
            picUrl = picUrl.replace(" ", "%20")
            Picasso.get().load(picUrl + picName).into(itemView.imgProduct)
            itemView.txtAdd.setOnClickListener {
                 Person.addToCartId = pId
                var amountFragment= AmountFragment()
                var fragmentManager =(itemView.context as Activity).fragmentManager
                amountFragment.show(fragmentManager,"TAG")
            }
        }

    }

}