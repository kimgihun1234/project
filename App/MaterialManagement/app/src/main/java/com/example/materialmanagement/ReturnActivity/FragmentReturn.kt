package com.example.materialmanagement.ReturnActivity

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.SearchActivity.SearchInOrder
import com.example.materialmanagement.SearchActivity.SearchOutOrder
import com.example.materialmanagement.R
import com.example.materialmanagement.SearchActivity.SearchItem
import com.example.materialmanagement.SearchActivity.SearchStorage
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.zxing.integration.android.IntentIntegrator
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentReturn.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentReturn : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var toggleButton : MaterialButtonToggleGroup
    private lateinit var btnIn : Button
    private lateinit var btnOut : Button
    private lateinit var putBtn : Button
    private lateinit var barCodeScanBtn : ImageButton
    private lateinit var searchOrder : SearchView
    private lateinit var searchCustomer : TextView
    private lateinit var searchStorage : SearchView
    private lateinit var searchBarCode : SearchView
    private lateinit var searchItemName : SearchView

    private var buttonState : Boolean = true // 입고는 true, 출고는 false
    private lateinit var tableDate : TextView

    private lateinit var intent : Intent

    private lateinit var dialogView : View
    private lateinit var setDate : TextView
    private lateinit var putDate : TextView
    private lateinit var deleteBtn : Button

    private val NO_SEARCH : String = "null"
    private var searchCategory : Int = 0 // 1 : 수주번호, 2 : 발주번호,  3 : 창고, 4 : 품목명

    //dialog

    private var itemInNumString : String = NO_SEARCH
    private var itemOutNumString : String = NO_SEARCH
    private var itemNameString : String = NO_SEARCH
    private var storNameString : String = NO_SEARCH
    private var itemSizeString : String = NO_SEARCH
    private lateinit var itemName : TextView
    private lateinit var emp_name : TextView
    private lateinit var storName : TextView
    private lateinit var itemSize : EditText

    private lateinit var refreshBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(com.example.materialmanagement.ReturnActivity.ARG_PARAM1)
            param2 = it.getString(com.example.materialmanagement.ReturnActivity.ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_return, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.item_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = ReturnRecyclerAdapter()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toggleButton = view.findViewById(R.id.toggleButton)
        btnIn = view.findViewById(R.id.btnIn)
        btnOut = view.findViewById(R.id.btnOut)
        barCodeScanBtn = view.findViewById(R.id.barCodeScanBtn)
        putBtn = view.findViewById(R.id.putBtn)
        tableDate = view.findViewById(R.id.tableDate)
        deleteBtn = view.findViewById(R.id.deleteBtn)
        refreshBtn = view.findViewById(R.id.refreshBtn)

        toggleButton.addOnButtonCheckedListener{ toggleButton, checkedId, isChecked ->
            if(isChecked) {
                when (checkedId) {
                    R.id.btnIn -> {
                        btnIn.getBackground().setTint(view.getResources().getColor(R.color.white));
                        btnOut.getBackground().setTint(view.getResources().getColor(R.color.darkGray));

                        searchOrder.setQueryHint("발주 번호")
                        tableDate.text = "입고일자"
                        buttonState = true
                    }
                    R.id.btnOut -> {
                        btnOut.getBackground().setTint(view.getResources().getColor(R.color.white));
                        btnIn.getBackground().setTint(view.getResources().getColor(R.color.darkGray));

                        searchOrder.setQueryHint("수주 번호")
                        tableDate.text = "출고일자"
                        buttonState = false
                    }
                }
            } else {
                if(toggleButton.checkedButtonId == View.NO_ID) {
                    Toast.makeText(activity,"선택 사항 없음", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //새로 고침 버튼
        refreshBtn.setOnClickListener{
            Toast.makeText(activity, "refresh", Toast.LENGTH_SHORT).show()
        }

        searchOrder = view.findViewById(R.id.searchOrder)
        searchCustomer = view.findViewById(R.id.searchCustomer)
        searchStorage = view.findViewById(R.id.searchStorage)
        searchBarCode = view.findViewById(R.id.searchBarCode)
        searchItemName = view.findViewById(R.id.searchItemName)

        searchOrder.isSubmitButtonEnabled = true
        searchStorage.isSubmitButtonEnabled = true
        searchBarCode.isSubmitButtonEnabled = true
        searchItemName.isSubmitButtonEnabled = true

        searchOrder.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(buttonState){
                    intent = Intent(getActivity(), SearchInOrder::class.java)
                    searchCategory = 1 // 발주번호검색
                } else {
                    intent = Intent(getActivity(), SearchOutOrder::class.java)
                    searchCategory = 2 //수주번호검색
                }
                intent.putExtra("query", query)
                startActivityForResult(intent, 100);

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                // 검색창에서 글자가 변경이 일어날 때마다 호출

                return true
            }
        })

        searchStorage.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                intent = Intent(getActivity(), SearchStorage::class.java)
                intent.putExtra("query", query)
                //getActivity()?.startActivity(intent)
                searchCategory = 3 // 창고검색
                startActivityForResult(intent, 100);
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                // 검색창에서 글자가 변경이 일어날 때마다 호출

                return true
            }
        })

        searchItemName.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                intent = Intent(getActivity(), SearchItem::class.java)
                intent.putExtra("query", query)
                searchCategory = 4 //품목명검색
                startActivityForResult(intent, 100);
                // 검색 버튼 누를 때 호출

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                // 검색창에서 글자가 변경이 일어날 때마다 호출

                return true
            }
        })

        searchBarCode.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                // 검색 버튼 누를 때 호출

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                // 검색창에서 글자가 변경이 일어날 때마다 호출

                return true
            }
        })

        val positiveInButtonClick = { dialogInterface: DialogInterface, i: Int ->
            if(itemSize.getText().toString().equals("") || itemSize.getText().toString() == null){
                Toast.makeText(activity, "수량을 입력해주세요", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            } else {
                Toast.makeText(activity, "입고반품되었습니다", Toast.LENGTH_SHORT).show()
                itemNameString = NO_SEARCH
                storNameString = NO_SEARCH
            }
        }
        val positiveOutButtonClick = { dialogInterface: DialogInterface, i: Int ->
            Toast.makeText(activity, "출고반품되었습니다", Toast.LENGTH_SHORT).show()
        }
        val negativeButtonClick = { dialogInterface: DialogInterface, i: Int ->

        }

        //입고 dialog // 현재 시간
        putBtn.setOnClickListener {
            dialogView = View.inflate(view.context, R.layout.in_dialog, null)

            itemName = dialogView.findViewById(R.id.itemName)
            setDate = dialogView.findViewById(R.id.setDate)
            storName = dialogView.findViewById(R.id.storName)
            itemSize = dialogView.findViewById(R.id.itemSize)

            setDate = dialogView.findViewById(R.id.setDate)
            putDate = dialogView.findViewById(R.id.putDate)

            val now = System.currentTimeMillis()
            var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN).format(now)

            putDate.setText("반품일자")

            if(itemNameString != NO_SEARCH && storNameString != NO_SEARCH){ //itemInNumString != NO_SEARCH || itemOutNumString != NO_SEARCH)
                setDate.setText(simpleDateFormat)
                itemName.text = itemNameString
                storName.text = storNameString

                if(itemSizeString != NO_SEARCH){
                    itemSize.setText(itemSizeString)
                }

                var dlg = AlertDialog.Builder(view.context)
                dlg.setTitle("반품 등록")
                if (buttonState){
                    dlg.setView(dialogView)
                    dlg.setPositiveButton("입고반품", positiveInButtonClick)
                } else {
                    dlg.setView(dialogView)
                    dlg.setPositiveButton("출고반품", positiveOutButtonClick)
                }

                dlg.setNegativeButton("취소", negativeButtonClick)
                dlg.show()
            } else {
                Toast.makeText(activity, "검색 요소가 부족합니다", Toast.LENGTH_SHORT).show()
            }
        }

        //바코드 스캔
        barCodeScanBtn.setOnClickListener {
            val scanIntegrator = IntentIntegrator.forSupportFragment(this@FragmentReturn)
            scanIntegrator.setPrompt("Scan")
            scanIntegrator.setBeepEnabled(true)
            scanIntegrator.setBarcodeImageEnabled(true)
            scanIntegrator.initiateScan()
        }

        deleteBtn.setOnClickListener{
            Toast.makeText(activity, "삭제되었습니다", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data) //결과 파싱
        if (scanningResult != null) { //정상적으로 전달
            if (scanningResult.contents != null) { //result 값

                //수정 필요 - 미들웨어에서 받은 값 파싱으로
                val jsonObject = JSONObject(scanningResult.contents)
                //val jsonArray = jsonObject.getJSONArray("person")

                val item_nm = jsonObject.getString("item_nm") //품목이름
                val item_cd = jsonObject.getString("item_cd")//품목번호
                val qty = jsonObject.getString("qty") //품목수

                itemNameString = item_nm
                if(buttonState){
                    itemInNumString = item_cd
                } else {
                    itemOutNumString = item_cd
                }
                itemSizeString = qty

                if(storNameString != NO_SEARCH){
                    Toast.makeText(activity, "반품되었습니다", Toast.LENGTH_SHORT).show()
                    storNameString = NO_SEARCH
                    itemNameString = NO_SEARCH
                    itemSizeString = NO_SEARCH
                } else {
                    Toast.makeText(activity, "검색 요소가 부족합니다", Toast.LENGTH_SHORT).show()
                }

                //Toast.makeText(activity,"Scanned : ${scanningResult.contents} format : ${scanningResult.formatName}", Toast.LENGTH_SHORT).show()
            }
        }

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                100 -> {
                    when(searchCategory){
                        1 -> itemInNumString = data!!.getStringExtra("searchResult").toString()
                        2 -> itemOutNumString = data!!.getStringExtra("searchResult").toString()
                        3 -> storNameString = data!!.getStringExtra("searchResult").toString()
                        4 -> itemNameString = data!!.getStringExtra("searchResult").toString()
                    }
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
         * @return A new instance of fragment FragmentReturn.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentReturn().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}