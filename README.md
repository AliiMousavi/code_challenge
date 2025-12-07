## 🚀 Generate 5 Million Persian User in Under 30 Seconds.
This repo benchmarks different strategies for high-volume data insertion using Java and PostgreSQL
and implement the fastest and most efficient way to insert 5,000,000 Persian user records into PostgreSQL and automatically clean them up.
مشخصات پروژه؛  
Spring Boot 3.5.8 + PostgreSQL COPY + RabbitMQ Event-Driven

[![Java 17](https://img.shields.io/badge/Java-17-blue)](https://adoptium.net/)
[![Spring Boot 3.5.8](https://img.shields.io/badge/Spring_Boot-3.5.8-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)](https://www.postgresql.org/)
[![RabbitMQ](https://img.shields.io/badge/RabbitMQ-3.12-orange)](https://www.rabbitmq.com/)
[![Performance](https://img.shields.io/badge/5M_records-<10s-success)](.)

## آخرین پیاده سازی (HDD + 16GB RAM)

| مرحله                   | زمان اجرا      | روش نهایی                                   |
|-------------------------|---------------|---------------------------------------------|
| تولید فایل CSV          | ۲٫۸ ثانیه | Java + ThreadLocalRandom + 8MB Buffer       |
| بارگذاری در دیتابیس     | 20 ثانیه | PostgreSQL COPY با CopyManager          |
| پاک‌سازی خودکار          | < 500ms   | TRUNCATE از طریق RabbitMQ Listener        |
| کل فرآیند            | < 30 ثانیه |                                           |

## استراتژی‌های تست‌شده و چرایی انتخاب پیاده سازی بالا به عنوان بهینه‌ترین روش.

### ۱. تولید ۵ میلیون رکورد CSV

| روش                                    | زمان اجرا       | مصرف RAM   | نتیجه / دلیل شکست                              |
|----------------------------------------|----------------|-----------|-----------------------------------------------|
| Python + Faker + pandas                | ۳۵–۶۵ ثانیه    | ~۱٫۵ GB   | Faker در حجم بالا فوق‌العاده کند می‌شود         |
| Python + pandas + random               | ۱۲–۱۸ ثانیه    | ~۱ GB     | نوشتن روی HDD کند بود                         |
| Java + parallel stream + synchronized  | ۲۶–۴۵ ثانیه    | ۱۰–۱۵ GB  | Thread contention            |
| Java + for loop + String.format        | ۱۱ ثانیه       | ~۲ GB     | String.format و now() در حلقه کند هستند         |

### ۲. درج ۵ میلیون رکورد در PostgreSQL

| روش                                           | زمان اجرا         | مصرف RAM   | نتیجه                                   |
|-----------------------------------------------|------------------|-----------|----------------------------------------|
| JPA saveAll()                               | ۸–۱۵ دقیقه        | ۲۰+ GB    |  غیرقابل استفاده                   |
| Hibernate batch insert                        | ۲–۵ دقیقه         | ۸–۱۲ GB   | کند و پرمصرف                           |
| JdbcTemplate batch insert                     | ۴۵–۹۰ ثانیه      | ~۳ GB     | قابل قبول اما کند                        |
| COPY FROM 'file' با JdbcTemplate            | ۴۵–۶۰ ثانیه      | ~۱ GB     | روی ویندوز کند بود                       |

### ۳. پاک‌سازی ۵ میلیون رکورد

| روش                | زمان اجرا         | نتیجه                              |
|-------------------|------------------|-----------------------------------|
| DELETE FROM     | ۳۰–۹۰ ثانیه      | کند و  سنگین                    |
| DROP TABLE      | ۳۰۰–۵۰۰ میلی‌ثانیه | ساختار از بین می‌رود                |

## نحوه اجرا
clone -> Configure application.properties -> Build the Project -> Run the Application...

git clone https://github.com/your-username/massive-load-interview.git

## تست عملکرد
curl -X POST http://localhost:8080/microServiceA/generate
