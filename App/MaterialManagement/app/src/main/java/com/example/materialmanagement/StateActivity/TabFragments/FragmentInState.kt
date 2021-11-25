package com.example.materialmanagement.StateActivity.TabFragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.R
import com.example.materialmanagement.SearchActivity.SearchCustomer
import com.example.materialmanagement.SearchActivity.SearchItem
import com.example.materialmanagement.StateActivity.TabRecyclerAdapter.InStateRecyclerAdapter
import com.google.android.material.datepicker.MaterialDatePicker

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentInState.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentInState : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var searchCustomer : SearchView
    private lateinit var dateText : TextView
    private lateinit var searchDate : ImageButton

    private lateinit var intent : Intent

    private val NO_SEARCH : String = "null"
    private var customerNameString : String = NO_SEARCH // 발주번호
    private var customerNumString : String = NO_SEARCH // 수주번호

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
        val view = inflater.inflate(R.layout.fragment_in_state, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.item_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = InStateRecyclerAdapter()

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

                // 검색 버튼 누를 때 호출
                intent = Intent(getActivity(), SearchCustomer::class.java)
                intent.putExtra("query", query)
                startActivityForResult(intent, 100)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                // 검색창에서 글자가 변경이 일어날 때마다 호출

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
            dateRangePicker.addOnPositiveButtonClickListener {
                dateText.setText(dateRangePicker.headerText)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                100 -> {
                    customerNameString = data!!.getStringExtra("cust_nm").toString()
                    customerNumString = data!!.getStringExtra("cust_cd").toString()
                    Toast.makeText(activity, "$customerNameString $customerNumString", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(activity, "검색결과없음", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentInState.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentInState().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}