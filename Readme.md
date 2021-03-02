# Androui Control

![](https://img.shields.io/badge/version-0.0.1-blue.svg) ![](https://travis-ci.org/joemccann/dillinger.svg?branch=master) 

Android custom component wrapper such as composite recyclerview, form element, dropdown, etc.

### Installation
TODO : Will publish this library to public maven repository

### Available UI-Control
Here are current ui-control objects available to use

#### 1. CompositeRecyclerAdapter
Generic class that inherit RecyclerViewAdapter, which accept generic type of T as child of BaseRecyclerData.
Every data that want to be rendered in composite recycler view, has to extend BaseRecyclerData.
##### How to use
First of all, create the DTO / Pojo class that describe what data you need. 
Let say in your `RecyclerView`, you need to render both teacher and car object.
Create `CarDto` and `TeacherDto` class this way:

```
data class CarDto(
    val name: String = "",
    val policeNumber: String = ""
) : BaseRecyclerData()

data class TeacherDto(
    val name: String = "",
    val teacherId: String = ""
) : BaseRecyclerData()
```

The next step is create the view holders for both CarDto and TeacherDto, let say CarViewHolder and TeacherViewHolder.
These are the declaration for both class:
```
class CarViewHolder(itemView: View): BaseCompositeRecyclerViewHolder<CarDto>(itemView) {

    override fun bind(item: BaseRecyclerData, position: Int) {
        val carDto = item.cast<CarDto>()

        with(itemView) {
            main_textview_car_itemname.text = carDto.name
            main_textview_car_itempolicenumber.text = carDto.policeNumber
        }
    }
}

class TeacherViewHolder(itemView: View) : BaseCompositeRecyclerViewHolder<CarDto>(itemView) {
    override fun bind(item: BaseRecyclerData, position: Int) {
        val teacherDto = item.cast<TeacherDto>()

        with(itemView) {
            main_textview_teacher_itemname.text = teacherDto.name
        }
    }

}
```

Then create the composite list adapter this way:
```
class MyCompositeListAdapter : CompositeRecyclerAdapter<BaseRecyclerData>() {
    override fun getDataType(data: BaseRecyclerData): Int {
        return when(data) {
            is TeacherDto -> TEACHER
            else -> CAR
        }
    }

    override fun getViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseCompositeRecyclerViewHolder<BaseRecyclerData> {
        return when(viewType) {
            TEACHER -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.main_item_teacher, parent, false)
                TeacherViewHolder(view)
            }
            else -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.main_item_car, parent, false)
                CarViewHolder(view)
            }
        }
    }

    companion object {
        const val TEACHER = 0
        const val CAR = 1
    }
}
```

We create MyCompositeListAdapter accepts every object that extends BaseRecyclerData (as our CarDto and TeacherDto are extend BaseRecyclerData respectively).
Then we define the view types for each object we have in `getDataType(data: BaseRecyclerData)` function. TeacherDto is going to 1 and CarDto going to 2.
In the getViewHolder(parent: ViewGroup, viewType: Int) section, we describe how to inflate our views based on what data type we had.

That's it. That's all we have to do to make our composite list adapter.