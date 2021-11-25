package com.example.materialmanagement.StateActivity.TabFragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.DTO.CurrentData
import com.example.materialmanagement.R
import com.example.materialmanagement.SearchActivity.SearchStorage
import com.example.materialmanagement.StateActivity.TabRecyclerAdapter.CurrentStateRecyclerAdapter
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCurrentState.newInstance] factory method to
 * create an instance of this fragment.
 */

//현재고조회
class FragmentCurrentState : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var intent : Intent
    private lateinit var searchStorage : SearchView

    private val NO_SEARCH : String = "null"
    private var storageNameString : String = NO_SEARCH
    private var storageNumString : String = NO_SEARCH

    private lateinit var recyclerView: RecyclerView

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
        val view = inflater.inflate(R.layout.fragment_current_state, container, false)
        recyclerView = view.findViewById(R.id.item_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchStorage = view.findViewById(R.id.searchStorage)
        searchStorage.isSubmitButtonEnabled = true

        searchStorage.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                intent = Intent(getActivity(), SearchStorage::class.java)
                intent.putExtra("query", query)
                startActivityForResult(intent, 100)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                100 -> {
                    storageNameString = data!!.getStringExtra("loca_nm").toString()
                    storageNumString = data!!.getStringExtra("loca_cd").toString()
//                    Toast.makeText(activity, "$storageNameString $storageNumString", Toast.LENGTH_SHORT).show()
                    getStorageStatus()

                }
            }
        } else {
            Toast.makeText(activity, "검색결과없음", Toast.LENGTH_SHORT).show()
        }
    }

    fun getStorageStatus(){
        //val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
        val url = "http://101.101.208.223:8080/curItemList"

        val request: Request = Request.Builder()
            .url(url)
            .get()
            .build();

        var client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                activity!!.runOnUiThread{ Log.d("test","fail")}
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val myRequest = response.body!!.string()
                    val data = Gson().fromJson(myRequest, Array<CurrentData>::class.java).toList()

                    activity!!.runOnUiThread{
                        for(i in 0..data.size-1){
                            System.out.println(data[i].item_cd + ", " +  data[i].item_nm)
                        }

                        val currentStateRecyclerAdapter = CurrentStateRecyclerAdapter(data)

                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                            adapter = currentStateRecyclerAdapter
                        }

                    }
                }
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentCurrentState.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCurrentState().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}