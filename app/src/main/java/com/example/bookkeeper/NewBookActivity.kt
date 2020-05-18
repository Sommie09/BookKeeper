package com.example.bookkeeper

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.activity_new_book.*
import org.w3c.dom.Text

class NewBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)

        txvLastUpdated.visibility = View.INVISIBLE

        bSave.setOnClickListener {
            val resultIntent = Intent()

            if(TextUtils.isEmpty(etAuthorName.text) || TextUtils.isEmpty(etBookName.text) || TextUtils.isEmpty(etDescription.text)){
                setResult(Activity.RESULT_CANCELED, resultIntent)
            }else{
                val author = etAuthorName.text.toString()
                val book = etBookName.text.toString()
                val description = etDescription.text.toString()

                resultIntent.putExtra(NEW_AUTHOR, author)
                resultIntent.putExtra(NEW_BOOK, book)
                resultIntent.putExtra(NEW_DESCRIPTION, description)
                setResult(Activity.RESULT_OK, resultIntent)

            }
            finish()
        }

        bCancel.setOnClickListener {
            finish()
        }
    }
    companion object{
        const val NEW_AUTHOR = "new_author"
        const val NEW_BOOK = "new_book"
        const val NEW_DESCRIPTION = "new_description"
    }
}
