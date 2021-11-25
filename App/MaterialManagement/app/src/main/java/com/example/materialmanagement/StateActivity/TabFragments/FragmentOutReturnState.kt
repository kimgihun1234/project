package com.example.materialmanagement.StateActivity.TabFragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.DTO.StatePostData
import com.example.materialmanagement.DTO.UnstoringReturnListGetData
import com.example.materialmanagement.MainActivity
import com.example.materialmanagement.R
import com.example.materialmanagement.SearchActivity.SearchCustomer
import com.example.materialmanagement.StateActivity.TabRecyclerAdapter.InReturnStateRecyclerAdapter
import com.example.materialmanagement.StateActivity.TabRecyclerAdapter.InStateRecyclerAdapter
import com.example.materialmanagement.StateActivity.TabRecyclerAdapter.OutReturnStateRecyclerAdapter
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentOutReturnState.newInstance] factory method to
 * create an instance of this fragment.
 */

//출고반품조회
class FragmentOutReturnState : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var searchCustomer : SearchView
    private lateinit var dateText : TextView
    private lateinit var searchDate : ImageButton

    private val NO_SEARCH : String = ""

    private var startDate : String = NO_SEARCH // 선택 날짜 1
    private var endDate : String = NO_SEARCH // 선택 날짜 2
    private var itemNumstring : String = NO_SEARCH // 품목번
    private var customerNumString : String = NO_SEARCH // 거래처번호
    private var customerNameString : String = NO_SEARCH // 거래처명

    private lateinit var intent : Intent

    private lateinit var recyclerView: RecyclerView

    private lateinit var myRequest : String
    private var data : List<InReturnStateRecyclerAdapter> = emptyList()
    private var searchData : MutableList<InReturnStateRecyclerAdapter> = mutableListOf()

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
        val view = inflater.inflate(R.layout.fragment_out_state, container, false)
        recyclerView = view.findViewById(R.id.item_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchCustomer = view.findViewById(R.id.searchCustomer)
        dateText = view.findViewById(R.id.dateText)
        searchDate = view.findViewById(R.id.searchDate)

        searchCustomer.isSubmitButtonEnabled = true

        searchCustomer.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                intent = Intent(getActivity(), SearchCustomer::class.java)
                intent.putExtra("query", query)
                startActivityForResult(intent, 100)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        searchDate.setOnClickListener {
            val dateRangePicker =
                MaterialDatePicker.Builder.dateRangePicker()
                    .setTitleText("검색 기간을 골라주세요")
//                    .setSelection(
//                        Pair(
//                            MaterialDatePicker.thisMonthInUtcMilliseconds(),
//                            MaterialDatePicker.todayInUtcMilliseconds()
//                        )
//                    )
                    .build()

            dateRangePicker.show(childFragmentManager, "date_picker")
            dateRangePicker.addOnPositiveButtonClickListener(object :
                MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>> {
                override fun onPositiveButtonClick(selection: Pair<Long, Long>?) {
                    val calendar = Calendar.getInstance()
                    calendar.timeInMillis = selection?.first ?: 0
                    startDate = SimpleDateFormat("yyyyMMdd").format(calendar.time).toString()
                    Log.d("start", startDate)

                    calendar.timeInMillis = selection?.second ?: 0
                    endDate = SimpleDateFormat("yyyyMMdd").format(calendar.time).toString()
                    Log.d("end", endDate)

                    dateText.setText(dateRangePicker.headerText)
                    getInstance(customerNumString, startDate, endDate, itemNumstring)
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                100 -> {
                    customerNameString = data!!.getStringExtra("cust_nm").toString()
                    customerNumString = data!!.getStringExtra("cust_cd").toString()
                    System.out.println(customerNameString + " " + customerNumString)
                }
            }
        } else {
            Toast.makeText(activity, "검색결과없음", Toast.LENGTH_SHORT).show()
        }
    }

    fun getInstance(customerNumString : String, startDate : String, endDate : String, itemNumstring: String){
        val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
        val url = "http://101.101.208.223:8080/unstoringReturnList"

        val postData = StatePostData(customerNumString, startDate, endDate, itemNumstring)
        val jsonString = Gson().toJson(postData)

        val formBody: RequestBody = RequestBody.create(JSON, jsonString)

        val request: Request = Request.Builder()
            .url(url)
            .post(formBody)
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
                    val data = Gson().fromJson(myRequest, Array<UnstoringReturnListGetData>::class.java).toList()

                    activity!!.runOnUiThread{
                        for(i in 0..data.size-1){
                            System.out.println(data[i].item_cd + ", " +  data[i].item_nm);
                        }

                        val inReturnStateRecyclerView = OutReturnStateRecyclerAdapter(data)

                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                            adapter = inReturnStateRecyclerView
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
         * @return A new instance of fragment FragmentOutReturnState.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentOutReturnState().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}