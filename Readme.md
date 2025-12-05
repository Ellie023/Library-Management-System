

# 📚 Library Management System (도서 관리 시스템)

> **Java와 MySQL을 연동하여 도서의 대출, 반납, 예약 프로세스를 완벽하게 구현한 콘솔 기반 관리 시스템**

## 📖 프로젝트 개요

이 프로젝트는 도서관의 핵심 업무 프로세스를 전산화한 시스템이다. 도서의 입고부터 회원의 대출, 예약, 반납에 이르는 생명주기(Lifecycle)를 관리하며, **JDBC API**를 활용하여 데이터베이스와의 안정적인 연결 및 데이터 무결성을 보장하도록 설계했다.

## 🛠 Tech Stack

| Category | Technology |
| :--- | :--- |
| **Language** | Java SE (JDK 8+) |
| **Database** | MySQL 8.0 |
| **Connectivity** | JDBC (Java Database Connectivity) |
| **Date/Time** | `java.time` (LocalDate, DateTimeFormatter) |
| **Environment** | IntelliJ IDEA / Eclipse |

## ⚙️ Key Features & Implementation Details

단순히 CRUD를 구현한 것을 넘어, 실제 도서관 운영 시 발생할 수 있는 예외 상황과 비즈니스 로직을 처리하는 데 중점을 두었다.

### 1\. 정교한 대출 및 예약 로직 (`BorrowManager`)

  * **상태 기반 프로세스 제어**: 도서의 상태(대출 가능, 대출 중, 예약 중)를 판별하여 동적으로 프로세스를 분기한다.
  * **예약 우선순위 처리**: 대출 중인 도서에 대해 예약 기능을 제공하며, 반납 시 예약자가 존재할 경우 예약자만 대출이 가능하도록 \*\*선점 로직(Preemption Logic)\*\*을 구현하여 데이터의 논리적 오류를 방지했다.
  * **날짜 연산 자동화**: `java.time` 패키지를 활용해 대출 시점으로부터 14일 후 반납일을 자동 계산하여 DB에 저장한다.

### 2\. 데이터 무결성 (`InputValidator`)

  * **유효성 검사 모듈화**: 사용자 입력값에 대한 검증 로직을 별도의 클래스(`InputValidator`)로 분리하여 유지보수성을 높였다.
  * **참조 무결성 보장**: 비즈니스 로직 수행 전 `MemberID`, `BookID` 등의 존재 여부를 DB에서 먼저 조회하여, 외래키(FK) 제약 조건 위반으로 인한 SQL 에러를 사전에 차단했다.

### 3\. 보안 및 효율성을 고려한 DB 접근 (`DatabaseConnection`, `select1`)

  * **PreparedStatement 활용**: 모든 SQL 쿼리에 `PreparedStatement`를 사용하여 **SQL Injection 공격을 방지지**하고, 쿼리 캐싱을 통해 성능을 최적화했다.
  * **View 활용**: 복잡한 조인(Join) 쿼리 대신 DB단에서 `MemberBorrowedBooks` View를 생성하고 이를 조회함으로써, 애플리케이션 레벨의 쿼리 복잡도를 낮추고 데이터 조회 성능을 개선했다.

## 🗄️ Database ERD & Schema

본 시스템은 정규화된 테이블 설계를 바탕으로 운영된다.

  * **Books**: 도서 정보 (PK: `book_id`)
  * **Members**: 회원 정보 (PK: `member_id`)
  * **Borrowing**: 대출 이력 및 현황 (FK: `book_id`, `member_id`)
  * **Reservations**: 예약 대기열 관리 (FK: `book_id`, `member_id`)

## 💻 How to Run

1.  **Database Setup**:
    MySQL에 접속하여 `library` 스키마를 생성하고 테이블 스크립트를 실행한다.
    (*`DatabaseConnection.java`의 url, user, password를 본인 환경에 맞게 수정해야 한다.*)

2.  **Build**:
    프로젝트 루트 디렉토리에서 컴파일한다.

    ```bash
    javac -d bin src/*.java
    ```

3.  **Execution**:
    빌드된 jar 파일을 실행하거나 Main 클래스를 실행한다.

    ```bash
    java -jar Library.jar
    # 또는
    java Main
    ```

## 🔍 Code Snippet Example

**[대출 가능 여부 판단 로직]**

```java
// BorrowManager.java 중 일부
// 대출 중이거나 예약 중인지 DB에서 조회 후 상태 플래그 설정
if (!borrow && !reservation) {
    // 대출 로직 수행
} else if (!borrow && reservation) {
    // 예약자가 본인인지 확인 후 대출 수행 (우선순위 로직)
}
```

## 🚀 Future Improvements

  * **Connection Pool 도입**: 현재 요청마다 생성되는 DB 연결 비용을 절감하기 위해 HikariCP 등을 도입하여 성능 개선 예정.
  * **JPA/Hibernate 전환**: SQL 의존성을 줄이고 객체 중심의 설계를 강화하기 위해 ORM 기술 도입 고려.
  * **GUI/Web 확장**: 콘솔 인터페이스를 JavaFX 또는 Spring Boot 기반의 웹 인터페이스로 확장.

