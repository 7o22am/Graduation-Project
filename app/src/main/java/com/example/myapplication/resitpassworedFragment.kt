package com.example.myapplication

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [resitpassworedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class resitpassworedFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_resitpasswored, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment resitpassworedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                resitpassworedFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
    private var mAuth: FirebaseAuth? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val myButton = view?.findViewById<Button>(R.id.resit_bt) as Button
        myButton.setOnClickListener()
        {
            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netinf = cm.activeNetworkInfo
            if (netinf != null && netinf.isConnected) {
                val e = view?.findViewById<EditText>(R.id.resit_email) as EditText
                val s: String = e.text.toString()
                if (s.contains("@") && s.contains(".")) {
                    mAuth = FirebaseAuth.getInstance()
                    mAuth!!.sendPasswordResetEmail(e.text.toString())
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Email send to gmail ", Toast.LENGTH_LONG)
                                    .show()
                                activity?.supportFragmentManager?.beginTransaction()
                                    ?.replace(R.id.fragment, loguserFragment())?.commitNow()
                            } else {
                                Toast.makeText(context, "email not found", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                } else {
                    Toast.makeText(context, "invailed email ", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(context, "NO INTERNET ..", Toast.LENGTH_LONG).show()
            }
        }

    }
}