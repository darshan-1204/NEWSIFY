package com.example.newsify.Fragment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.newsify.Activity.LoginActivity
import com.example.newsify.Activity.SignupActivity
import com.example.newsify.R
import com.example.newsify.SQLDatabase.SqliteDatabase
import com.example.newsify.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var db = SqliteDatabase(requireContext())
        var list = db.showData()
        var position = 0

        binding.username.text = list[position].username
        binding.userGender.text = list[position].gender

        binding.btnEdit.setOnClickListener {
            var dialog = Dialog(requireContext())

        }

//        binding.dot.setOnClickListener {
//            val popupMenu: PopupMenu = PopupMenu(context, binding.dot)
//            popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)
//            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
//                when (item.itemId) {
//                    R.id.logout ->d
//                        requireContext().startActivity(Intent(context,SignupActivity::class.java))
//
//                }
//                true
//            })
//            popupMenu.show()
//        }

    }

}