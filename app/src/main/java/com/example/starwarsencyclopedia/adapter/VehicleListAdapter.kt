package com.example.starwarsencyclopedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsencyclopedia.adapter.data.DataSource
import com.example.starwarsencyclopedia.databinding.VehicleViewItemBinding

class VehicleListAdapter(private val clickListener: VehicleListener) :
    ListAdapter<Int, VehicleListAdapter.VehicleViewHolder>(DiffCallback) {

    class VehicleViewHolder(
        private var binding: VehicleViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: VehicleListener, vehicleId: Int, vehicleName: String) {
            binding.clickListener = clickListener
            binding.vehicleId = vehicleId
            binding.vehicleName = vehicleName
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(
            oldItem: Int, newItem: Int): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: Int, newItem: Int): Boolean = false
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): VehicleViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        return VehicleViewHolder(
            VehicleViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicleId = getItem(position)
        val vehicleName = DataSource.vehicleNames[vehicleId]
        holder.bind(clickListener, vehicleId, vehicleName!!)
    }
}

class VehicleListener(val clickListener: (vehicleId: Int) -> Unit) {
    fun onClick(vehicleId: Int) = clickListener(vehicleId)
}
