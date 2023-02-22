package com.example.nyc_school.presentation.view

import androidx.recyclerview.widget.RecyclerView
import com.example.nyc_school.R
import com.example.nyc_school.data.db.School
import com.example.nyc_school.databinding.SchoolItemBinding

class SchoolListViewHolder(private val binding: SchoolItemBinding,
                           onClick: (Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            onClick(absoluteAdapterPosition)
        }
    }

    fun bind(item: School) {
        binding.run {
            schoolName.text = item.schoolName ?:
                    itemView.context.getString(R.string.message_loading)
            city.text = item.city
            neighborhood.text = item.neighborhood ?:
            itemView.context.getString(R.string.message_unknown)
        }
    }
}