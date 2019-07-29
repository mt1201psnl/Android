package com.example.quiz_poke;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz_poke.db.accessor.DBA_PokemonDb;
import com.example.quiz_poke.db.InsertData;
import com.example.quiz_poke.domainLogic.CreateQuizData;
import com.example.quiz_poke.domainLogic.ReadAllRecord;
import com.example.quiz_poke.vo.PokemonVo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // 画面要素変数
    private TextView countLabel;
    private ImageView questionImage;
    private EditText editText;

    // 管理用変数
    private String rightAnswer;
    private int quizCount = 1;
    private int rightAnswerCount = 0;

    // クイズリスト
    LinkedList<PokemonVo> QuizData = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 画面要素取得
        countLabel = findViewById(R.id.countLabel);
        questionImage = findViewById(R.id.questionImage);
        editText = findViewById(R.id.editText);

        // DBデータを最新化する
        InsertData insertData = new InsertData(getApplicationContext());
        insertData.onCreate(insertData.getReadableDatabase());
        try {
            insertData.copyDatabase(insertData.getWritableDatabase());
        } catch (IOException ioe) {
        }

        // accessor作成
        DBA_PokemonDb accessor = new DBA_PokemonDb(getApplicationContext());
        SQLiteDatabase db = accessor.getWritableDatabase();

        // Select
        ReadAllRecord readAllRecord = new ReadAllRecord();
        LinkedList<PokemonVo> allRecord = readAllRecord.execute(db);

        // クイズデータ作成
        CreateQuizData createQuizData = new CreateQuizData();
        QuizData = createQuizData.execute(allRecord, PokeQuizConst.QUIZ_VOLUME);

        // 問題表示処理
        showNextQuiz();

        // 入力されたら答え合わせ
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        checkAnswer();
                    }
                }
                return false;
            }
        });
    }

    public void showNextQuiz() {
        // 出題数表示
        countLabel.setText(quizCount + "問目");

        // 入力欄初期化
        editText.setText("");

        // ランダムな数字を取得
        Random random = new Random();
        int randomNum = random.nextInt(QuizData.size());

        // randomNumを使ってquizArrayからクイズを一つ取り出す
        ArrayList<String> dataList = new ArrayList<>();
        PokemonVo quizVo = QuizData.get(randomNum);

        // 取り出したクイズのデータをリストに詰める
        dataList.add(quizVo.getImage());
        dataList.add(quizVo.getName());
        dataList.add(quizVo.getType_1());
        dataList.add(quizVo.getType_2());
        dataList.add(quizVo.getCharacteristic_1());
        dataList.add(quizVo.getCharacteristic_2());
        dataList.add(quizVo.getCharacteristic_dream());
        dataList.add(quizVo.getSpecies());
        dataList.add(quizVo.getMost_used_move());

        // 画像をセット
        questionImage.setImageResource(getResources().getIdentifier(dataList.get(0), "drawable", getPackageName()));

        // 出題する内容と正解を生成
        String hintText = this.decideQuiz(dataList);
        editText.setHint(hintText);
        System.out.println(quizCount + "問目：" + hintText);

        // このクイズをquizArrayから削除
        QuizData.remove(randomNum);
    }

    public String decideQuiz(ArrayList<String> dataList) {

        // 問題文
        String hintTextTmp;

        // 問題文リストを作成
        ArrayList<String> question = new ArrayList<>();
        question.add(dataList.get(1));
        question.add(dataList.get(2));
        question.add(dataList.get(3));
        question.add(dataList.get(4));
        question.add(dataList.get(5));
        question.add(dataList.get(6));
        question.add(dataList.get(7));
        question.add(dataList.get(8));

        // ランダムな数字を取得
        Random random = new Random();
        int randomNum = random.nextInt(question.size());

        // 問題の内容を決定
        if (randomNum == 0) {
            hintTextTmp = PokeQuizConst.QUESTION_NAME;
        } else if (randomNum == 1) {
            hintTextTmp = PokeQuizConst.QUESTION_TYPE_1;
        } else if (randomNum == 2) {
            hintTextTmp = PokeQuizConst.QUESTION_TYPE_2;
        } else if (randomNum == 3) {
            hintTextTmp = PokeQuizConst.QUESTION_CHARACTERISTIC_1;
        } else if (randomNum == 4) {
            hintTextTmp = PokeQuizConst.QUESTION_CHARACTERISTIC_2;
        } else if (randomNum == 5) {
            hintTextTmp = PokeQuizConst.QUESTION_CHARACTERISTIC_DREAM;
        } else if (randomNum == 6) {
            hintTextTmp = PokeQuizConst.QUESTION_SPECIES;
        } else if (randomNum == 7) {
            hintTextTmp = PokeQuizConst.QUESTION_MOST_USED_MOVE;
        } else {
            // 予備
            hintTextTmp = PokeQuizConst.QUESTION_NAME;
            this.rightAnswer = dataList.get(0);
            return hintTextTmp;
        }

        // 正解存在判定
        if (!(question.get(randomNum) == null)) {
            this.rightAnswer = question.get(randomNum);
        } else {
            this.rightAnswer = PokeQuizConst.ANSWER_NULL;
        }

        return hintTextTmp;
    }

    public void checkAnswer() {

        // 入力したテキストを取得
        String answer = editText.getText().toString();
        System.out.println(answer);

        // 判定
        String alertTitle;
        if (rightAnswer.equals(answer)) {
            // 正解
            alertTitle = PokeQuizConst.RIGHT;
            rightAnswerCount++;
        } else {
            // 不正解
            alertTitle = PokeQuizConst.WRONG;
        }

        // ダイアログ作成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        System.out.println(alertTitle);
        if (QuizData.size() == 0) {
            // 結果発表
            builder.setMessage("答え：" + rightAnswer);
            builder.setPositiveButton(PokeQuizConst.RESULT, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    showResult();
                }
            });
        } else {
            // 次の問題を出題
            builder.setMessage("答え：" + rightAnswer);
            builder.setPositiveButton(PokeQuizConst.NEXT, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    editText.setText("");
                    quizCount++;
                    showNextQuiz();
                }
            });
        }
        builder.setCancelable(false);
        builder.show();
    }

    public void showResult() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("結果");
        builder.setMessage(quizCount + "問中 " + rightAnswerCount + "問正解");
        builder.setNegativeButton(PokeQuizConst.ONE_MORE, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 入力欄をクリアしてActivity再起動
                editText.getText().clear();
                recreate();
            }
        });
        builder.setPositiveButton(PokeQuizConst.EXIT, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // アプリを終了
                finish();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
