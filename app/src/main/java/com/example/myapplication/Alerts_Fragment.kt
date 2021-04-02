package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Alerts_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Alerts_Fragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_alerts_, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Alerts_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Alerts_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.setTitle("Alerts")
        val database = Firebase.database
        val myRef = database.getReference("Noitifaction")
        val rec = view?.findViewById<ListView>(R.id.list) as ListView
      try {
          myRef.addValueEventListener(object : ValueEventListener {
              override fun onDataChange(dataSnapshot: DataSnapshot) {
                  val myarray = arrayListOf<String>()
                  for (i in dataSnapshot.children) {
                      var v = i.getValue(String::class.java)
                      myarray.add(v.toString())
                  }
                  myarray.reverse()

                  val myadp = ArrayAdapter(context!!, android.R.layout.simple_list_item_1
                      , myarray)
                  rec.adapter = myadp

              }

              override fun onCancelled(error: DatabaseError) {
                  Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
              }

          })
      }
      catch (ex:Exception)
      {
          Toast.makeText(context, "load .. ", Toast.LENGTH_SHORT).show()
      }


    }

}