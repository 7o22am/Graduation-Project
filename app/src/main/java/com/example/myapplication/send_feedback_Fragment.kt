package com.example.myapplication

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [send_feedback_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class send_feedback_Fragment : Fragment() {
   // var mAuth : FirebaseAuth?=null
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
        return inflater.inflate(R.layout.fragment_send_feedback_, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment send_feedback_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                send_feedback_Fragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.setTitle("Feedback")
        val myButton = view?.findViewById<FloatingActionButton>(R.id.send_feedback) as FloatingActionButton
        val f = view?.findViewById<EditText>(R.id.feedback) as EditText
        val e = view?.findViewById<EditText>(R.id.email_feedback) as EditText



            myButton.setOnClickListener {
                val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val netinf =cm.activeNetworkInfo
                if (netinf!=null && netinf.isConnected) {
                val s: String = f.text.toString()
                val s2: String = e.text.toString()
                val database = Firebase.database
                val myRef = database.getReference("Feedback")
                if (s.length > 3) {
                    myRef.push().setValue(s2 + ">>" + s)
                    Toast.makeText(context, "Send", Toast.LENGTH_LONG).show()
                    activity?.setTitle("schedule")
                    activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, homeFragment())?.commitNow()
                } else {
                    Toast.makeText(context, "Nothing to Send", Toast.LENGTH_LONG).show()
                }
            }else
                {
                    Toast.makeText(context, "No INTERNET ..  ", Toast.LENGTH_LONG).show()
                }
        }

    }


}