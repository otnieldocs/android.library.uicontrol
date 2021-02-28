package com.otnieldocs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.otnieldocs.uicontrol.recyclerview.BaseRecyclerData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val t: List<String> = emptyList()

        val teachers = mutableListOf<TeacherDto>()
        teachers.add(TeacherDto("Charlie", "TCH-22001"))
        teachers.add(TeacherDto("Vanessa", "TCH-22002"))
        val car = mutableListOf<CarDto>()
        car.add(CarDto("Bonnie", "D1002 BE"))
        car.add(CarDto("Dona", "D1003 CX"))
        val recyclerData = mutableListOf<BaseRecyclerData>()
        recyclerData.addAll(teachers)
        recyclerData.addAll(car)

        val adapter1 = MyCompositeListAdapter()
        adapter1.setData(recyclerData)

        with(main_recyclerview) {
            itemAnimator = null
            adapter = adapter1
        }
    }
}