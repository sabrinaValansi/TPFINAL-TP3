package ar.edu.ort.tpfinal_tp3.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tpfinal_tp3.R
import com.bumptech.glide.Glide

class CharacterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val name : TextView
    private val status : TextView
    private val image : ImageView

    init {
        name = itemView.findViewById(R.id.character_name)
        status = itemView.findViewById(R.id.character_status)
        image = itemView.findViewById(R.id.character_image)
    }

    fun bind(character: ar.edu.ort.tpfinal_tp3.model.Character) {
        name.text = character.name
        status.text = character.status

        Glide.with(itemView)
            .load(character.image)
            .into(image)
    }
}