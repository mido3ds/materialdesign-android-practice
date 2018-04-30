package com.practice.mahmoudadas.materialdesign

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    var cardsCount = 10
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setupRecyclerView()
        setupFab()
        setupNav()
    }

    private fun setupNav() {
        nav.setNavigationItemSelectedListener { item: MenuItem ->
            item.isChecked = true
            drawer.closeDrawers()

            Snackbar.make(coordLayout, "you clicked ${item.title}", Snackbar.LENGTH_SHORT)
                    .show()
            true
        }
        nav.setPadding(0, resources.dpToPx(24), 0, 0)
    }

    private fun setupFab() = fab.setOnClickListener {
        cardsCount++
        recyclerView.adapter.notifyDataSetChanged()
    }

    private fun setupToolbar() {
        val statusBarHeight = resources.dpToPx(24)
        toolbar.apply {
            setSupportActionBar(this)

            layoutParams.height += statusBarHeight
            setPadding(0, statusBarHeight, 0, 0)
            (parent as CollapsingToolbarLayout).apply {
                setExpandedTitleColor(resources.getColor(R.color.white))
                setCollapsedTitleTextColor(resources.getColor(R.color.white))
            }
            requestLayout()
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CustomRVAdapter(this@MainActivity)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.about -> {
                Snackbar.make(coordLayout, "you clicked about", Snackbar.LENGTH_SHORT)
                        .show()
                true
            }
            R.id.bottom_sheet -> {
                CustomBSDFragment().apply { show(supportFragmentManager, "custom_bottom_sheet_fragment") }
                true
            }
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}

class CustomRVAdapter(private val activity: MainActivity): RecyclerView.Adapter<CustomRVAdapter.ViewHolder>() {
    inner class ViewHolder(val cardView: CardView): RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(activity)
                        .inflate(R.layout.item_card, parent, false)
                        as CardView
        )
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.cardView?.findViewById<TextView>(R.id.textViewNumber)?.text = "Card $position"
        holder?.cardView?.findViewById<Button>(R.id.acceptButton)?.setOnClickListener {
            Snackbar.make(activity.coordLayout, "You accepted card $position", Snackbar.LENGTH_SHORT)
                    .show()
        }

        if (position + 1 == itemCount) {
            (holder?.cardView?.layoutParams as ViewGroup.MarginLayoutParams)
                    .bottomMargin = activity.resources.dpToPx(8)
            holder.cardView.requestLayout()
        }
    }

    override fun getItemCount(): Int = activity.cardsCount
}

class CustomBSDFragment : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.bottom_sheet, container, false).apply { }
}
