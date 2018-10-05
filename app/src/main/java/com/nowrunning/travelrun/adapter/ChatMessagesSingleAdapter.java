package com.nowrunning.travelrun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.util.Chats;
import com.nowrunning.travelrun.util.ChatsMessages;

import java.util.ArrayList;

public class ChatMessagesSingleAdapter extends RecyclerView.Adapter<ChatMessagesSingleAdapter.ChatHolder>{
    private Chats containedChat;
    private Context context;


public class ChatHolder extends RecyclerView.ViewHolder {


    protected TextView messageText;
    protected TextView timeText;

    protected ImageView chatProfile;


    public ChatHolder(View itemView) {
        super(itemView);
        this.messageText = (TextView) itemView.findViewById(R.id.messageContent);
        this.timeText = (TextView) itemView.findViewById(R.id.messageTime);
        this.chatProfile = (ImageView) itemView.findViewById(R.id.senderPic);
    }

}

    public ChatMessagesSingleAdapter(Chats chatsIn, Context inContext){
        containedChat = chatsIn;
        this.context = inContext;
    }

    @Override
    public ChatMessagesSingleAdapter.ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_chat_messages, null);
        ChatMessagesSingleAdapter.ChatHolder mh = new ChatMessagesSingleAdapter.ChatHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ChatMessagesSingleAdapter.ChatHolder holder, int position) {

        /*LinearLayout mainLayout = (LinearLayout)findViewById(R.id.circleProfileImages);

        //mainLayout = ;

        mainLayout.addView();*/
        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(150,150);



        ArrayList<ChatsMessages> gotMSGS = containedChat.getSentMessages();

        ImageView imgIn;
        TextView msgTextin;
        TextView msgTimeFrmNow;

        for(int ii =0; ii < getItemCount(); ii++) {
            imgIn = new ImageView(context);
            /*imgIn.setMaxHeight(50);
            imgIn.setMaxHeight(50);*/

            //imgIn.setImageResource(circleMembers.get(ii).getMainImage());

            //imgIn.set

            //holder.containedLayout.addView(imgIn, imgParams);
        }

        /*imgIn = new ImageView(context);

        imgIn.setImageResource(R.drawable.pexels_photo_97900);
        holder.containedLayout.addView(imgIn,imgParams);*/

    }

    @Override
    public int getItemCount() {
        return containedChat.getSentMessages().size();
    }
}
