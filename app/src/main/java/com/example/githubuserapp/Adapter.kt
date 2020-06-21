package com.example.githubuserapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class Adapter(private val listUser: ArrayList<User>): RecyclerView.Adapter<Adapter.CardViewViewHolder>() {
    inner class CardViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_user_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_user_name)
        var tvCompany: TextView = itemView.findViewById(R.id.tv_user_company)
        var tvLocation: TextView = itemView.findViewById(R.id.tv_user_location)
        var tvRepo: TextView = itemView.findViewById(R.id.tv_user_repo)
        var tvFollower: TextView = itemView.findViewById(R.id.tv_user_followers)
        var tvFollowing: TextView = itemView.findViewById(R.id.tv_user_following)

        private var item: RelativeLayout = itemView.findViewById(R.id.item_list)
        private var context: Context = itemView.context

        init {
            item.setOnClickListener{
                val intent = Intent(context, DetailActivity::class.java)
                val position = adapterPosition

                intent.putExtra(DetailActivity.EXTRA_USER, listUser)
                intent.putExtra(DetailActivity.EXTRA_POSITION, position)
                intent.putExtra(DetailActivity.EXTRA_PHOTO, itemView.context.resources.getIdentifier(listUser[position].avatar, "drawable", itemView.context.packageName))

                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val user = listUser[position]
        val photo = holder.itemView.context.resources.getIdentifier(user.avatar, "drawable", holder.itemView.context.packageName)

        Glide.with(holder.itemView.context)
            .load(photo)
            .apply(RequestOptions().override(80, 80))
            .into(holder.imgPhoto)

        holder.tvName.text = user.name
        holder.tvCompany.text = user.company
        holder.tvLocation.text = user.location
        holder.tvRepo.text = user.repository.toString()
        holder.tvFollower.text = user.follower.toString()
        holder.tvFollowing.text = user.following.toString()

        holder.itemView.setOnClickListener { Toast.makeText(holder.itemView.context, listUser[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return CardViewViewHolder(view)
    }
}