package android.wings.websarva.com.listviewsample2

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.Toast

/**
 * ダイアログ生成処理を記述
 *
 */
class OrderConfirmDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.dialog_title)
        builder.setMessage(R.string.dialog_msg)
        // ダイアログのアクションボタンをタップしたときの処理
        builder.setPositiveButton(R.string.dialog_btn_ok, DialogInterface.OnClickListener { dialog, which ->
            var msg = R.string.dialog_ok_toast
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        })
        builder.setNegativeButton(R.string.dialog_btn_ng, DialogInterface.OnClickListener { dialog, which ->
            var msg = R.string.dialog_ng_toast
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        })
        builder.setNeutralButton(R.string.dialog_btn_nu, DialogInterface.OnClickListener { dialog, which ->
            var msg = R.string.dialog_nu_toast
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        })
        return builder.create()
    }
}