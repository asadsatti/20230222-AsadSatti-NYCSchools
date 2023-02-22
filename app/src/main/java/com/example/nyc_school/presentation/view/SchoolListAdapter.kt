package com.example.nyc_school.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.nyc_school.data.db.School
import com.example.nyc_school.databinding.SchoolItemBinding

class SchoolListAdapter(private val onClick: (School) -> Unit)
    : PagingDataAdapter<School, SchoolListViewHolder>(SCHOOL_COMPARATOR) {

    override fun onBindViewHolder(holder: SchoolListViewHolder, position: Int) {
        getItem(position)?.run {
            holder.bind(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolListViewHolder {
        val binding = SchoolItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)

        return SchoolListViewHolder(binding) {
            getItem(it)?.run {
                onClick(this)
            }
        }
    }

    companion object {
        val SCHOOL_COMPARATOR = object : DiffUtil.ItemCallback<School>() {
            override fun areContentsTheSame(oldItem: School, newItem: School): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: School, newItem: School): Boolean =
                    oldItem.schoolName == newItem.schoolName
        }
    }
}
