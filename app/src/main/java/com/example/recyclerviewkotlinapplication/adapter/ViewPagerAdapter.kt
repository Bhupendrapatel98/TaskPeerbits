package com.example.recyclerviewkotlinapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.recyclerviewkotlinapplication.R
import com.example.recyclerviewkotlinapplication.data.Post
import com.example.recyclerviewkotlinapplication.databinding.RowDataBinding

class ViewPagerAdapter(context: Context,var postList: List<Post>,viewPager2: ViewPager2) : RecyclerView.Adapter<ViewPagerAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val rowDataBinding: RowDataBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.row_data, parent, false)
        return CategoryViewHolder(rowDataBinding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(postList[position], position)
    }

    override fun getItemCount(): Int = postList.size


    inner class CategoryViewHolder(val rowDataBinding: RowDataBinding) :
        RecyclerView.ViewHolder(rowDataBinding.root) {

        fun bind(post: Post, i: Int) {
            this.rowDataBinding.post = post
            this.rowDataBinding.executePendingBindings()
        }
    }

    fun setPostData(postList: ArrayList<Post>) {
        this.postList = postList
        notifyDataSetChanged()
    }
}