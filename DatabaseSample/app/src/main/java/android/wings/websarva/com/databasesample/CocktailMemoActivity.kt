package android.wings.websarva.com.databasesample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

/**
 * カクテルの感想をメモするアクティビティ
 */
class CocktailMemoActivity : AppCompatActivity() {

    var _cocktailId = -1
    lateinit var _tvCocktailName: TextView
    lateinit var _cocktailName: String
    lateinit var _btnSave : Button

    /**
     * カクテル名一覧を表示するListViewをタップすると
     * カクテル名を取得してグローバル変数へ格納する
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_memo)

        _tvCocktailName = findViewById(R.id.tvCocktailName)
        _btnSave = findViewById(R.id.btnSave)
        val lvCocktail = findViewById<ListView>(R.id.lvCocktail)
        lvCocktail.setOnItemClickListener { parent, view, position, id ->
            _cocktailId = position
            _cocktailName = parent.getItemAtPosition(_cocktailId) as String
            _tvCocktailName.text = _cocktailName
            _btnSave.isEnabled = true

            getCocktailNoteFromDB()
        }
    }

    /**
     * 保存ボタンのハンドラ
     *
     */
    fun onSaveButtonClick(view: View){
        val etNote = findViewById<EditText>(R.id.etNote)
        val note = etNote.text.toString()
        Log.d("data", "note: ${note}")
        /**
         * データベースアクセス
         * 選択されたカクテルデータのメモを削除した後に、インサートを実施。
         */
        val helper = DatabaseHelper(this, "cocktailmemo.db", null, 1)
        val db = helper.writableDatabase
        try{
            val sqlDelete = "DELETE FROM cocktailmemo WHERE _id = ?"
            val stmt_delete = db.compileStatement(sqlDelete)
            stmt_delete.bindLong(1, _cocktailId.toLong())
            stmt_delete.executeUpdateDelete()

            val sqlInsert = "INSERT INTO cocktailmemo(_id, name, note) VALUES (?, ?, ?)"
            val stmt_insert = db.compileStatement(sqlInsert)
            stmt_insert.bindLong(1, _cocktailId.toLong())
            stmt_insert.bindString(2, _cocktailName)
            stmt_insert.bindString(3, note)
            stmt_insert.executeInsert()

        }finally {
            db.close()
        }
        // カクテル名を未選択に変更
        _tvCocktailName.text = getString(R.string.tv_name)
        etNote.setText("")
        _btnSave.isEnabled = false
    }

    private fun getCocktailNoteFromDB(){
        val helper = DatabaseHelper(this, "cocktailmemo.db", null, 1)
        val db = helper.writableDatabase
        try {
            var sql = "SELECT * FROM cocktailmemo WHERE _id = ${_cocktailId}"
            val cursor = db.rawQuery(sql, null)
            var note = ""
            while(cursor.moveToNext()){
                var idxNote = cursor.getColumnIndex("note")
                note = cursor.getString(idxNote)
            }
            var etNote = findViewById<EditText>(R.id.etNote)
            etNote.setText(note)
        }finally {
            db.close()
        }

    }
}
