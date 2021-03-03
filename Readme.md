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

#### 2. AdjustableEditText
This component extends `AppCompatEditText`, and add additional functionality such as the flexibility to define key event, validation rule, etc.

This is how to use the AdjustableEditText component. Let say we want to apply validation rule to check whether the text is empty or not, and when when key enter pressed.
Note : To apply enter key pressed, we need to set `android:maxLines="1"` and `android:inputType="text"` in our `AdjustableEditText`.

Here is the sample code to achieve both requirements above.
```
<!-- in our activity_main.xml -->
<com.otnieldocs.uicontrol.edittext.AdjustableEditText
        android:id="@+id/adjustable_edit_text1"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:hint="Tap to edit"
        android:inputType="text"
        android:maxLines="1"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <com.otnieldocs.uicontrol.edittext.AdjustableEditText
        android:id="@+id/adjustable_edit_text2"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:hint="Tap to edit"
        android:inputType="text"
        android:maxLines="1"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```
```
<!-- MainActivity -->
class FormControlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_control)

        adjustable_edit_text1.config(
            EditTextConfigDto(
                activateKeyEvent = mapOf(
                    KeyEvent.KEYCODE_ENTER to onEnterKeyPressed
                ),
                activateValidationRule = validateTextNotEmpty
            )
        ).applyConfig()

        adjustable_edit_text2.config(
            EditTextConfigDto(
                activateKeyEvent = mapOf(
                    KeyEvent.KEYCODE_ENTER to onEnterKeyPressed
                ),
                activateValidationRule = validateTextNotEmpty
            )
        )
    }

    private val onEnterKeyPressed: () -> Unit = {
        Toast.makeText(this, "Enter key pressed", Toast.LENGTH_SHORT).show()
    }

    private val validateTextNotEmpty: () -> Unit = {
        when {
            adjustable_edit_text1.text.toString().isEmpty() -> {
                Toast.makeText(
                    this,
                    "This value can't be empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
            adjustable_edit_text1.text.toString().length < 3 -> {
                Toast.makeText(
                    this,
                    "This value have to be larger or equals than 3",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                Toast.makeText(
                    this,
                    "This value is valid",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
```
#### 3. AdjustableBottomSheetFragment
Wrap the BottomSheetDialog fragment implementation, reduce the trivial things, so that we can hit the important things directly.
##### How to use
First, create the bottom sheet layout
```
<!--main_bottom_sheet_option_fragment.xml-->
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/bottom_sheet_option_item1"
        android:layout_width="0dp"
        android:layout_height="48dp"
        android:gravity="center"
        android:text="Menu Item 1"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/bottom_sheet_option_item2"
        android:layout_width="0dp"
        android:layout_height="48dp"
        android:gravity="center"
        android:text="Menu Item 2"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/bottom_sheet_option_item1" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
Then we create the bottom sheet dialog fragment in our activity like this
```
class BottomSheetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)

        btn_option_bottom_sheet.setOnClickListener {
            AdjustableBottomSheetFragment.newInstance(
                bundle = null,
                layoutRes = R.layout.main_bottom_sheet_option_fragment,
                setupViews = ::setupBottomSheetOptionView
            ).show(supportFragmentManager, BottomSheetActivity::class.java.simpleName)
        }
    }

    private fun setupBottomSheetOptionView(view: View, bundle: Bundle?) {
        with(view) {
            bottom_sheet_option_item1.setOnClickListener {
                Toast.makeText(this@BottomSheetActivity, "Menu Item 1", Toast.LENGTH_SHORT).show()
            }

            bottom_sheet_option_item2.setOnClickListener {
                Toast.makeText(this@BottomSheetActivity, "Menu Item 2", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
```