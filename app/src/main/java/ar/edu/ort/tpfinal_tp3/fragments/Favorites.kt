package ar.edu.ort.tpfinal_tp3.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tpfinal_tp3.R
import ar.edu.ort.tpfinal_tp3.adapter.CharacterAdapter
import ar.edu.ort.tpfinal_tp3.listener.OnCharacterClickedListener
import ar.edu.ort.tpfinal_tp3.model.Character
import ar.edu.ort.tpfinal_tp3.repository.CharacterRepository
import ar.edu.ort.tpfinal_tp3.utils.UserSession
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var message : TextView
private lateinit var recycler : RecyclerView
private lateinit var layoutManager : LinearLayoutManager
private lateinit var characterList : ArrayList<ar.edu.ort.tpfinal_tp3.model.Character>
private lateinit var characterRepository : CharacterRepository

private lateinit var adapter : CharacterAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [Favorites.newInstance] factory method to
 * create an instance of this fragment.
 */
class Favorites : Fragment(), OnCharacterClickedListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        message = view.findViewById(R.id.welcome_message)

        message.text = "Hola ${UserSession.userName}, estos son tus \n personajes favoritos"

        context?.let { characterRepository = CharacterRepository.getInstance(it) }

        recycler = view.findViewById(R.id.recycler_favorites)

        lifecycleScope.launch {
            adapter = CharacterAdapter(characterRepository.getAllCharacters(), this@Favorites)
            val layoutManager = GridLayoutManager(context, 2)

            recycler.layoutManager = layoutManager
            recycler.adapter = adapter
        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Favorites.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Favorites().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCharacterSelected(character: Character) {
        findNavController().navigate(FavoritesDirections.actionFavoritesToDetail(character, false))
    }
}