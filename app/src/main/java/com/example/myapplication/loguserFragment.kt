package com.example.myapplication

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.style.TextAppearanceSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseCommonRegistrar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.sql.DatabaseMetaData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [loguserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class loguserFragment : Fragment() {
    var mAuth : FirebaseAuth?=null

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
        return inflater.inflate(R.layout.fragment_loguser, container, false)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment loguserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            loguserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    var sta : String ="false"
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val myButton = view?.findViewById<Button>(R.id.login_user) as Button

        val e   = view?.findViewById(R.id.log_email) as TextView
        val p   = view?.findViewById(R.id.pass_email) as TextView
        myButton.setOnClickListener(){

            mAuth = FirebaseAuth.getInstance()
            val email = e.text.toString()
            val pass = p.text.toString()
            if(email.isNotEmpty() && pass.isNotEmpty())
            {
                mAuth?.signInWithEmailAndPassword(email,pass)?.addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                      Toast.makeText(context, "wellcome !!", Toast.LENGTH_SHORT).show()
                        verify()
                    }
                    else { Toast.makeText(context, "INVALID EMAIL OR PASSWORD", Toast.LENGTH_SHORT).show() }
                }
            }
            else { Toast.makeText(context, "Empity Filed", Toast.LENGTH_SHORT).show() }
        }
        val myButton2 = view?.findViewById<Button>(R.id.back_regist) as Button
        myButton2.setOnClickListener(){ activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment,registerFragment())?.commitNow() }
        val cb_single =  view?.findViewById<CheckBox>(R.id.remimber) as CheckBox
        cb_single.setOnClickListener(View.OnClickListener {
            if (cb_single.isChecked ) {
                sta ="ture"
            }
            else{
                sta= "false"
            }
        })
      val myButton5 = view?.findViewById<Button>(R.id.forgot_pass) as Button
        myButton5.setOnClickListener(){
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment,resitpassworedFragment())?.commitNow()

        }
    }

    private fun verify()
    {
        val vuser = mAuth?.currentUser
        if(vuser!!.isEmailVerified)
        {
            startActivity(Intent(context,MainActivity::class.java))
            if(sta == "ture")
            {
                var share: SharedPreferences? = null
                share = context?.getSharedPreferences("remimber", 0)
                var edit: SharedPreferences.Editor = share?.edit()!!
                edit.putString("stata", "active")
                edit.commit()

            }
        }
        else { Toast.makeText(context, "plase go verified your account ... ", Toast.LENGTH_SHORT).show() }
    }


}