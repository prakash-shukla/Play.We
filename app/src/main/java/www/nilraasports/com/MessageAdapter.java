package www.nilraasports.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

        private static final int MSG_TYPE_LEFT=0;
        private static final int MSG_TYPE_RIGHT=1;

        Context mContext;
        private List<Chat> mChat;
        private String imageurl;
        FirebaseUser firebaseUser;

        public MessageAdapter(Context mContext, List<Chat> mChat, String imageurl) {
        this.mContext = mContext;
        this.mChat = mChat;
        this.imageurl = imageurl;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemview;
            if(i==MSG_TYPE_RIGHT) itemview = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right,viewGroup,false);
            else itemview = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left,viewGroup,false);
            MyViewHolder myViewHolder = new MyViewHolder(itemview);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            Chat chat = mChat.get(i);
            myViewHolder.textView.setText(chat.getMessage());
            myViewHolder.time.setText(chat.getTime());

            if(imageurl.equals("default"))
            {
                myViewHolder.imageView.setImageResource(R.drawable.user_icon);
            }else
            {
                Glide.with(mContext).load(imageurl).into(myViewHolder.imageView);
            }

            if(i==mChat.size()-1){
                if(chat.isIsseen()){
                    myViewHolder.txt_seen.setText("Seen");
                }else{
                    myViewHolder.txt_seen.setText("Delivered");
                }
            }else{
                myViewHolder.txt_seen.setVisibility(View.GONE);
            }
        }


        @Override
        public int getItemCount() {
        return mChat.size();
        }



        public class MyViewHolder extends RecyclerView.ViewHolder {
            CircleImageView imageView;
            TextView textView;
            TextView txt_seen;
            TextView time;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = (CircleImageView)itemView.findViewById(R.id.profile_image);
                textView = (TextView)itemView.findViewById(R.id.show_message);
                txt_seen = (TextView) itemView.findViewById(R.id.txt_seen);
                time = (TextView) itemView.findViewById(R.id.show_time);
            }
        }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(firebaseUser.getUid()))
            return MSG_TYPE_RIGHT;
        else  return MSG_TYPE_LEFT;
    }

}

