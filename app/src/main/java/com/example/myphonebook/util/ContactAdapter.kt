package com.example.myphonebook.util

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myphonebook.R
import com.example.myphonebook.data.datamodel.Contact

//der Adapter brqucht den Context, um auf string Resourcen zuzugreifen
//und die Liste an Kontakten, um sie für den RecyclerView vorzubereiten
class ContactAdapter(): RecyclerView.Adapter<ContactAdapter.ItemViewHolder>() {

    private var dataset = listOf<Contact>()

    //der ViewHolder weiß welche Teile des LAyouts beim Recycling angepasst werde
    class ItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val contactFullName: TextView = view.findViewById(R.id.item_fullName_tv)
        val contactRow: ConstraintLayout = view.findViewById(R.id.item_constraintLayout)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<Contact>) {
        dataset = newList
        notifyDataSetChanged()
    }

    //hier werden neue ViewHolder erstellt
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        //das itemlayout wird gebaut
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)

        //und in einem ViewHolder zurückgegeben
        return ItemViewHolder(adapterLayout)
    }

    //hier findet der Recyclingprozess statt
    //die vom ViewHolder bereitgestellten Parameter werden verändert
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        //Vor- und Zuname zusammen setzen
        var fullName = (item.firstName + " " + item.lastName).toString()
        holder.contactFullName.text = fullName
        //holder.contactFullName.text = item.firstName                            //todo vor und zunme zusammensetzen

        holder.contactRow.setOnClickListener {
            holder.itemView.findNavController()
            //   .navigate()                                   TODO navigieren zum editfragment
        }
    }

    //damit der LayoutManager weiß wie lang die Liste ist
    override fun getItemCount(): Int {
        return dataset.size
    }
}