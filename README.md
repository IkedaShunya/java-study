# 受講生管理システム
 - 受講生情報を管理するシステ。<br >
 - 受講生情報の検索・登録・編集・削除が可能 <br >
 - REST APIにて実装 <br >

## 使用技術
- Java21
- springframework3.3.4
- mybatis 3.0.3
- junit
- Thymeleaf（RestAPI実装前）
- MySQL 8.4.2
- MVCモデル

## 機能一覧
- 受講生情報の登録、削除、編集、検索
- 受講生コース情報の登録、削除、編集、検索
- 登録の際に例外処理の発生
- バリデーションのチェック

## DB設計（テーブル構成）
- 受講生情報
  - 受講生名前,受講生ID(主キー,外部キー),受講生フリガナ,ニックネーム,アドレス,地域,年齢,性別,備考,削除フラグ
- 受講生コース情報
  - 受講生コースID(主キー),受講生ID(外部キー),コース名,コース開始日付,コース終了日付


    
