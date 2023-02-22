package com.example.nyc_school.presentation.view

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.recyclerview.widget.RecyclerView
import com.example.nyc_school.databinding.SchoolLoadStateItemBinding

class SchoolLoadStateViewHolder(
    private val binding: SchoolLoadStateItemBinding,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private val retry = binding.retryButton
        .also {
            it.setOnClickListener { retryCallback() }
        }

    fun bindTo(loadState: LoadState) {
        binding.run {
            progressBar.isVisible = loadState is Loading
            retry.isVisible = loadState is Error
            errorMsg.isVisible = !(loadState as? Error)?.error?.message.isNullOrBlank()
            errorMsg.text = (loadState as? Error)?.error?.message
        }
    }
}
