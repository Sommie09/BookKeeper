package com.example.bookkeeper

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class BookRepository(application: Application) {

    private val bookDao: BookDao
    val allBooks: LiveData<List<Book>>

    init {
        val bookDB = BookRoomDatabase.getDatabase(application)
        bookDao = bookDB!!.bookDao()
        allBooks = bookDao.allBooks
    }

    fun getBooksByBookOrAuthor(searchQuery: String) : LiveData<List<Book>>?{
        return getBooksByBookOrAuthor(searchQuery)
    }


    fun insert(book: Book){
       InsertAsyncTask(bookDao).execute(book)
    }

    fun update(book: Book){
        UpdateAsyncTask(bookDao).execute(book)
    }

    fun delete(book: Book){
        DeleteAsyncTask(bookDao).execute(book)

    }

    companion object {
        private class InsertAsyncTask(private val bookDao: BookDao) : AsyncTask<Book, Void, Void>()  {

            override fun doInBackground(vararg books: Book): Void? {
                bookDao.insert(books[0])
                return null
            }
        }

        private class UpdateAsyncTask(private val bookDao: BookDao) : AsyncTask<Book, Void, Void>()  {

            override fun doInBackground(vararg books: Book): Void? {
                bookDao.update(books[0])
                return null
            }
        }

        private class DeleteAsyncTask(private val bookDao: BookDao) : AsyncTask<Book, Void, Void>()  {

            override fun doInBackground(vararg books: Book): Void? {
                bookDao.delete(books[0])
                return null
            }
        }


    }


}