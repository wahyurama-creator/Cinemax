package com.way.cinemax.presentation.ui.onboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.way.cinemax.databinding.LayoutItemOnboardBinding
import com.way.cinemax.presentation.ui.onboard.OnboardAdapter.*

class OnboardAdapter() : RecyclerView.Adapter<OnboardViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<OnboardItem>() {
        override fun areItemsTheSame(oldItem: OnboardItem, newItem: OnboardItem): Boolean {
            return oldItem.imageItem == newItem.imageItem
        }

        override fun areContentsTheSame(oldItem: OnboardItem, newItem: OnboardItem): Boolean {
            return oldItem == newItem
        }
    }

    val diffList = AsyncListDiffer(this, callback)

    inner class OnboardViewHolder(private val binding: LayoutItemOnboardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(onboardItem: OnboardItem) {
            binding.ivOnboard.setImageResource(onboardItem.imageItem)
            binding.tvTitleOnboard.setText(onboardItem.title)
            binding.tvDescOnboard.setText(onboardItem.desc)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardViewHolder {
        val binding = LayoutItemOnboardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OnboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardViewHolder, position: Int) {
        holder.bind(diffList.currentList[position])
    }

    override fun getItemCount(): Int = diffList.currentList.size
}