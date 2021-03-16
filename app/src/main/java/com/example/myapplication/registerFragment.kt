package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [registerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class registerFragment : Fragment() {

    var myAuth= FirebaseAuth.getInstance()
    val userDataBase= FirebaseFirestore.getInstance().collection("Users");

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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment registerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            registerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

   override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       val myButton2 = view?.findViewById<Button>(R.id.back_login) as Button
       myButton2.setOnClickListener(){
           activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment,loguserFragment())?.commitNow()
       }
       val myButton = view?.findViewById<Button>(R.id.regist_user) as Button
           myButton.setOnClickListener {
               val n   = view?.findViewById(R.id.r_name) as TextView
               val e   = view?.findViewById(R.id.r_email) as TextView
               val p   = view?.findViewById(R.id.r_pass) as TextView
               val rp   = view?.findViewById(R.id.r_rpass) as TextView
               val ph   = view?.findViewById(R.id.r_phone) as TextView
               val le   = view?.findViewById(R.id.r_level) as TextView


            val email = e.text.toString()
            val pass = p.text.toString()
            val repass = rp.text.toString()
            val phone = ph.text.toString()
            val name = n.text.toString()
            val level = le.text.toString()
               var q1 :Boolean = false
               var q2 :Boolean = false
               var q3 :Boolean = false
               var q4 :Boolean = false
               if(email.contains("@") && email.contains(".") ){q1=true}
               else {
                   Toast.makeText(context, "INVAILED EMAIL", Toast.LENGTH_SHORT).show()}
               if(pass == repass){q2=true}
               else{
                   Toast.makeText(context, "INVAILED PASSWORED", Toast.LENGTH_SHORT).show()
               }
               if(phone.length >=9 || phone.length <= 12){ q3 =true } else{
                   Toast.makeText(context, "INVAILED PHONE NUMBER", Toast.LENGTH_SHORT).show()
               }
               if(name.isNotEmpty() && name.length>=3){q4=true}
               else
               {
                   Toast.makeText(context, "INVAILED NAME", Toast.LENGTH_SHORT).show()
               }

               if(q4 == true && q1==true && q2 == true && q3 ==true) {

                   myAuth?.createUserWithEmailAndPassword(email, pass)?.addOnCompleteListener {
                       if(it.isSuccessful) {
                           var u: Users = Users()
                           u.gmail = email
                           u.name = name
                           u.phone = phone
                           u.type = "User"
                           u.state = "Online"
                           u.id = myAuth.currentUser!!.uid.toString()
                           u.level = level
                           userDataBase.document(myAuth.currentUser!!.uid.toString()).set(u)
                           Toast.makeText(context, "go verify your account  ", Toast.LENGTH_SHORT).show()
                           sendvverify()
                           activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment,loguserFragment())?.commitNow()
                       }
                       else
                       {
                           Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                           Toast.makeText(context, "chick your internet plz .. ", Toast.LENGTH_LONG).show()
                       }
                   }

               }
        }
    }

    private fun sendvverify()
    {
        var suser = myAuth?.currentUser
        suser?.sendEmailVerification()?.addOnCompleteListener{
            if(it.isSuccessful)
            {
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment,loguserFragment())?.commitNow()
            }
            else
            {
                Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}