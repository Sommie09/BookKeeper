package com.example.bookkeeper

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao {

    @Insert
    fun insert(book: Book)

    @get: Query("SELECT * FROM books")
    val allBooks: LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE book LIKE :searchString OR author LIKE :searchString")
    fun getBooksByBookOrAuthor(searchString: String) : LiveData<List<Book>>

    @Update
    fun update(book: Book)

    @Delete
    fun delete(book: Book)


}