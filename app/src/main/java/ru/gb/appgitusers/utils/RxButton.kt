package ru.gb.appgitusers.utils

import android.content.Context
import androidx.core.view.marginBottom
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.gb.appgitusers.R

class RxButton(context: Context) : FloatingActionButton(context) {
    private val publishSbj = PublishSubject.create<String>()
    val observable: Observable<String> = publishSbj.hide()

    init {
        setImageResource(R.drawable.ic_baseline_refresh_24)
        id = R.id.rxButtonId

        setOnClickListener {
            publishSbj.onNext("Test")
        }
    }

}