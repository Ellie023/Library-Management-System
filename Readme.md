
# 📚 Library Management System

**Java + MySQL Console-Based Library Management System**

![Java](https://img.shields.io/badge/Java-8+-blue?logo=openjdk)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange?logo=mysql)
![JDBC](https://img.shields.io/badge/JDBC-Database%20Connectivity-green)
![Build](https://img.shields.io/badge/Build-Passing-success)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

A user-oriented library management system designed to automate all core library operations—**borrowing, returning, and reservation**—with robust exception handling and solid database integration via JDBC.

This project emphasizes **clean architecture**, **data integrity**, and **maintainable business logic**, making it suitable both as a backend console application and as a foundation for future GUI or web-based expansion.

---

## 📖 Overview

This system manages core library functions:

* Book registration and search
* Member information management
* Borrowing and returning workflows
* Reservation queue handling and priority rules
* State-based exception processing

Using Java SE and MySQL (with Views and Indexes), the application provides fast and reliable access to structured library data.

---

## 🛠 Tech Stack

| Category     | Technology              |
| ------------ | ----------------------- |
| Language     | Java SE (JDK 8+)        |
| Database     | MySQL 8.0               |
| Connectivity | JDBC                    |
| Date/Time    | java.time API           |
| Environment  | IntelliJ IDEA / Eclipse |

---

## ⚙️ Key Features

### 1. Advanced Borrowing & Reservation Logic

The system evaluates multiple book/member states and handles **five real-world borrowing scenarios**.

#### ✔ State-Based Workflow

* **Available → Borrow immediately**
* **Borrowed → Allow reservation**
* **Reserved → Restrict borrowing to the reserver**

#### ✔ Reservation Priority

If a reservation exists when a book is returned, **only the reserver** is allowed to borrow it.

#### ✔ Automated Date Management

* Return date automatically calculated as **14 days after the borrow date**
* All date logic based on `java.time`

---

### 2. Defensive Programming for Data Integrity

A dedicated `InputValidator`:

* Validates all user input
* Pre-checks foreign key references via DB lookup
* Prevents crashes from invalid inputs

---

### 3. Optimized Database Operations

* **PreparedStatement-first** approach
* **MySQL View (MemberBorrowedBooks)** for simplified select queries
* Indexes on `book_id` and `member_id` improve read performance

---

## 🗄️ Database Schema

### ERD

<img width="2274" height="1171" alt="Untitled (3)" src="https://github.com/user-attachments/assets/7ca8e4b9-5191-429d-a000-8648310f4216" />


### Relational Schema

<img width="1841" height="946" alt="Untitled (4)" src="https://github.com/user-attachments/assets/84fa9683-ec94-458b-a8ab-8191c1d28e46" />


### Table Overview

* **Books** — Book metadata (PK: `book_id`)
* **Members** — Member data (PK: `member_id`)
* **Borrowing** — Borrow records (FK: `book_id`, `member_id`)
* **Reservations** — Reservation queue (FK: `book_id`, `member_id`)

---

# 📁 Project Structure

```plaintext
📦 LibraryManagementSystem
├── 📁 src
│   ├── Main.java                 # Application entry point
│   ├── DatabaseConnection.java   # JDBC connection handler
│   ├── InputValidator.java       # Input validation module
│   ├── BorrowManager.java        # Borrowing workflow logic
│   ├── ReturnManager.java        # Return workflow logic
│   ├── Member_management.java    # Member management (rename recommended)
│   ├── BookGenre.java            # Genre enum/class
│   └── select1.java              # Book lookup (rename recommended: BookSearch.java)
│
├── 📁 sql
│   ├── createschema.sql
│   └── initdata.sql
│
├── Library.jar
└── README.md
```

---

# 💻 Usage Scenario

### Example Flow

1. User A borrows *"채권총론"*.
2. User B attempts to borrow the same book → system suggests reservation.
3. User B reserves it.
4. When User A returns the book, **only User B** may borrow it.

---

# 🚀 How to Run

### 1. Setup Database

Run:

```
createschema.sql
initdata.sql
```

Update credentials in:

```java
// src/DatabaseConnection.java
String url = "jdbc:mysql://localhost:3306/library_db?serverTimezone=UTC";
String user = "root";
String password = "your_password";
```

---

### 2. Build

```
javac -d bin src/*.java
```

### 3. Run

```
java -jar Library.jar
```

or

```
java Main
```

---

# 🔍 Example Code

```java
// Priority reservation logic in BorrowManager
if (hasReservation && !isRequesterReservedMember) {
    System.out.println("This book is reserved. Only the reserver may borrow it.");
    return;
}
```

---

# 🚀 Future Improvements

* Connection Pooling (HikariCP)
* Migration to JPA/Hibernate
* GUI (JavaFX) or Web extension (Spring Boot)

---

---

# 🇰🇷 **국문 README – 도서 관리 시스템**

# 📚 도서 관리 시스템

**Java + MySQL 기반 콘솔형 도서관 관리 시스템**

![Java](https://img.shields.io/badge/Java-8+-blue?logo=openjdk)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange?logo=mysql)
![JDBC](https://img.shields.io/badge/JDBC-Database%20Connectivity-green)
![Build](https://img.shields.io/badge/Build-Passing-success)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

이 프로젝트는 도서관의 핵심 업무인 **대출, 반납, 예약 프로세스를 자동화**한 콘솔 기반 관리 시스템입니다.
사용자 중심의 로직, 데이터 무결성, 예외 처리, 성능 최적화에 중점을 두어 실제 운영 환경에서도 안정적으로 동작하도록 설계했습니다.

---

## 📖 프로젝트 개요

이 시스템은 다음 기능을 제공합니다:

* 도서 등록 및 조회
* 회원 관리
* 대출 / 반납 처리
* 예약 및 예약자 우선 대출
* 상태 기반 예외 처리

Java SE + JDBC 기반으로 구현되었으며 MySQL View, Index를 활용해 조회 성능을 최적화했습니다.

---

## 🛠 기술 스택

| 구분           | 기술                      |
| ------------ | ----------------------- |
| Language     | Java SE (JDK 8+)        |
| Database     | MySQL 8.0               |
| Connectivity | JDBC                    |
| Date/Time    | java.time API           |
| Environment  | IntelliJ IDEA / Eclipse |

---

## ⚙️ 핵심 기능

### 1. 정교한 대출 / 예약 로직

도서 및 회원 상태를 기반으로 총 **5가지 케이스**를 분기 처리합니다.

#### ✔ 상태 기반 제어

* **대출 가능** → 즉시 대출
* **대출 중** → 예약 제안
* **예약 중** → 예약자만 대출 가능

#### ✔ 선점 로직(Preemption Logic)

반납 시 예약자가 존재하면 **오직 예약자만 대출할 수 있도록 제한**합니다.

#### ✔ 자동 날짜 계산

* 대출일 + 14일 → 자동 반납 예정일 계산

---

### 2. 데이터 무결성

`InputValidator`를 통해:

* 사용자 입력값 검증
* BookID / MemberID 존재 여부 사전 확인
* 잘못된 입력으로 인한 강제 종료 방지

---

### 3. 데이터베이스 최적화

* PreparedStatement로 SQL Injection 차단
* MySQL View로 반복 조회 단순화
* PK/FK Index로 조회 성능 향상

---

# 🗄️ 데이터베이스 스키마

### ERD

<img width="2274" height="1171" alt="Untitled (3)" src="https://github.com/user-attachments/assets/b84d2ebc-4d64-4d50-bd31-4a95a59f7304" />


### 관계형 스키마
<img width="1841" height="946" alt="Untitled (4)" src="https://github.com/user-attachments/assets/94ea4fa9-7635-4976-a3c3-0f552d315324" />

---

## 📁 프로젝트 구조 (실제 파일 기준)

```plaintext
📦 LibraryManagementSystem
├── 📁 src
│   ├── Main.java
│   ├── DatabaseConnection.java
│   ├── InputValidator.java
│   ├── BorrowManager.java
│   ├── ReturnManager.java
│   ├── Member_management.java    # (리팩토링 권장: MemberService.java)
│   ├── BookGenre.java
│   └── select1.java              # (리팩토링 권장: BookSearch.java)
│
├── 📁 sql
│   ├── createschema.sql
│   └── initdata.sql
│
├── Library.jar
└── README.md
```

---

# 💻 사용 시나리오

1. 사용자 A가 *「채권총론」* 을 대출
2. 사용자 B가 동일 도서 대출 요청 → 시스템이 "예약 가능" 안내
3. 사용자 B 예약
4. A가 반납하면 **예약자 B만** 대출 가능

---

# 🚀 실행 방법

### 1. DB 설정

```java
// src/DatabaseConnection.java
String url = "jdbc:mysql://localhost:3306/library_db?serverTimezone=UTC";
String user = "root";
String password = "your_password";
```

아래 SQL 스크립트를 실행합니다:

```
createschema.sql
initdata.sql
```

---

### 2. 컴파일

```
javac -d bin src/*.java
```

### 3. 실행

```
java -jar Library.jar
```

또는

```
java Main
```

---

# 🔍 코드 예시

```java
if (hasReservation && !isRequesterReservedMember) {
    System.out.println("현재 도서는 예약된 상태입니다. 예약자만 대출할 수 있습니다.");
    return;
}
```

---

# 🚀 향후 개선 사항

* HikariCP 기반 Connection Pool 도입
* JPA/Hibernate 전환
* JavaFX 또는 Spring Boot를 통한 GUI/Web 확장
