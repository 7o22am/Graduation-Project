package com.example.myapplication

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_alerts_.*
import kotlinx.android.synthetic.main.list_row.view.*

class CustomAlert {

    fun showDialog(activity: Activity?) {
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_layout)
        val text = dialog.findViewById<View>(R.id.text_dialog) as TextView
        var s =""
        val dialogButton: Button = dialog.findViewById<View>(R.id.btn_dialog) as Button
        val dialogButton2: Button = dialog.findViewById<View>(R.id.btn2_dialog) as Button
        dialogButton.setOnClickListener {

            val database = Firebase.database
            val myRef2 = database.getReference("Noitifaction")
            var s=text.text.toString()
            if(s.length>3) {
                myRef2.push().setValue(s)
            }
            dialog.dismiss()
        }
        dialogButton2.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}

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
    var myAuth= FirebaseAuth.getInstance()
    val userDataBase= FirebaseFirestore.getInstance().collection("Users");
    val storge = FirebaseStorage.getInstance()
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
        val myRef2 = database.getReference("Feedback")

        var u: Users? = null
        val ss :String="Teacher"
        userDataBase.document(myAuth.currentUser!!.uid.toString()).get().addOnSuccessListener {
            u = it.toObject(Users::class.java)!!
          if (u?.type.toString() == ss) {
              fab.isVisible = true
          }
        }
fab.setOnClickListener{

    val alert = CustomAlert()
     alert.showDialog(context as Activity?)

}





        val rec = view?.findViewById<ListView>(R.id.list) as ListView
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val myarray = arrayListOf<String>()
                for (i in dataSnapshot.children) {
                    val v = i.getValue(String::class.java)
                    val s = v.toString()
                    myarray.add(s)
                }
                myarray.reverse()
                //    val myadp = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, myarray)
                rec.adapter = context?.let { my_custom_adp(it, myarray) }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
            }
        })


    }

    private class my_custom_adp(context: Context, array: ArrayList<String>) : BaseAdapter()
    {
        val mycontex :Context
        val myarr :ArrayList<String>
        init {
            this.mycontex=context
            this.myarr=array

        }
        override fun getCount(): Int {
            return myarr.size
        }

        override fun getItem(p0: Int): Any {
            return ""
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
val my_layout_inf = LayoutInflater.from(mycontex).inflate(R.layout.list_row, p2, false)

         //  my_layout_inf.message.text = myarr[p0]
           // my_layout_inf.img.setImageResource(R.drawable.ic_facebook)



                 if (myarr[p0].contains("firebasestorage.googleapis.com") ) {
                    Picasso.get()
                        .load(myarr[p0])
                        .resize(1050, 1200)
                        .into(my_layout_inf.img)
                }
                else
                {
                    my_layout_inf.message.text=myarr[p0]+"\n"
                }

            return my_layout_inf


        }

    }

}


