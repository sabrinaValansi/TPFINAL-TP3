package ar.edu.ort.tpfinal_tp3.fragments

import android.accessibilityservice.AccessibilityService.MagnificationController.OnMagnificationChangedListener
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tpfinal_tp3.R
import ar.edu.ort.tpfinal_tp3.adapter.CharacterAdapter
import ar.edu.ort.tpfinal_tp3.listener.OnCharacterClickedListener
import ar.edu.ort.tpfinal_tp3.model.Character
import ar.edu.ort.tpfinal_tp3.model.JsonObject
import ar.edu.ort.tpfinal_tp3.service.CharacterServiceApiBuilder
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment(), OnCharacterClickedListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var recycler : RecyclerView
    private lateinit var characterList : ArrayList<ar.edu.ort.tpfinal_tp3.model.Character>
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var layoutManager : LinearLayoutManager



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

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById<RecyclerView>(R.id.recyclerview)

        layoutManager = LinearLayoutManager(this.context)

        recycler.layoutManager = GridLayoutManager(this.context, 2)

        characterList = ArrayList()

        getCharacter().let {

        }

        recycler.adapter = CharacterAdapter(characterList, this)


    }

    private fun getCharacter() {
        val service = CharacterServiceApiBuilder.create()

        service.getCharacter().enqueue(object : Callback<JsonObject> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {
                if (response.isSuccessful && response.body() != null) {

                  val json = response.body()!!.results

                    characterList.addAll(json)

                    callAdapter(characterList)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("Example ", t.stackTraceToString())
            }
        })
    }

    private fun callAdapter(characterList: ArrayList<Character>) {
        recycler.adapter = CharacterAdapter(characterList, this)

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCharacterSelected(character: Character) {
        findNavController().navigate(HomeDirections.actionHome2ToDetail(character))
    }
}