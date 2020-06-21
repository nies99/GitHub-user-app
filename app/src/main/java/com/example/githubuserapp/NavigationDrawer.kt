package com.example.githubuserapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.navigation_view.*

class NavigationDrawer: BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navigation_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.go_to_github -> {
                    val goToGithub = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.github.com/"))
                    startActivity(goToGithub)
                }
                R.id.menuInfo -> {

                }
            }
            true
        }
        return inflater.inflate(R.layout.navigation_view, container, false)
    }
}