package com.example.materialmanagement.StateActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.R
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentState.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentState : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var searchCustomer : SearchView
    private lateinit var dateText : TextView
    private lateinit var searchDate : ImageButton

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
        // Inflate the layout for this fragment
        //recycler view
        val view = inflater.inflate(R.layout.fragment_state, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.item_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = StateRecyclerAdapter()

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
//            val calendar = Calendar.getInstance()
//            var year = calendar.get(Calendar.YEAR)
//            var month = calendar.get(Calendar.MONTH)
//            var day = calendar.get(Calendar.DAY_OF_MONTH)
//
//            var date_listener = object : DatePickerDialog.OnDateSetListener{
//                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
//                    dateText.text = "${year}년 ${month + 1}월 ${day}일"
//                }
//            }
//
//            var builder = DatePickerDialog(view.context, date_listener, year, month, day)
//            builder.show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentState.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentState().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}