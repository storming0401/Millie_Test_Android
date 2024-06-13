package com.kwj.presentation.ui.common

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.core.view.isVisible
import com.kwj.presentation.databinding.LayoutDialogBinding

/**
 * 공통으로 사용하는 Dialog를 제공하기 위한 클래스입니다.
 *
 * @author (김위진)
 * @since (2024-06-13)
 */
class CommonDialog constructor(
    context: Context,
    val content: String,
    private val isShowCancel: Boolean,
    private val okButtonName: String,
    private val cancelButtonName: String
) : Dialog(context) {

    private var dialogClickListener: DialogClickListener? = null
    private var _binding: LayoutDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCanceledOnTouchOutside(false)

        _binding = LayoutDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)

        binding.content = content
        binding.executePendingBindings()

        binding.btCancel.isVisible = isShowCancel
        binding.btCancel.apply {
            text = cancelButtonName
            setOnClickListener {
                dismiss()
            }
        }

        binding.btOk.apply {
            text = okButtonName
            setOnClickListener {
                dialogClickListener?.onClick()
                dismiss()
            }
        }
    }

    override fun dismiss() {
        super.dismiss()
        _binding = null
    }

    interface DialogClickListener {
        fun onClick()
    }

    fun setItemClickListener(dialogClickListener: DialogClickListener) {
        this.dialogClickListener = dialogClickListener
    }
}