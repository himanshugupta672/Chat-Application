package com.example.chitthi

import android.content.Context
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context, val messageList: ArrayList<com.example.chitthi.Message>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        val ITEM_RECIVE = 1;
        val ITEM_SENT = 2;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        if(viewType==1){
            //inflate recive
            val view: View = LayoutInflater.from(context).inflate(R.layout.recive,parent,false)
            return ReciveViewHolder(view)
        }
        else{
            //inflate sent
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            return SentViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]

        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return  ITEM_SENT
        }
        else{
            return ITEM_RECIVE
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /*Since there are two viewHolder so we have to check first which view Holder it is*/

        //here we will get the messgage from the messge list and it respective position
        val currentMessage = messageList[position]

        if(holder.javaClass == SentViewHolder::class.java){
            //do the stuff for sent view holder
            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message
        }
        else{
            // do the stuff for recive view holder
            val viewHolder = holder as ReciveViewHolder
            holder.reciveMessage.text = currentMessage.message
        }
    }

    //this time we have to create two view holder one for sending the message and one for reciving the message
    class SentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val sentMessage = itemView.findViewById<TextView>(R.id.txt_sent_message)
    }

    class ReciveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val reciveMessage = itemView.findViewById<TextView>(R.id.txt_recive_message)
    }
}