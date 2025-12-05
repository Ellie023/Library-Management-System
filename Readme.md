
# 📚 Library Management System

**Java + MySQL Console-based Library Management System**

![Static Badge](https://img.shields.io/badge/Java-8+-blue?logo=coffeescript)
![Static Badge](https://img.shields.io/badge/MySQL-8.0-orange?logo=mysql)
![Static Badge](https://img.shields.io/badge/JDBC-Database%20Connectivity-green)
![Static Badge](https://img.shields.io/badge/Build-Passing-success)
![Static Badge](https://img.shields.io/badge/License-MIT-lightgrey)

A fully functional, user-oriented library management system built with Java and MySQL.
It handles the **complete lifecycle of book operations**—borrowing, returning, and reservation—while addressing real-world edge cases through clean, maintainable business logic.

Designed with **data integrity**, **robust exception handling**, and **scalable architecture** in mind, the system lays a solid foundation for further expansion into GUI or web applications.

---

## 📖 Overview

The system digitizes essential library workflows:

* Book registration & search
* Member management
* Borrowing & returning
* Reservation handling and prioritized loaning
* Exception handling based on book/member states

Java SE + JDBC ensures a lightweight yet reliable architecture, while MySQL Views and Indexes boost query performance.

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

A state-driven workflow handles five real-world borrowing scenarios, ensuring accurate and predictable behavior.

* **Available → Borrow immediately**
* **Borrowed → Offer reservation**
* **Reserved → Only the reserver may borrow**

#### Reservation Priority (Preemption Logic)

When a book is returned, and a reservation exists, **the reserver exclusively gains borrowing rights**, enforcing fair access.

#### Automated Date Handling

* Return date automatically set to **14 days** after the borrowing date
* All date operations handled via `java.time`

---

### 2. Defensive Programming & Data Integrity

A dedicated `InputValidator` module ensures:

* Clean separation of validation logic
* Pre-checks of BookID/MemberID to avoid foreign key violations
* Robust handling of invalid input to prevent system interruptions

---

### 3. Optimized Database Interaction

* **PreparedStatement-only queries** → prevents SQL Injection & improves performance
* **Database View (MemberBorrowedBooks)** → simplifies recurring queries
* Indexes on `book_id`, `member_id` for faster lookups

---

## 🗄️ Database Schema

### E-R Diagram

<img width="2274" height="1171" src="https://github.com/user-attachments/assets/bcfb9a11-b1c4-48ca-bd78-ffcbc6ff2332" />

### Relational Schema

<img width="1841" height="946" src="https://github.com/user-attachments/assets/f3f9b3fc-d954-4826-bc62-91a86f8cca7c" />

### Tables

* **Books** — Book information (PK: `book_id`)
* **Members** — Member information (PK: `member_id`)
* **Borrowing** — Borrow history (FK: `book_id`, `member_id`)
* **Reservations** — Reservation queue (FK: `book_id`, `member_id`)

---

## 📁 Project Structure

```plaintext
📦 LibraryManagementSystem
├── 📁 src
│   ├── Main.java
│   ├── DatabaseConnection.java
│   ├── BorrowManager.java
│   ├── InputValidator.java
│   ├── BookService.java
│   ├── MemberService.java
│   ├── ReservationService.java
│   └── Utils.java
│
├── 📁 sql
│   ├── createschema.sql
│   └── initdata.sql
│
├── 📁 bin               # Compiled bytecode
├── Library.jar         # Executable JAR
└── README.md
```

If you want, I can customize this tree to match your actual folder structure.

---

## 💻 Usage Scenario

### Example Workflow

1. **User A** borrows *“채권총론”*.
2. **User B** attempts to borrow the same title.
   → System prompts: *Borrowed — Reservation available.*
3. User B reserves the book.
4. When User A returns it, **only User B** is allowed to borrow next.

---

## 🚀 How to Run

### 1. Database Setup

Execute:

```
createschema.sql
initdata.sql
```

Update DB credentials in `DatabaseConnection.java`.

---

### 2. Build

```
javac -d bin src/*.java
```

---

### 3. Run

```
java -jar Library.jar
```

or:

```
java Main
```

---

## 🔍 Code Example

```java
if (!borrow && !reservation) {
    // Borrow logic
} else if (!borrow && reservation) {
    // Reservation-holder priority logic
}
```

---

## 🚀 Future Improvements

* Introduce **Connection Pooling (HikariCP)**
* Migrate to **JPA/Hibernate**
* Expand UI via **JavaFX** or **Spring Boot Web Application**


# 📚 Library Management System

**Java + MySQL 기반 콘솔형 도서 관리 시스템**

도서의 **대출, 반납, 예약 전 과정을 자동화**한 콘솔 기반 관리 시스템입니다.
관리자 중심이 아닌 **사용자 중심 프로세스**에 집중하여, 실제 도서관 업무에서 발생하는 다양한 예외 상황을 정교하게 처리하도록 설계했습니다.
JDBC를 활용해 MySQL과 안정적으로 연동하며, 데이터 무결성과 예외 처리에 중점을 두었습니다.

---

## 📖 Overview

이 프로젝트는 다음과 같은 도서관 핵심 기능을 전산화합니다.

* 도서 등록 및 조회
* 회원 정보 관리
* 대출 및 반납 처리
* 예약 및 예약자 우선 대출
* 도서/회원 상태 기반의 예외 처리

Java SE와 JDBC로 구현되었으며, MySQL의 View, Index 등을 활용해 성능과 유지보수성을 강화했습니다.

---

## 🛠 Tech Stack

| Category     | Technology                               |
| ------------ | ---------------------------------------- |
| Language     | Java SE (JDK 8+)                         |
| Database     | MySQL 8.0                                |
| Connectivity | JDBC                                     |
| Date/Time    | java.time (LocalDate, DateTimeFormatter) |
| Environment  | IntelliJ IDEA / Eclipse                  |

---

## ⚙️ Key Features

### 1. 정교한 대출 및 예약 로직 (BorrowManager)

도서와 회원의 상태를 조합하여 **5가지 케이스**로 분기 처리했습니다.

#### ✔ 상태 기반 프로세스 제어

* **대출 가능** → 즉시 대출
* **대출 중** → 예약 진행
* **예약 중** → 예약자가 아닌 경우 대출 불가

#### ✔ 예약자 우선권(Preemption Logic)

반납 시점에 예약자가 존재하면 **오직 예약자만 대출 가능**하도록 제한하여
예약의 우선순위를 확실히 보장합니다.

#### ✔ 날짜 자동 계산

* 대출 시점으로부터 **14일 후 반납일 자동 계산**
* 모든 날짜 연산은 `java.time` 패키지로 처리

---

### 2. 데이터 무결성을 위한 방어적 프로그래밍 (InputValidator)

* **입력 검증 로직 모듈화**로 유지보수성 향상
* MemberID, BookID 등의 존재 여부를 **DB 선조회**
  → 외래키 제약 위반 등을 사전에 차단
* 잘못된 입력으로 인한 프로그램 중단을 최소화

---

### 3. 효율적인 DB 접근 구조

* **PreparedStatement 전면 사용**
  → SQL Injection 방지 + 쿼리 캐싱으로 성능 향상
* **View 활용 (MemberBorrowedBooks)**
  → 회원별 대출 목록 조회 최적화
* `book_id`, `member_id`에 **Index 생성**
  → 조회 속도 개선

---

## 🗄️ Database Schema

### E-R Diagram
<img width="2274" height="1171" alt="Untitled (3)" src="https://github.com/user-attachments/assets/bcfb9a11-b1c4-48ca-bd78-ffcbc6ff2332" />


### Relational Schema Diagram

<img width="1841" height="946" alt="Untitled (4)" src="https://github.com/user-attachments/assets/f3f9b3fc-d954-4826-bc62-91a86f8cca7c" />



### 📌 테이블 구성

* **Books** — 도서 정보 (PK: `book_id`)
* **Members** — 회원 정보 (PK: `member_id`)
* **Borrowing** — 대출 이력 (FK: `book_id`, `member_id`)
* **Reservations** — 예약 정보 (FK: `book_id`, `member_id`)

### 📌 관계 (ERD 개념)

* Members ↔ Books 는 **Borrowing**, **Reservations**를 통해 N:1 관계
* 모든 회원이 대출 또는 예약을 하는 것은 아니므로 **Partial Participation**
* 성능을 위해 주요 외래키에 Index 적용

---

## 💻 Usage Scenario

### 1. 도서 현황 조회

* 장르별 보유 권수 조회
* 대출 중 / 예약 중 / 대출 가능 여부 확인

### 2. 대출 및 예약

예시:

1. 사용자 A(차은우) → *「채권총론」* 대출
2. 사용자 B(변우석) 동일 도서 대출 요청
   → 시스템: *대출 중 → 예약 가능* 안내
3. 예약 확정 → Reservations 테이블에 등록

### 3. 반납 및 예약자 우선 대출

* A가 반납하면 대출 기록 삭제
* 예약자가 존재할 경우, **해당 예약자만 대출 가능**

### 4. 회원 정보 수정

* 이메일, 전화번호 등 즉시 DB에 반영

---

## 🚀 How to Run

### 1. Database Setup

MySQL에서 아래 스크립트 실행:

```
createschema.sql
initdata.sql
```

`DatabaseConnection.java` 내 `url`, `user`, `password`를 환경에 맞게 수정합니다.

---

### 2. Build

```
javac -d bin src/*.java
```

---

### 3. Execute

```
java -jar Library.jar
```

또는

```
java Main
```

---

## 🔍 Code Snippet Example

```java
// BorrowManager.java 일부
// 도서 상태 조회 후 대출/예약 분기 처리

if (!borrow && !reservation) {
    // 대출 로직 수행
} else if (!borrow && reservation) {
    // 예약자 본인인지 확인 후 대출 수행 (우선순위 로직)
}
```

---

## 🚀 Future Improvements

* **Connection Pool (HikariCP) 도입**
  매 요청마다 생성되는 DB 연결 비용 감소

* **JPA / Hibernate 적용**
  객체 중심 구조로 리팩토링하여 유지보수성 향상

* **GUI / Web 확장**
  콘솔 기반에서 JavaFX / Spring Boot 웹 서비스로 확장
