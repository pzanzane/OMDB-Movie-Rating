package com.deligence.omdbmovierating

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import com.deligence.omdbmovierating.enums.EnumTypes
import com.deligence.omdbmovierating.fragments.FragmentListItem
import com.deligence.omdbmovierating.models.ModelHome
import com.deligence.omdbmovierating.utility.ToastUtils
import com.deligence.omdbmovierating.viewmodels.VMActivityHome

class ActivityHome : AppCompatActivity() {

    lateinit var liveDatamodelHome: LiveData<ModelHome>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.homeToolBar))


        val homeViewModel = ViewModelProviders.of(this).get(VMActivityHome::class.java)
        liveDatamodelHome = homeViewModel!!.getMovies()


        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        setupViewPager(viewPager)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)

        loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.homemenu, menu)

        val menuItem: MenuItem = menu!!.findItem(R.id.action_search)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(quertChangeListener)

        return true
    }

    fun loadData(){
        liveDatamodelHome.observe(this, Observer<ModelHome>{
            modelHome ->


            if(!TextUtils.isEmpty(modelHome?.errorMessage)){
                ToastUtils.showToast(modelHome?.errorMessage!!)
            }else{

                Log.d("WASTE"," In Activity Observer: "+ modelHome?.listDataMovies?.size)

            }
        })
    }

    fun setupViewPager(viewPager: ViewPager){

        val pagerAdapter = HomePagerAdapter(this, supportFragmentManager)
        pagerAdapter.addFragment(0,EnumTypes.ALL.name)
        pagerAdapter.addFragment(1,EnumTypes.MOVIE.name)
        pagerAdapter.addFragment(2,EnumTypes.SERIES.name)

        viewPager.adapter = pagerAdapter

    }
    val quertChangeListener = object: SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(text: String?): Boolean {

            Log.d("WASTE", "Query Searched:"+ text)
            return true
        }

        override fun onQueryTextChange(text: String?): Boolean {
            return true
        }
    }

    private class HomePagerAdapter: FragmentPagerAdapter{

        private val mapPosition: MutableMap<Int, String>  = HashMap()
        private lateinit var context: Context

        constructor(context: Context,
                    fragManager: FragmentManager) : super(fragManager) {
            this.context = context
        }

        override fun getItem(position: Int): Fragment {

            Log.d("WASTE","getItemCalled "+ position)
            return FragmentListItem.getInstance(mapPosition.get(position)!!, context)

        }

        override fun getCount(): Int {
            return mapPosition.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mapPosition.get(position)
        }

        fun addFragment(pos: Int, title: String){
            mapPosition.put(pos, title)

            Log.d("WASTE","Fragments:: "+mapPosition.size)
        }
    }
}
