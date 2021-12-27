package ru.mvideo.android.app.support.view


import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat

class ClearableEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : EditText(context, attrs), View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {

    companion object {
        private const val RIGHT_DRAWABLE_INDEX = 2
    }

    private var touchListener: OnTouchListener? = null
    private var focusListener: OnFocusChangeListener? = null
    private val hintText = hint
    private var closeDrawable: Drawable? = null

    init {
        init()
    }

    override fun setOnTouchListener(l: OnTouchListener) {
        this.touchListener = l
    }

    override fun setOnFocusChangeListener(f: OnFocusChangeListener) {
        this.focusListener = f
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        compoundDrawablesRelative[RIGHT_DRAWABLE_INDEX]?.let {
            val x = event.x.toInt()
            val y = event.y.toInt()
            val left = width - paddingRight - it.intrinsicWidth
            val right = width
            val tappedX = x in left..right && y >= 0 && y <= bottom - top
            if (tappedX) {
                if (event.action == MotionEvent.ACTION_UP) {
                    setText("")
                }
                return true
            }
        }
        return touchListener?.onTouch(v, event) ?: false
    }

    override fun onFocusChange(v: View, hasFocus: Boolean) {
        if (hasFocus) {
            setClearIconVisible(!text.isEmpty())
        } else {
            setClearIconVisible(false)
        }
        focusListener?.onFocusChange(v, hasFocus)
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (isFocused) {
            setClearIconVisible(!(text?.isEmpty() ?: true))
        }
    }

    override fun afterTextChanged(s: Editable?) {
        //pass
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // pass
    }

    override fun setCompoundDrawablesRelative(start: Drawable?, top: Drawable?, end: Drawable?, bottom: Drawable?) {
        end?.let { closeDrawable = it }
    }

    private fun init() {
        super.setOnTouchListener(this)
        super.setOnFocusChangeListener(this)

        closeDrawable = compoundDrawablesRelative[RIGHT_DRAWABLE_INDEX]
            ?: ContextCompat.getDrawable(context, android.R.drawable.presence_offline)

        addTextChangedListener(this)
        setClearIconVisible(false)
    }

    private fun setClearIconVisible(visible: Boolean) {
        val cd = compoundDrawablesRelative
        val displayed = cd[2]
        val wasVisible = displayed != null
        if (visible != wasVisible) {
            hint = if (visible) "" else hintText
            val x = if (visible) closeDrawable else null
            super.setCompoundDrawablesRelative(cd[0], cd[1], x, cd[3])
        }
    }
}
