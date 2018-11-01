package com.deligence.omdbmovierating.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.deligence.omdbmovierating.R
import com.deligence.omdbmovierating.activities.ActivityDetail
import com.deligence.omdbmovierating.utility.SpacesItemDecoration
import com.deligence.omdbmovierating.dataobjects.DataMovie
import com.deligence.omdbmovierating.enums.EnumTypes
import com.deligence.omdbmovierating.models.ModelHome
import com.deligence.omdbmovierating.utility.ToastUtils
import com.deligence.omdbmovierating.viewmodels.VMActivityHome

class FragmentListItem : Fragment(), View.OnClickListener{

    companion object {
        val TAG = "FragmentListItem"
        val EXTRA_TYPE = "Type"

        fun getInstance(type: String, context: Context): Fragment{

            val bundle = Bundle()
            bundle.putString(EXTRA_TYPE, type)

            val frag: FragmentListItem  = Fragment.instantiate(context,
                    FragmentListItem::class.java.name) as FragmentListItem
            frag.arguments = bundle

            return frag
        }
    }

    var type: String? = null
    var listMovie: List<DataMovie>? = null
    var homeViewModel: VMActivityHome? = null
    var progress: ProgressBar? = null
    var textView: TextView? = null
    var recylerView: RecyclerView? = null
    private var adapter: AdapterMovies? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.getString(EXTRA_TYPE) ?: EnumTypes.ALL.name
    }

    fun showProgress(){
        progress?.visibility =  View.VISIBLE
        recylerView?.visibility = View.INVISIBLE
        textView?.visibility = View.INVISIBLE
    }

    fun hideProgress(){
        progress?.visibility =  View.GONE
        recylerView?.visibility = View.VISIBLE
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_list_movie, null)

        progress = view.findViewById(R.id.progressBar)
        textView = view.findViewById(R.id.textNoData)

        recylerView = view.findViewById(R.id.recylerView)
        recylerView?.layoutManager = LinearLayoutManager(activity!!)
        recylerView?.addItemDecoration(SpacesItemDecoration(20))
        return view
    }

    override fun onClick(view: View?) {

        if(adapter!=null && view != null){

            val intent = Intent(activity, ActivityDetail::class.java)
            intent.putExtra(ActivityDetail.EXTRA_DATAMOVIE, adapter!!.getItem(Integer.parseInt(view.tag.toString())))
            startActivity(intent)

        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewModel = ViewModelProviders.of(activity!!).get(VMActivityHome::class.java)
        homeViewModel?.getMovies()?.observe(this, Observer<ModelHome>{
            modelHome ->

            hideProgress()

            if(!TextUtils.isEmpty(modelHome?.errorMessage)){
                ToastUtils.showToast(modelHome?.errorMessage!!)

                textView?.visibility =  View.VISIBLE
                textView?.text = modelHome?.errorMessage
                recylerView?.visibility = View.INVISIBLE

            }else{

                var objectType = ""

                if(type.equals(EnumTypes.MOVIE.name, true)){
                    objectType = "movie"
                }else if(type.equals(EnumTypes.SERIES.name, true)){
                    objectType = "series"
                }

                if(TextUtils.isEmpty(objectType)){

                    listMovie = modelHome?.listDataMovies

                }else{

                    listMovie = modelHome?.listDataMovies?.filter { model ->
                        model.type.equals(objectType)
                    }
                }

                loadAdapter()
            }
        })

    }

    fun loadAdapter(){
        Log.d("WASTE","================= "+ type+ " =================")
        Log.d("WASTE"," In Fragment : "+ listMovie?.size)

        if(listMovie != null){
            adapter = AdapterMovies(activity!!, listMovie!!, this)
            recylerView?.adapter = adapter

        }


    }

    private class AdapterMovies(private var context: Context, private var list: List<DataMovie>,
                                private var onClickListener: View.OnClickListener): RecyclerView.Adapter<AdapterMovies.ViewHolderMovie>() {

        private class ViewHolderMovie: RecyclerView.ViewHolder{

            val imgePoster: ImageView
            val textTitle: TextView
            val textYear: TextView
            val item: View

            constructor(itemView: View?, onClickListener: View.OnClickListener): super(itemView!!){

                item = itemView
                imgePoster = itemView?.findViewById(R.id.imgPoster)
                textTitle = itemView?.findViewById(R.id.textTitle)
                textYear = itemView?.findViewById(R.id.textYear)

                item.setOnClickListener(onClickListener)

            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolderMovie {


            return ViewHolderMovie(LayoutInflater.from(context).inflate(R.layout.view_movie_item,viewGroup, false),
                    onClickListener)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        fun getItem(position: Int): DataMovie?{
            Log.d("WASTE","ItemClicked:: "+position)
            if(position >= 0 && position < list.size){
                return list.get(position)
            }
            return null
        }
        override fun onBindViewHolder(viewHolder: ViewHolderMovie, position: Int) {

            val dataMovie = list.get(position)
            viewHolder.textTitle.text = dataMovie.name
            viewHolder.textYear.text = dataMovie.year.toString()
            viewHolder.item.tag = position

            Glide.with(context)
                    .load(dataMovie.posterUrl)
                    .apply(RequestOptions().override(150,200))
                    .into(viewHolder.imgePoster)
        }


    }
}
