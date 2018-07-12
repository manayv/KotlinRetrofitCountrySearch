package de.manayv.kotlinretrofitcountrysearch

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener {
            if (edit_search.text.toString().isNotEmpty())
                beginSearch(edit_search.text.toString().trim().toUpperCase())
        }
    }


    @SuppressLint("SetTextI18n")
    private fun beginSearch(searchString: String) {
        txt_search_result.text = ""

        disposable = CountryApiService.create().getForIso2Code(searchString)
                .subscribeOn(Schedulers.io())
                // Only the leave struct in the JSON tree (JSON key "result") is interesting
                .map { responseRoot -> responseRoot.restResponse?.country }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        /* onSuccess lambda */
                        { country ->
                            if (country != null)
                                txt_search_result.text = "Country ${country.name} / " +
                                        "${country.isoCode2} / " +
                                        "${country.isoCode3} "
                            else
                                txt_search_result.text = "No Country not found for ISO code"
                        },
                        /* onError lambda */
                        { Toast.makeText(this, it.message, Toast.LENGTH_LONG).show() }
                )
    }


    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
