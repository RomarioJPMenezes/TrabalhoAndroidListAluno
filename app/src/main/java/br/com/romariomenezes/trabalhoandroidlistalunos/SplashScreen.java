package br.com.romariomenezes.trabalhoandroidlistalunos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import br.com.romariomenezes.trabalhoandroidlistaalunos.model.User;

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setContentView(R.layout.activity_splash_screen);
        //Executando o método que iniciará nossa animação
        carregar();
    }


    private void carregar() {
        Animation anim = AnimationUtils.loadAnimation(this,
                R.anim.animacao_splash);
        anim.reset();
        //Pegando o nosso objeto criado no layout
        ImageView iv = (ImageView) findViewById(R.id.splash);
        if (iv != null) {
            iv.clearAnimation();
            iv.startAnimation(anim);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences shared = getSharedPreferences("info",MODE_PRIVATE);
                String userLogado = shared.getString("user","vazio");

                if(userLogado.equals("vazio")) {
                    // Após o tempo definido irá executar a próxima tela
                    Intent intent = new Intent(SplashScreen.this,
                            LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);

                }else{
                    Intent intent = new Intent(SplashScreen.this, MenuPrincipal.class);
                    User user = new User();
                    user.setEmail(userLogado);
                    intent.putExtra("user", user);
                    startActivity(intent);

                }
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


}
