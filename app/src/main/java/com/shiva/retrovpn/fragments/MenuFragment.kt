package com.shiva.retrovpn.fragments

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.shiva.retrovpn.R
import com.shiva.retrovpn.activities.FaqActivity
import com.shiva.retrovpn.activities.LoadingWebDataActivity
import com.shiva.retrovpn.activities.SubscriptionActivity
import com.shiva.retrovpn.databinding.FragmentMenuBinding
import com.shiva.retrovpn.dialogs.RateDialog

class MenuFragment : Fragment() {

    private lateinit var mContext: Context
    private var binding: FragmentMenuBinding? = null



    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding!!.drawerPremiumItem.setOnClickListener {
            val subscriptionActivity = Intent(mContext, SubscriptionActivity::class.java)
            startActivity(subscriptionActivity)
        }
        binding!!.drawerShareItem.setOnClickListener {
            shareApp()
        }
        binding!!.drawerPrivacyItem.setOnClickListener {
            privacyPolicyLink()
        }
        binding!!.drawerAboutItem.setOnClickListener {
            showAboutDialog()
        }
        binding!!.drawerRateItem.setOnClickListener {
            val rateDialog = RateDialog(requireActivity())
            rateDialog.show()
        }
        binding!!.drawerFaqItem.setOnClickListener {
            val faq_intent = Intent(mContext, FaqActivity::class.java)
            startActivity(faq_intent)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun showAboutDialog() {
        val dialog = Dialog(mContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.dialog_about)
        dialog.setCancelable(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        (dialog.findViewById<View>(R.id.bt_close) as RelativeLayout).setOnClickListener { dialog.dismiss() }
        dialog.show()
        dialog.window!!.attributes = lp
    }

    private fun privacyPolicyLink() {
        val webActivity = Intent(mContext, LoadingWebDataActivity::class.java)
        webActivity.putExtra("activityName", "Privacy Policy")
        webActivity.putExtra("webLink", resources.getString(R.string.privacy_policy_link))
        startActivity(webActivity)
    }

    private fun shareApp() {
        val msg = StringBuilder()
        msg.append(getString(R.string.share_msg))
        msg.append("\n")
        msg.append("https://play.google.com/store/apps/details?id=com.snaptube.savevideos.all.videos.downloader2020.allvideodownload")
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg.toString())
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
    }


}