package com.example.android_w7_task2

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var rbEven: RadioButton
    private lateinit var rbOdd: RadioButton
    private lateinit var rbSquare: RadioButton
    private lateinit var btnShow: Button
    private lateinit var lvResults: ListView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo các thành phần giao diện
        etNumber = findViewById(R.id.etNumber)
        radioGroup = findViewById(R.id.radioGroup)
        rbEven = findViewById(R.id.rbEven)
        rbOdd = findViewById(R.id.rbOdd)
        rbSquare = findViewById(R.id.rbSquare)
        btnShow = findViewById(R.id.btnShow)
        lvResults = findViewById(R.id.lvResults)
        tvError = findViewById(R.id.tvError)

        // Thiết lập sự kiện cho nút Show
        btnShow.setOnClickListener {
            val input = etNumber.text.toString().trim()

            // Kiểm tra tính hợp lệ của dữ liệu nhập
            if (input.isEmpty() || input.toIntOrNull() == null || input.toInt() <= 0) {
                tvError.text = "Vui lòng nhập số nguyên dương hợp lệ."
                tvError.visibility = View.VISIBLE
                lvResults.adapter = null  // Xóa kết quả cũ nếu có
            } else {
                tvError.visibility = View.GONE
                val n = input.toInt()
                val results = ArrayList<Int>()

                // Xác định loại số và thêm vào danh sách kết quả
                when {
                    rbEven.isChecked -> {
                        for (i in 0..n step 2) results.add(i)
                    }
                    rbOdd.isChecked -> {
                        for (i in 1..n step 2) results.add(i)
                    }
                    rbSquare.isChecked -> {
                        var i = 0
                        while (i * i <= n) {
                            results.add(i * i)
                            i++
                        }
                    }
                    else -> {
                        // Nếu không có loại nào được chọn
                        tvError.text = "Vui lòng chọn một loại số."
                        tvError.visibility = View.VISIBLE
                        lvResults.adapter = null
                        return@setOnClickListener
                    }
                }

                // Thiết lập adapter cho ListView để hiển thị kết quả
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
                lvResults.adapter = adapter
            }
        }
    }
}
