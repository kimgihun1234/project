package com.example.materialmanagement.ReturnActivity

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.DTO.*
import com.example.materialmanagement.InOutActivity.InRecyclerAdapter
import com.example.materialmanagement.InOutActivity.OutRecyclerAdapter
import com.example.materialmanagement.MainActivity
import com.example.materialmanagement.R
import com.example.materialmanagement.SearchActivity.*
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.zxing.integration.android.IntentIntegrator
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException
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
    private var searchCategory : Int = 0 // 1 : 수주번호, 2 : 발주번호,  3 : 창고, 4 : 품목명, 5 : 바코드

    private var itemInNumString : String = NO_SEARCH // 발주번호
    private var itemOutNumString : String = NO_SEARCH // 수주번호
    private var itemNumString : String = NO_SEARCH // 품목번호
    private var itemNameString : String = NO_SEARCH // 품목명
    private var storeNumString : String = NO_SEARCH // 창고번호
    private var storNameString : String = NO_SEARCH // 창고명
    private var locationNumString : String = NO_SEARCH // 위치번호
    private var locationNameString : String = NO_SEARCH // 위치명
    private var itemSizeString : String = NO_SEARCH // 수량
    private var customerNumString : String = NO_SEARCH // 거래처번호
    private var customerNameString : String = NO_SEARCH // 거래처명

    private var purc_retu_no : String = NO_SEARCH // 입고번호
    private var ex_retu_no : String = NO_SEARCH // 출고번호

    private lateinit var itemName : TextView
    private lateinit var emp_name : TextView
    private lateinit var storName : TextView
    private lateinit var itemSize : EditText

    private lateinit var myRequest : String
    private lateinit var inReturnRecyclerAdapter: InReturnRecyclerAdapter
    private lateinit var outReturnRecyclerAdapter: OutReturnRecyclerAdapter
    private lateinit var recyclerView: RecyclerView

    private var inData : MutableList<StoreStateInfo> = mutableListOf()
    private var outData : MutableList<StoreStateInfo> = mutableListOf()

    private var inDataPositionList : MutableList<Int> = mutableListOf()
    private var outDataPositionList : MutableList<Int> = mutableListOf()

    private var jwt : String = "null"

    private lateinit var refreshBtn : Button

    val client = OkHttpClient()

    val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
    val gson = GsonBuilder().setPrettyPrinting().create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_return, container, false)
        recyclerView = view.findViewById(R.id.item_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        inReturnRecyclerAdapter = InReturnRecyclerAdapter(inData)
        outReturnRecyclerAdapter = OutReturnRecyclerAdapter(outData)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            if(buttonState){
                adapter = inReturnRecyclerAdapter
            } else {
                adapter = outReturnRecyclerAdapter
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jwt = (activity as MainActivity?)?.getJwt().toString()

        toggleButton = view.findViewById(R.id.toggleButton)
        btnIn = view.findViewById(R.id.btnIn)
        btnOut = view.findViewById(R.id.btnOut)
        putBtn = view.findViewById(R.id.putBtn)
        deleteBtn = view.findViewById(R.id.deleteBtn)
        barCodeScanBtn = view.findViewById(R.id.barCodeScanBtn)
        tableDate = view.findViewById(R.id.tableDate)
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

                        recyclerView.apply {
                            adapter = inReturnRecyclerAdapter
                        }
                    }
                    R.id.btnOut -> {
                        btnOut.getBackground().setTint(view.getResources().getColor(R.color.white));
                        btnIn.getBackground().setTint(view.getResources().getColor(R.color.darkGray));

                        searchOrder.setQueryHint("수주 번호")
                        tableDate.text = "출고일자"
                        buttonState = false

                        recyclerView.apply {
                            adapter = outReturnRecyclerAdapter
                        }
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
                return true
            }
        })

        searchStorage.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                intent = Intent(getActivity(), SearchStorage::class.java)
                intent.putExtra("query", query)
                searchCategory = 3 // 창고검색
                startActivityForResult(intent, 100);
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        searchItemName.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                intent = Intent(getActivity(), SearchItem::class.java)
                intent.putExtra("query", query)
                searchCategory = 4 //품목명검색
                startActivityForResult(intent, 100);
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        searchBarCode.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                intent = Intent(getActivity(), SearchBarcode::class.java)
                intent.putExtra("query", query)
                searchCategory = 5 // 품목명검색
                startActivityForResult(intent, 100);
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        val positiveInButtonClick = { dialogInterface: DialogInterface, i: Int ->
            itemSizeString = itemSize.text.toString()
            if(itemSize.getText().toString().equals("") || itemSize.getText().toString() == null){
                Toast.makeText(activity, "수량을 입력해주세요", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            } else {
                storageReturnInsert(itemNameString, customerNumString, storeNumString, locationNumString, itemNumString, itemSizeString, jwt)

                itemNameString = NO_SEARCH
                storNameString = NO_SEARCH
            }
        }
        val positiveOutButtonClick = { dialogInterface: DialogInterface, i: Int ->
            unstoringReturnInsert(itemNameString, customerNumString, storeNumString, locationNumString, itemNumString, itemSizeString, jwt)
        }
        val negativeButtonClick = { dialogInterface: DialogInterface, i: Int ->

        }

        //입고 dialog
        putBtn.setOnClickListener {
            dialogView = View.inflate(view.context, R.layout.in_dialog, null)

            itemName = dialogView.findViewById(R.id.itemName)
            setDate = dialogView.findViewById(R.id.setDate)
            storName = dialogView.findViewById(R.id.storName)
            emp_name = dialogView.findViewById(R.id.emp_name)
            itemSize = dialogView.findViewById(R.id.itemSize)

            setDate = dialogView.findViewById(R.id.setDate)
            putDate = dialogView.findViewById(R.id.putDate)

            val now = System.currentTimeMillis()
            var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN).format(now)

            var name = (activity as MainActivity?)?.getEmpName()
            emp_name.setText(name)

            putDate.setText("반품일자")

            if(itemNameString != NO_SEARCH && storNameString != NO_SEARCH){ //itemInNumString != NO_SEARCH || itemOutNumString != NO_SEARCH)
                setDate.setText(simpleDateFormat)
                itemName.text = itemNameString
                storName.text = storNameString + " / " + locationNameString

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
            inDataPositionList = mutableListOf()
        }

        inReturnRecyclerAdapter.setItemClickListener (object: InReturnRecyclerAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                if(inDataPositionList.contains(position)){
                    inDataPositionList.remove(position)
                } else {
                    inDataPositionList.add(position)
                }
                Log.d("in position list", "$inDataPositionList")
            }
        })

        outReturnRecyclerAdapter.setItemClickListener (object: OutReturnRecyclerAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                if(outDataPositionList.contains(position)){
                    outDataPositionList.removeAt(position)
                } else {
                    outDataPositionList.add(position)
                }
                Log.d("out position list", "$outDataPositionList")
            }
        })

        deleteBtn.setOnClickListener{
            storingReturnDelete()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data) //결과 파싱
        if (scanningResult != null) { //정상적으로 전달
            if (scanningResult.contents != null) { //result 값
                Log.d("Barcode", scanningResult.contents)

                val data = BarcodePostInfo(scanningResult.contents.toString())
                val jsonString = gson.toJson(data)
                val formBody: RequestBody = RequestBody.create(JSON, jsonString)

                val url = "http://101.101.208.223:8080/barcode"
                val request: Request = Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        activity!!.runOnUiThread { Log.d("test", "fail") }
                    }

                    @Throws(IOException::class)
                    override fun onResponse(call: Call, response: Response) {
                        if (response.isSuccessful) {
                            myRequest = response.body!!.string()
                            System.out.println(myRequest)
                            var data : BarcodeInfo = Gson().fromJson(myRequest, BarcodeInfo::class.java)
                            activity!!.runOnUiThread {
                                itemNameString = data.item_nm
                                itemNumString = data.item_cd
                                itemSizeString = data.qty.toString()

                                Log.d("Barcode", data.item_nm + " "
                                        + data.item_cd + " " + data.qty.toString())

                                if(storNameString != NO_SEARCH){
                                    if(buttonState){
                                        storageReturnInsert(itemNameString, customerNumString, storeNumString, locationNumString, itemNumString, itemSizeString, jwt)
                                    } else {
                                        unstoringReturnInsert(itemNameString, customerNumString, storeNumString, locationNumString, itemNumString, itemSizeString, jwt)
                                    }
                                    storNameString = NO_SEARCH
                                    itemNameString = NO_SEARCH
                                    itemSizeString = NO_SEARCH
                                } else {
                                    Toast.makeText(activity, "검색 요소가 부족합니다", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                })
            }
        }

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                100 -> {
                    // 1 : 수주번호, 2 : 발주번호,  3 : 창고, 4 : 품목명, 5 : 바코드
                    when(searchCategory){
                        1 -> {
                            itemInNumString = data!!.getStringExtra("plord_no").toString()
                            customerNameString = data!!.getStringExtra("cust_nm").toString()
                            customerNumString = data!!.getStringExtra("cust_cd").toString()
                            searchCustomer.setText(customerNameString)
                        }
                        2 -> {
                            itemOutNumString = data!!.getStringExtra("ex_requ_no").toString()
                            customerNameString = data!!.getStringExtra("cust_nm").toString()
                            customerNumString = data!!.getStringExtra("cust_cd").toString()
                            searchCustomer.setText(customerNameString)
                        }
                        3 -> {
                            storNameString = data!!.getStringExtra("stor_nm").toString()
                            storeNumString = data!!.getStringExtra("stor_cd").toString()
                            locationNameString = data!!.getStringExtra("loca_nm").toString()
                            locationNumString = data!!.getStringExtra("loca_cd").toString()
                        }
                        4 -> {
                            itemNameString = data!!.getStringExtra("item_nm").toString()
                            itemNumString = data!!.getStringExtra("item_cd").toString()
                        }
                        5 -> {
                            Log.d("getTest", "barcode Get")
                            itemNameString = data!!.getStringExtra("item_nm").toString()
                            itemNumString = data!!.getStringExtra("item_cd").toString()
                            itemSizeString = data!!.getStringExtra("qty").toString()

                            if(storNameString != NO_SEARCH){
                                if(buttonState){
                                    storageReturnInsert(itemNameString, customerNumString, storeNumString, locationNumString, itemNumString, itemSizeString, jwt)
                                } else {
                                    unstoringReturnInsert(itemNameString, customerNumString, storeNumString, locationNumString, itemNumString, itemSizeString, jwt)
                                }
                                storNameString = NO_SEARCH
                                itemNameString = NO_SEARCH
                                itemSizeString = NO_SEARCH
                            } else {
                                Toast.makeText(activity, "검색 요소가 부족합니다", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        } else {
            Toast.makeText(activity, "검색결과없음", Toast.LENGTH_SHORT).show()
        }
    }

    private fun storageReturnInsert(itemNameString : String, customerNumString : String, storeNumString: String, locationNumString: String,
                              itemNumString: String, itemSizeString: String, jwt: String) {
        val data = InPostInfo(customerNumString, storeNumString, locationNumString, itemNumString,
            itemSizeString.toDouble())
        val jsonString = gson.toJson(data)
        System.out.println(jsonString)
        val formBody: RequestBody = RequestBody.create(JSON, jsonString)

        val url = "http://101.101.208.223:8080/storingReturnInsert?jwt=$jwt"
        val request: Request = Request.Builder()
            .url(url)
            .post(formBody)
            .build();

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                activity!!.runOnUiThread { Log.d("test", "fail") }
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    myRequest = response.body!!.string()
                    System.out.println(myRequest)
                    var data : StoringReturnInsertInfo = Gson().fromJson(myRequest, StoringReturnInsertInfo::class.java)
                    activity!!.runOnUiThread {
                        purc_retu_no = data.purc_retu_no

                        Log.d("Return Storing Insert", data.purc_retu_no)

                        inData.add(StoreStateInfo(purc_retu_no, customerNumString, storeNumString, locationNumString, itemNumString, itemNameString,
                            itemSizeString.toDouble()))

                        inReturnRecyclerAdapter.notifyDataSetChanged();
                        Toast.makeText(activity, "$purc_retu_no 반품되었습니다", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun unstoringReturnInsert(itemNameString : String, customerNumString : String, storeNumString: String, locationNumString: String,
                                itemNumString: String, itemSizeString: String, jwt: String) {
        val data = InPostInfo(customerNumString, storeNumString, locationNumString, itemNumString,
            itemSizeString.toDouble())
        val jsonString = gson.toJson(data)
        System.out.println(jsonString)
        val formBody: RequestBody = RequestBody.create(JSON, jsonString)

        val url = "http://101.101.208.223:8080/unstoringReturnInsert?jwt=$jwt"
        val request: Request = Request.Builder()
            .url(url)
            .post(formBody)
            .build();

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                activity!!.runOnUiThread { Log.d("test", "fail") }
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    myRequest = response.body!!.string()
                    System.out.println(myRequest)
                    var data : UnstoringReturnInsertInfo = Gson().fromJson(myRequest, UnstoringReturnInsertInfo::class.java)
                    activity!!.runOnUiThread {
                        ex_retu_no = data.ex_retu_no
                        Log.d("Unstoring Insert", data.ex_retu_no)

                        outData.add(StoreStateInfo(ex_retu_no, customerNumString, storeNumString, locationNumString, itemNumString, itemNameString,
                            itemSizeString.toDouble()))

                        outReturnRecyclerAdapter.notifyDataSetChanged()

                        Toast.makeText(activity, "$ex_retu_no 반품되었습니다", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun storingReturnDelete() {
        if (buttonState) {
            val url = "http://101.101.208.223:8080/storingReturnDelete"
            var jsonString: String = ""
            var deleteQty : Int = 0

            for (i in 0..inDataPositionList.size - 1) {
                val no = inData[inDataPositionList[i]].no
                val itemNumString = inData[inDataPositionList[i]].item_cd
                val itemSizeString = inData[inDataPositionList[i]].qty

                val data = DeleteStateInfo(no, itemNumString, itemSizeString)
                jsonString = jsonString + gson.toJson(data) + ","
                deleteQty++
            }
            jsonString = jsonString.substring(0, jsonString.length - 1)
            val jsonArrayString = "[$jsonString]"
            System.out.println(jsonArrayString)
            val formBody: RequestBody = RequestBody.create(JSON, jsonArrayString)

            val request: Request = Request.Builder()
                .url(url)
                .post(formBody)
                .build();

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    activity!!.runOnUiThread { Log.d("test", "fail") }
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        myRequest = response.body!!.string()
                        val requestList = "[$myRequest]"
                        System.out.println(myRequest)
                        var data: List<DeletePostInfo> =
                            Gson().fromJson(requestList, Array<DeletePostInfo>::class.java).toList()
                        activity!!.runOnUiThread {
                            System.out.println(data[0].result)
                            if (data[0].result == "0") {
                                for (i in 0..inDataPositionList.size - 1) {
                                    inData.removeAt(inDataPositionList[i])
                                    for(i in 0..inDataPositionList.size - 1) {
                                        inDataPositionList[i]--
                                    }
                                }
                                inReturnRecyclerAdapter.checkboxReset()
                                Toast.makeText(activity, "삭제되었습니다", Toast.LENGTH_SHORT).show()
                                inDataPositionList = mutableListOf()
                                inReturnRecyclerAdapter.notifyDataSetChanged()
                            } else if (data[0].result == "1") {
                                Toast.makeText(activity, "삭제에 실패했습니다", Toast.LENGTH_SHORT).show()
                            } else {

                            }
                        }
                    } else {
                        activity!!.runOnUiThread { Log.d("test", "response fail") }
                    }
                }
            })
        } else {
            val url = "http://101.101.208.223:8080/unstoringReturnDelete"
            var jsonString = ""
            var deleteQty = 0

            for (i in 0..outDataPositionList.size - 1) {
                val no = outData[outDataPositionList[i]].no
                val itemNumString = outData[outDataPositionList[i]].item_cd
                val itemSizeString = outData[outDataPositionList[i]].qty

                val data = DeleteStateInfo(no, itemNumString, itemSizeString)
                jsonString = jsonString + gson.toJson(data) + ","
                deleteQty++
            }
            jsonString = jsonString.substring(0, jsonString.length - 1)
            val jsonArrayString = "[$jsonString]"
            System.out.println(jsonArrayString)
            val formBody: RequestBody = RequestBody.create(JSON, jsonArrayString)

            val request: Request = Request.Builder()
                .url(url)
                .post(formBody)
                .build();

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    activity!!.runOnUiThread { Log.d("test", "fail") }
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        myRequest = response.body!!.string()
                        val requestList = "[$myRequest]"
                        System.out.println(myRequest)
                        var data: List<DeletePostInfo> =
                            Gson().fromJson(requestList, Array<DeletePostInfo>::class.java).toList()
                        activity!!.runOnUiThread {
                            System.out.println(data[0].result)
                            if (data[0].result == "0") {
                                for (i in 0..outDataPositionList.size - 1) {
                                    outData.removeAt(outDataPositionList[i])
                                    for(i in 0..outDataPositionList.size - 1) {
                                        outDataPositionList[i]--
                                    }
                                }
                                outReturnRecyclerAdapter.checkboxReset()
                                Toast.makeText(activity, "삭제되었습니다", Toast.LENGTH_SHORT).show()
                                outDataPositionList = mutableListOf()
                                outReturnRecyclerAdapter.notifyDataSetChanged()
                            } else if (data[0].result == "1") {
                                Toast.makeText(activity, "삭제에 실패했습니다", Toast.LENGTH_SHORT).show()
                            } else {

                            }
                        }
                    } else {
                        activity!!.runOnUiThread { Log.d("test", "response fail") }
                    }
                }
            })
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