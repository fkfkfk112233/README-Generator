# README Generator V2

一個使用 Java Swing 開發的桌面應用程式，用於快速產生專案 README.md 文件。

本專案採用 MVC 分層架構，結合 JDBC 與 MySQL 資料庫，讓使用者可以管理專案資料、套用 Markdown Template，並匯出 README 文件。

---

## 📌 Project Overview

README Generator V2 主要解決開發者建立專案文件時，需要重複撰寫 README 的問題。

使用者只需要輸入：

* 專案名稱
* 作者
* 版本
* GitHub URL
* 專案描述
* 功能列表
* 安裝方式
* 使用方式

系統會自動產生 Markdown 格式的 README。

---

## ✨ Features

### Project Management

* 新增專案資料
* 修改專案資料
* 刪除專案資料
* 查詢專案資料
* JTable 顯示專案列表
* 點擊資料快速載入

---

### README Generator

* Markdown Template 系統
* 自動填入專案資訊
* README 即時預覽
* 匯出 README.md 文件

---

### Template System

目前支援：

* Default Template
* Professional Template
* Minimal Template

Template 使用：

```
${projectName}
${description}
${author}
${version}
${github}
${features}
${installation}
${usage}
```

系統會自動替換成專案資料。

---

## 🛠 Tech Stack

### Backend / Core

* Java JDK 11
* JDBC
* MySQL 8.0

### GUI

* Java Swing
* JFrame
* Window Builder

### Build Tool

* Maven

### IDE

* Eclipse

---

## 🏗 Project Architecture

```
src/main/java

├── model
│   └── ProjectInfo.java
│
├── dao
│   ├── ProjectDao.java
│   └── impl
│       └── ProjectDaoImpl.java
│
├── service
│   ├── ProjectService.java
│   ├── ReadmeService.java
│   │
│   └── impl
│       ├── ProjectServiceImpl.java
│       └── ReadmeServiceImpl.java
│
├── controller
│   └── MainFrame.java
│
├── util
│   ├── DbConnection.java
│   ├── TemplateEngine.java
│   └── FileUtil.java
│
└── exception
```

---

## 🗄 Database

Database:

```
readme_generator
```

Table:

```
project
```

Example:

```sql
CREATE TABLE project(

    id INT PRIMARY KEY AUTO_INCREMENT,

    project_name VARCHAR(100),

    description TEXT,

    author VARCHAR(50),

    version VARCHAR(20),

    github_url VARCHAR(255),

    installation TEXT,

    usage_text TEXT,

    features TEXT,

    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);
```

---

## ⚙️ Installation

### 1. Clone Project

```bash
git clone https://github.com/yourname/README-Generator.git
```

---

### 2. Import Database

建立 MySQL database：

```sql
CREATE DATABASE readme_generator;
```

執行建表 SQL。

---

### 3. 修改資料庫設定

修改：

```
util/DbConnection.java
```

設定：

```java
String url =
"jdbc:mysql://localhost:3306/readme_generator";

String username="root";

String password="your_password";
```

---

### 4. Maven Build

```bash
mvn clean install
```

---

### 5. Run

執行：

```
MainFrame.java
```

---

## 📖 Usage

1. 開啟程式

2. 輸入專案資訊

例如：

```
Project Name:
Shopping Cart

Version:
1.0.0
```

3. 選擇 Template

4. 點擊：

```
Generate
```

產生 README

5. 點擊：

```
Export README
```

輸出：

```
README.md
```

---

## 🔄 Application Flow

```
User

 ↓

MainFrame

 ↓

Service Layer

 ↓

DAO Layer

 ↓

MySQL Database


Generate README

 ↓

ReadmeService

 ↓

TemplateEngine

 ↓

README.md
```

---

## 📌 Future Improvements

* [ ] Markdown HTML Preview
* [ ] Dark Mode UI
* [ ] GitHub API 自動取得 Repository 資訊
* [ ] AI 自動產生 README
* [ ] 多語系 Template
* [ ] 匯出 PDF
* [ ] 使用者登入系統

---

## 👨‍💻 Author

王碩熙

---

