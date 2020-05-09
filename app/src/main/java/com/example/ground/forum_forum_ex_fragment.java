/*
package com.example.ground;




import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
//게시판
public class forum_forum_ex_fragment extends Fragment implements View.OnClickListener{

    Button next_tab, write, cancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        next_tab = next_tab.findViewById(R.id.go_forum_image);
        write = write.findViewById(R.id.go_forum_write);
        cancel = cancel.findViewById(R.id.btn_forum_forum_cancel);

        next_tab.setOnClickListener(this);
        write.setOnClickListener(this);
        cancel.setOnClickListener(this);
        return inflater.inflate(R.layout.activity_forum_forum, container, false);

    }

    @Override
    public void onClick (View v){
        if (v.getId() == R.id.go_forum_image) {
            Intent intent01 = new Intent(forum_forum.this, forum_image.class);
            startActivity(intent01);
        }
        if (v.getId() == R.id.go_forum_write) {
            Intent intent02 = new Intent(forum_forum.this, forum_forum_write.class);
            startActivity(intent02);
        }
        if (v.getId() == R.id.btn_forum_forum_cancel) {
            finish();
        }
    }
}


 */

