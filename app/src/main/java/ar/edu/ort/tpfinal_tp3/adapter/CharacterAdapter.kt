package ar.edu.ort.tpfinal_tp3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tpfinal_tp3.R
import ar.edu.ort.tpfinal_tp3.listener.OnCharacterClickedListener

class CharacterAdapter (
    private val characterList : MutableList<ar.edu.ort.tpfinal_tp3.model.Character>,
    private val onCharacterClickedListener: OnCharacterClickedListener
) : RecyclerView.Adapter<CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList[position]

        holder.bind(character)

        holder.itemView.setOnClickListener{
            onCharacterClickedListener.onCharacterSelected(character)
        }
    }

    fun updateCharacter(filteredCharacters : ArrayList<ar.edu.ort.tpfinal_tp3.model.Character>) {
        characterList.clear()
        characterList.addAll(filteredCharacters)
        this.notifyDataSetChanged()
    }

    override fun getItemCount() = characterList.size

}