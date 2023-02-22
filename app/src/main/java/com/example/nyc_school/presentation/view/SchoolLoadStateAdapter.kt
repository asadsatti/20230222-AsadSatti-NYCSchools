package com.example.nyc_school.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.nyc_school.databinding.SchoolLoadStateItemBinding

class SchoolLoadStateAdapter(
    private val retryCallback: () -> Unit,
) : LoadStateAdapter<SchoolLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: SchoolLoadStateViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState):
            SchoolLoadStateViewHolder {
        val binding = SchoolLoadStateItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return SchoolLoadStateViewHolder(binding) { retryCallback.invoke() }
    }
}