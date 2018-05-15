package com.horical.kmvparchitect.ui.feed.blogs

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.horical.kmvparchitect.R
import com.horical.kmvparchitect.data.db.model.Blog
import com.horical.kmvparchitect.ui.base.BaseViewHolder
import com.horical.kmvparchitect.utils.AppLogger
import com.kev.karchitect.utils.images.ImageLoader
import kotlinx.android.synthetic.main.item_blog_empty_view.view.*
import kotlinx.android.synthetic.main.item_blog_view.view.*

class BlogAdapter constructor(private val mBlogList: MutableList<Blog>?) : RecyclerView.Adapter<BaseViewHolder>() {

    val VIEW_TYPE_EMPTY = 0
    val VIEW_TYPE_NORMAL = 1
    var mListener: BlogAdapterListener? = null

    interface BlogAdapterListener {
        fun onRetryClicked()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            VIEW_TYPE_EMPTY -> {
                return EmptyHolder(LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_blog_empty_view,
                                parent,
                                false))
            }
            else -> {
                return BlogHolder(LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_blog_view,
                                parent,
                                false))
            }
        }
    }

    override fun getItemCount(): Int {
        if (mBlogList != null && mBlogList.isNotEmpty()) {
            return mBlogList.size
        } else {
            return 1
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemCount) {
            1 -> {
                VIEW_TYPE_EMPTY
            }
            else -> {
                VIEW_TYPE_NORMAL
            }
        }
    }

    fun clearItems() {
        mBlogList?.clear()
    }

    fun addItems(newList: MutableList<Blog>) {
        mBlogList?.addAll(newList)
        notifyDataSetChanged()
    }

    inner class BlogHolder constructor(itemView: View) : BaseViewHolder(itemView) {

        override fun onBind(position: Int) {
            val blog = mBlogList?.get(position)
            ImageLoader.load(
                    itemView.context,
                    blog?.coverImgUrl,
                    itemView.imvCover)
            itemView.tvTitle.text = blog?.title
            itemView.tvAuthor.text = blog?.author
            itemView.tvDate.text = blog?.date
            itemView.tvContent.text = blog?.description
            itemView.setOnClickListener {
                try {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.addCategory(Intent.CATEGORY_BROWSABLE)
                    intent.data = Uri.parse(blog?.blogUrl)
                    itemView.context.startActivity(intent)
                } catch (e: Exception) {
                    AppLogger.d("Url error")
                }
            }
        }
    }

    inner class EmptyHolder constructor(itemView: View) : BaseViewHolder(itemView) {

        override fun onBind(position: Int) {
            itemView.btnRetry.setOnClickListener { mListener?.onRetryClicked() }
        }
    }
}
