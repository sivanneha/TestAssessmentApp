package com.example.mytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: ImageAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<ListItemData>()
    private lateinit var adapterList: ListItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setUpTransformer()
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
            }
        })
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    private val runnable = Runnable {
        viewPager.currentItem = viewPager.currentItem + 1
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        /* transformer.addTransformer { page, position ->
             val r = 1 - abs(position)
             page.scaleY = 0.85f + r * 0.14f
         }*/
        viewPager.setPageTransformer(transformer)
    }

    private fun init() {
        viewPager = findViewById(R.id.viewPager)
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()
        addImageList()
        adapter = ImageAdapter(imageList, viewPager)

        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 3 // Render the left and right items
        viewPager.clipToPadding = false // No clipping the left and right items
        viewPager.clipChildren =
            false  // Show the viewpager in full width without clipping the padding
        viewPager.getChildAt(0).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapterList = ListItemAdapter(mList)
        recyclerView.adapter = adapterList

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }

    private fun addImageList() {
        imageList.add(R.drawable.one)
        imageList.add(R.drawable.two)
        imageList.add(R.drawable.three)
        imageList.add(R.drawable.four)
        imageList.add(R.drawable.five)
        imageList.add(R.drawable.six)
        imageList.add(R.drawable.seven)
        imageList.add(R.drawable.eight)
    }

    private fun addDataToList() {
        mList.add(ListItemData("Android"))
        mList.add(ListItemData("Java"))
        mList.add(ListItemData("Kotlin"))
        mList.add(ListItemData("C++"))
        mList.add(ListItemData("Python"))
        mList.add(ListItemData("HTML"))
        mList.add(ListItemData("Swift"))
        mList.add(ListItemData("C#"))
        mList.add(ListItemData("JavaScript"))
        mList.add(ListItemData("AngularJS"))
        mList.add(ListItemData("XML"))
        mList.add(ListItemData("JSP"))
        mList.add(ListItemData("PHP"))
        mList.add(ListItemData("React"))
        mList.add(ListItemData("Flutter"))
        mList.add(ListItemData("Dart"))
        mList.add(ListItemData("Item"))
        mList.add(ListItemData("Apple"))
        mList.add(ListItemData("Ant"))
        mList.add(ListItemData("Ball"))
        mList.add(ListItemData("Bat"))
        mList.add(ListItemData("Cat"))
        mList.add(ListItemData("Cot"))
        mList.add(ListItemData("Dog"))
        mList.add(ListItemData("Doll"))
        mList.add(ListItemData("Egg"))
        mList.add(ListItemData("Elephant"))
        mList.add(ListItemData("Fish"))
        mList.add(ListItemData("Gold"))
    }

    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<ListItemData>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapterList.setFilteredList(filteredList)
            }
        }
    }
}