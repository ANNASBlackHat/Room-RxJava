package com.annasblackhat.roomrxjava.view;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.annasblackhat.roomrxjava.R;
import com.annasblackhat.roomrxjava.persistance.RoomRxJavaDatabase;
import com.annasblackhat.roomrxjava.persistance.User;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.completable.CompletableFromAction;
import io.reactivex.schedulers.Schedulers;


public class UserActivity extends LifecycleActivity {

    private CompositeDisposable disposable;
    private RoomRxJavaDatabase db;

    private TextInputEditText edtName;
    private TextInputEditText edtEmail;
    private Button btnSave;
    private TextView txvName;
    private TextView txvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        btnSave = findViewById(R.id.btn_save);
        txvName = findViewById(R.id.txv_name);
        txvEmail = findViewById(R.id.txv_email);

        disposable = new CompositeDisposable();
        db = Room.databaseBuilder(getApplicationContext(), RoomRxJavaDatabase.class, "roomRxJava.db").build();
    }

    public void updateUser(View view){
        final String name = edtName.getText().toString();
        final String email = edtEmail.getText().toString();

        if(!(name.isEmpty() || email.isEmpty())){
            btnSave.setEnabled(false);
            Completable completable = new CompletableFromAction(new Action() {
                @Override
                public void run() throws Exception {
                    db.userDao().insertUser(new User(1, email, name));
                }
            });
            Disposable insertUser = completable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action() {
                        @Override
                        public void run() throws Exception {
                            btnSave.setEnabled(true);
                        }
                    });
            disposable.add(insertUser);
        }else{
            Toast.makeText(this, "Field must not be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Disposable getuser = db.userDao().getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(@NonNull User user) throws Exception {
                        txvName.setText(user.getSureName());
                        txvEmail.setText(user.getEmail());
                        System.out.println("xxx email "+user.getEmail());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        System.out.println("xxx error "+throwable);
                    }
                });
        disposable.add(getuser);
    }

    @Override
    protected void onStop() {
        super.onStop();
        disposable.clear();
    }
}
