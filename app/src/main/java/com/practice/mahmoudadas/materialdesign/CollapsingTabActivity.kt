@file:Suppress("DEPRECATION")

package com.practice.mahmoudadas.materialdesign

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.*
import android.widget.LinearLayout
import android.widget.LinearLayout.*
import android.widget.LinearLayout.LayoutParams.*
import android.widget.ScrollView
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_collapsing_tab.*

class CollapsingTabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collapsing_tab)

        setupToolbar()
        setupTabLayout()
        setupViewPager()
    }

    private fun setupTabLayout() {
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setupViewPager() {
        viewPager.adapter = CustomVPAdapter(supportFragmentManager)
    }

    private fun setupToolbar() {
        toolbar.apply {
            setSupportActionBar(this)

            layoutParams.height += resources.dpToPx(24)
            setPadding(0, resources.dpToPx(24), 0, 0)
            titleColor = resources.getColor(R.color.white)
            requestLayout()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {finish(); true}
            else -> super.onOptionsItemSelected(item)
        }
    }
}

class CustomVPAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = ExampleFragment().apply { i = position }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence = "page #$position"
}

class ExampleFragment: Fragment() {
    var i: Int = 1

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ScrollView(context).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            isNestedScrollingEnabled = false

            addView(LinearLayout(context).apply {
                orientation = VERTICAL
                layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)

                addView(TextView(context).apply {
                    text = "some text in fragment #$i"
                    textSize = 15F
                    setTextColor(resources.getColor(R.color.black))
                    layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                        val margin = resources.dpToPx(16)
                        setMargins(margin, margin, margin, margin)
                    }
                })


                for (j in 0..50) { addView(TextView(context).apply {
                    text = "some other text"
                    layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                        val margin = resources.dpToPx(16)
                        setMargins(margin, 0, margin, margin)
                    }
                }) }
            })
        }
    }
}