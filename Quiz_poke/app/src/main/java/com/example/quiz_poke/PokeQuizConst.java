package com.example.quiz_poke;

public class PokeQuizConst {

    /* データーベース名 */
    public static final String DATABASE_NAME = "PokemonDB";
    /* データベースファイル名 */
    public static final String DB_FILE_NAME = "PokemonDB.db";
    /* DBバージョン */
    public static final int DB_VERSION = 1;
    /* テーブル名 */
    public static final String TABLE_NAME = "TBM_POKEMON";

    /* 出題問題数 */
    public static int QUIZ_VOLUME = 10;

    /* 問題文(名前) */
    public static final String QUESTION_NAME = "このポケモンの名前は？";
    /* 問題文(タイプ1) */
    public static final String QUESTION_TYPE_1 = "このポケモンの1つ目のタイプは？";
    /* 問題文(タイプ2) */
    public static final String QUESTION_TYPE_2 = "このポケモンの2つ目のタイプは？";
    /* 問題文(特性1) */
    public static final String QUESTION_CHARACTERISTIC_1 = "このポケモンの1つ目の通常特性は？";
    /* 問題文(特性2) */
    public static final String QUESTION_CHARACTERISTIC_2 = "このポケモンの2つ目の通常特性は？";
    /* 問題文(夢特性) */
    public static final String QUESTION_CHARACTERISTIC_DREAM = "このポケモンの夢特性は？";
    /* 問題文(分類) */
    public static final String QUESTION_SPECIES = "このポケモンの分類は？";
    /* 問題文(使用率1位) */
    public static final String QUESTION_MOST_USED_MOVE = "このポケモンの最も使用率の高い技は？";

    /* 答え：なし */
    public static final String ANSWER_NULL = "なし";

    /* 正解 */
    public static final String RIGHT = "正解";
    /* 不正解 */
    public static final String WRONG = "不正解";
    /* 結果を見る */
    public static final String RESULT = "結果を見る";
    /* 次へ */
    public static final String NEXT = "次へ";
    /* もう一度 */
    public static final String ONE_MORE = "もう一度";
    /* 終了 */
    public static final String EXIT = "終了";

}
