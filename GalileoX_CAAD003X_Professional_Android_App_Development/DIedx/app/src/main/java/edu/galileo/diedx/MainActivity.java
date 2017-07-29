package edu.galileo.diedx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Inject MessageService messageService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMessageComponent.builder()
                .messageModule(new MessageModule(this))
                .build()
                .inject(this);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.hi_button)
    public void hi() {
        messageService.sayHi();
    }

    @OnClick(R.id.bye_button)
    public void bye() {
        messageService.sayBye();
    }

    @OnClick(R.id.ask_button)
    public void ask() {
        messageService.askSomething();
    }
}
