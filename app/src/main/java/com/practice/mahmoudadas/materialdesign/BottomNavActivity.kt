package com.practice.mahmoudadas.materialdesign

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bottom_nav.*

class BottomNavActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav)

        setupToolbar()
        setupBottomNavView()
    }

    private fun setupBottomNavView() {
        bottomNavView.apply {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.list -> {
                        Toast.makeText(this@BottomNavActivity, "list", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.favorite -> {
                        Toast.makeText(this@BottomNavActivity, "favorite", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.friends -> {
                        Toast.makeText(this@BottomNavActivity, "friends", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }
    }

    private fun setupToolbar() {
        toolbar.apply {
            setSupportActionBar(this)
            layoutParams.height += resources.dpToPx(24)
            setPadding(0, resources.dpToPx(24), 0, 0)
        }
        supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish(); true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
