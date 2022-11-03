package ar.edu.ort.tpfinal_tp3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ar.edu.ort.tpfinal_tp3.R
import com.bumptech.glide.Glide

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Detail.newInstance] factory method to
 * create an instance of this fragment.
 */
class Detail : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var image_detail : ImageView
    private lateinit var status_detail : TextView
    private lateinit var name_detail : TextView
    private lateinit var specie_detail : TextView
    private lateinit var origin_detail : TextView




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
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image_detail=view.findViewById(R.id.character_detail_image)
        specie_detail=view.findViewById(R.id.character_detail_specie)
        name_detail=view.findViewById(R.id.character_detail_name)
        status_detail=view.findViewById(R.id.character_detail_status)

        arguments?.let {
            val character = DetailArgs.fromBundle(it).character
            specie_detail.text=character.species
            name_detail.text=character.name
            status_detail.text=character.status

            Glide.with(this)
                .load(character.image)
                .into(image_detail)


        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Detail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Detail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}