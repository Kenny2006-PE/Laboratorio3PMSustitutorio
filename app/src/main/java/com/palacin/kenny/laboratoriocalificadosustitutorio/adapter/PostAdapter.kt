package com.palacin.kenny.laboratoriocalificadosustitutorio.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.palacin.kenny.laboratoriocalificadosustitutorio.databinding.ItemPostBinding
import com.palacin.kenny.laboratoriocalificadosustitutorio.model.Post

class PostAdapter(
    private val context: Context,
    private val posts: List<Post>
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.binding.tvTitle.text = post.title
        holder.binding.tvBody.text = post.body

        // Click corto: enviar título por mensaje SMS
        holder.binding.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:+51925137361")
                putExtra("sms_body", post.title) // Enviar título en el mensaje
            }
            context.startActivity(intent)
        }

        // Click largo: enviar body del post por correo
        holder.binding.root.setOnLongClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:victor.saico@tecsup.edu.pe")
                putExtra(Intent.EXTRA_SUBJECT, "Enunciado del post")
                putExtra(Intent.EXTRA_TEXT, post.body)
            }
            if (emailIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(emailIntent)
            }
            true
        }
    }

    override fun getItemCount(): Int = posts.size
}
