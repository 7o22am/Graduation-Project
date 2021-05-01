package com.example.myapplication

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [inf_user_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class inf_user_Fragment : Fragment() {
    var myAuth= FirebaseAuth.getInstance()
    val userDataBase= FirebaseFirestore.getInstance().collection("Users");
    val storge =FirebaseStorage.getInstance()


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
        return inflater.inflate(R.layout.fragment_inf_user_, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment inf_user_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            inf_user_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
            activity?.setTitle("Profile ")
        val u_name1 = view?.findViewById(R.id.u_name) as TextView
        val u_phone1 = view?.findViewById(R.id.u_phone) as TextView
        val u_email1 = view?.findViewById(R.id.u_email) as TextView
        val u_level = view?.findViewById(R.id.u_lev) as TextView

        var u: Users? = null
       userDataBase.document(myAuth.currentUser!!.uid.toString()).get().addOnSuccessListener {
            u = it.toObject(Users::class.java)!!
            u_email1.text = u?.gmail
            u_name1.text = u?.name?.toUpperCase()
            u_phone1.text =  u?.phone
           u_level.text =  u?.level

        }
        val myButton = view?.findViewById<FloatingActionButton>(R.id.inf_resit) as FloatingActionButton
        myButton.setOnClickListener {
            myAuth = FirebaseAuth.getInstance()
            myAuth!!.sendPasswordResetEmail(u_email1.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "Email send to gmail ", Toast.LENGTH_LONG).show()
                    }
                }
        }

    }

}