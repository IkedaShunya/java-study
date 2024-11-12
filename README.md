# 受講生管理システム（作成中）
 - 受講生情報を管理するシステム<br >
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
- バリデーションチェック

## DB設計（テーブル構成）
- 受講生情報
  - 受講生名前,受講生ID(主キー,外部キー),受講生フリガナ,ニックネーム,アドレス,地域,年齢,性別,備考,削除フラグ
- 受講生コース情報
  - 受講生コースID(主キー),受講生ID(外部キー),コース名,コース開始日付,コース終了日付

## 実際の画面
例）受講生情報の登録<br >
![image](https://github.com/user-attachments/assets/dd195f4e-82bb-4edf-b739-9e7646960fd0)<br >
![image](https://github.com/user-attachments/assets/6e9e2ea8-466a-41f9-a984-f8d29118b1e9)<br >
例）REST API実装後の受講生情報の登録(PostMan画面)<br >
![image](https://github.com/user-attachments/assets/31fff2e6-dd9c-44ed-97a5-7a7609c146d5)
![image](https://github.com/user-attachments/assets/3b024a13-f04f-4348-9442-6a7080e22489)

## 　今後の取り組み
- バリデーションチェック（もう少し厳密に）
- AWSにてデプロイ
