package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class loginFragment : Fragment() {
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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
                loginFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        val myButton = view?.findViewById<Button>(R.id.login) as Button
       val myButton2 = view?.findViewById<Button>(R.id.register) as Button
       myButton2.setOnClickListener(){
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment,registerFragment())?.commitNow()
        }
        myButton.setOnClickListener(){
            ///Toast.makeText(context, "ssasa", Toast.LENGTH_SHORT).show()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment,loguserFragment())?.commitNow()

        }
    }
}