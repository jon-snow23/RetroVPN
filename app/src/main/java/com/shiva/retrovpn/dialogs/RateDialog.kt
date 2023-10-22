package com.shiva.retrovpn.dialogs


import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.facebook.shimmer.BuildConfig
import com.shiva.retrovpn.R
import com.shiva.retrovpn.activities.LoadingWebDataActivity


class RateDialog(private val activity: Activity) : Dialog(activity), View.OnClickListener {
    private val imageViewStars = arrayOfNulls<ImageView>(5)
    private var linear_layout_RatingBar: ViewGroup? = null
    private var star_number: Int
    private var text_view_submit: TextView? = null
    private var imageViewRate: ImageView? = null
    var dialogRatingButtonPrivacy: RelativeLayout? = null
    var dialogRatingButtonAbout: RelativeLayout? = null
    var dialogRatingButtonNegative: RelativeLayout? = null
    private var textViewTitle: TextView? = null
    private var textViewDesc: TextView? = null

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_rate)
        setCancelable(false)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initView()
        linear_layout_RatingBar!!.startAnimation(
            AnimationUtils.loadAnimation(
                activity, R.anim.shake
            )
        )
        star_number = 0
    }

    /*
    * Initialize the Views of the Dialog
    * */
    private fun initView() {
        text_view_submit = findViewById(R.id.text_view_submit)
        linear_layout_RatingBar = findViewById(R.id.linear_layout_RatingBar)
        imageViewRate = findViewById<ImageView>(R.id.dialog_rating_icon)
        textViewTitle = findViewById(R.id.textViewRateTitle)
        textViewDesc = findViewById(R.id.textViewRate)
        dialogRatingButtonAbout = findViewById(R.id.dialog_rating_button_about)
        dialogRatingButtonNegative = findViewById(R.id.dialog_rating_button_negative)
        dialogRatingButtonPrivacy = findViewById(R.id.dialog_rating_button_privacy)

        val image_view_star_1 = findViewById<ImageView>(R.id.image_view_star_1)
        val image_view_star_2 = findViewById<ImageView>(R.id.image_view_star_2)
        val image_view_star_3 = findViewById<ImageView>(R.id.image_view_star_3)
        val image_view_star_4 = findViewById<ImageView>(R.id.image_view_star_4)
        val image_view_star_5 = findViewById<ImageView>(R.id.image_view_star_5)
        image_view_star_1.setOnClickListener(this)
        image_view_star_2.setOnClickListener(this)
        image_view_star_3.setOnClickListener(this)
        image_view_star_4.setOnClickListener(this)
        image_view_star_5.setOnClickListener(this)
        imageViewStars[0] = image_view_star_1
        imageViewStars[1] = image_view_star_2
        imageViewStars[2] = image_view_star_3
        imageViewStars[3] = image_view_star_4
        imageViewStars[4] = image_view_star_5
        dialogRatingButtonAbout?.setOnClickListener(this)
        dialogRatingButtonPrivacy?.setOnClickListener(this)
        dialogRatingButtonNegative?.setOnClickListener(this)
        text_view_submit?.setOnClickListener(this)
        textViewTitle?.setVisibility(View.GONE)
    }

    /*
    * Perform the Clicks of the Dialog
    * */
    override fun onClick(view: View) {
        val id = view.id
        if (id != R.id.text_view_submit) {
            when (id) {
                R.id.image_view_star_1 -> {
                    star_number = 1
                    imageViewRate!!.setImageResource(R.drawable.ic_rate1_icon)
                    textViewTitle!!.text = context.resources.getString(R.string.rating_title_1)
                    textViewTitle!!.visibility = View.VISIBLE
                    textViewDesc!!.text = context.resources.getString(R.string.rating_text_1)
                    setStarBar()
                    return
                }

                R.id.image_view_star_2 -> {
                    star_number = 2
                    imageViewRate!!.setImageResource(R.drawable.ic_rate2_icon)
                    textViewTitle!!.text = context.resources.getString(R.string.rating_title_1)
                    textViewTitle!!.visibility = View.VISIBLE
                    textViewDesc!!.text = context.resources.getString(R.string.rating_text_1)
                    setStarBar()
                    return
                }

                R.id.image_view_star_3 -> {
                    star_number = 3
                    imageViewRate!!.setImageResource(R.drawable.ic_rate3_icon)
                    textViewTitle!!.text = context.resources.getString(R.string.rating_title_1)
                    textViewTitle!!.visibility = View.VISIBLE
                    textViewDesc!!.text = context.resources.getString(R.string.rating_text_1)
                    setStarBar()
                    return
                }

                R.id.image_view_star_4 -> {
                    star_number = 4
                    imageViewRate!!.setImageResource(R.drawable.ic_rate4_icon)
                    textViewTitle!!.text = context.resources.getString(R.string.rating_title_2)
                    textViewTitle!!.visibility = View.VISIBLE
                    textViewDesc!!.text = context.resources.getString(R.string.rating_text_4)
                    setStarBar()
                    return
                }

                R.id.image_view_star_5 -> {
                    star_number = 5
                    imageViewRate!!.setImageResource(R.drawable.ic_rate5_icon)
                    textViewTitle!!.text = context.resources.getString(R.string.rating_title_2)
                    textViewTitle!!.visibility = View.VISIBLE
                    textViewDesc!!.text = context.resources.getString(R.string.rating_text_4)
                    setStarBar()
                    return
                }

                R.id.dialog_rating_button_about -> {
                    dismiss()
                    showAboutDialog()
                    return
                }

                R.id.dialog_rating_button_privacy -> {
                    dismiss()
                    privacyPolicyLink()
                    dismiss()
                    return
                }

                R.id.dialog_rating_button_negative -> {
                    dismiss()
                    return
                }

                else -> {}
            }
        } else if (star_number >= 4) {
            activity.startActivity(
                Intent(
                    "android.intent.action.VIEW",
                    Uri.parse("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)
                )
            )
            dismiss()
        } else if (star_number > 0) {
            dismiss()
            showFeedbackDialog()
        } else {
            linear_layout_RatingBar!!.startAnimation(
                AnimationUtils.loadAnimation(
                    activity, R.anim.shake
                )
            )
        }
    }

    /*
    * Show the feedback dialog to the user
    * to perform the feedback about the Liberty VPN.
    * */
    private fun showFeedbackDialog() {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_feedback)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val positiveButton =
            dialog.findViewById<RelativeLayout>(R.id.dialog_rating_button_feedback_submit)
        val negativeButton =
            dialog.findViewById<RelativeLayout>(R.id.dialog_rating_button_feedback_cancel)
        val editText = dialog.findViewById<EditText>(R.id.dialog_rating_feedback)
        positiveButton.setOnClickListener { v: View? ->
            if (editText.text.toString().isEmpty() || editText.text
                    .toString().length < 10
            ) {
//                    Helper.showToast(getContext(), "Please explain the issue");
            } else {
                // go to email
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:")
                intent.putExtra(
                    Intent.EXTRA_EMAIL,
                    arrayOf<String>(
                        context.resources.getString(R.string.developers_email)
                    )
                )
                intent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    context.resources.getString(R.string.feedback_email_subject)
                )
                intent.putExtra(Intent.EXTRA_TEXT, editText.text.toString())
                try {
//                        startActivity(Intent.createChooser(intent, "Send E-mail"));
                } catch (ex: ActivityNotFoundException) {
//                        Helper.showToast(getContext(), "No email app found.");
                } catch (ex: Exception) {
//                        Helper.showToast(getContext(), "Network Error");
                }
            }
        }
        negativeButton.setOnClickListener { v: View? -> dialog.dismiss() }
        dialog.show()
    }

    /*
    * Load the privacy policy Link in separate activity
    * */
    private fun privacyPolicyLink() {
        val webActivity = Intent(context, LoadingWebDataActivity::class.java)
        webActivity.putExtra("activityName", "Privacy Policy")
        webActivity.putExtra("webLink", activity.getString(R.string.privacy_policy_link))
        activity.startActivity(webActivity)
    }

    /*
    * Show the About dialog
    * */
    private fun showAboutDialog() {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.dialog_about)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        (dialog.findViewById<View>(R.id.bt_close) as RelativeLayout).setOnClickListener { v: View? -> dialog.dismiss() }
        dialog.show()
        dialog.window!!.attributes = lp
    }

    /*
    * Set the Stars and perform the corresponding action.
    * */
    private fun setStarBar() {
        for (i in imageViewStars.indices) {
            if (i < star_number) {
                imageViewStars[i]!!.setImageResource(R.drawable.ic_round_star_on)
            } else {
                imageViewStars[i]!!.setImageResource(R.drawable.ic_round_star)
            }
        }
        if (star_number < 4) {
            text_view_submit!!.setText(R.string.rating_dialog_feedback_title)
        } else {
            text_view_submit!!.setText(R.string.submit_text)
        }
    }
}

