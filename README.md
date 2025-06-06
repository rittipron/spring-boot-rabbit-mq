# 🐇 Spring Boot + RabbitMQ Example

โปรเจกต์นี้แสดงการใช้งาน RabbitMQ กับ Spring Boot โดยมีฟีเจอร์:

✅ ส่งและรับข้อความแบบ JSON  
✅ Dead-letter Queue (DLQ) กรณี consumer ล้มเหลว  
✅ Async Logging ด้วย Logstash  
✅ Tracing ด้วย Spring Sleuth + Zipkin  
✅ REST API สำหรับรับข้อความจาก client  
✅ Docker Compose สำหรับ RabbitMQ + Zipkin  
✅ พร้อมต่อยอดด้วย Unit Test / Integration Test

---

## 🚀 เริ่มต้นใช้งาน

### 1. Clone และ build
```bash
mvn clean install
```

### 2. รัน RabbitMQ + Zipkin
```bash
docker-compose up -d
```

### 3. รัน Spring Boot App
```bash
./mvnw spring-boot:run
```

---

## 📦 REST API

### `POST /api/v1/message`

ส่งข้อความไปยัง RabbitMQ

**Request Body**
```json
{
  "id": "1",
  "content": "hello",
  "timestamp": "2025-06-02T10:00:00"
}
```

**Response**
```
HTTP 200 OK
Message sent
```

---

## ⚙️ โครงสร้าง RabbitMQ

- `demo_exchange`: exchange หลัก
- `demo_queue`: queue หลัก
- `dead_letter_exchange`: ใช้สำหรับข้อความที่ล้มเหลว
- `dead_letter_queue`: รับข้อความจาก DLX

---

## 📚 โฟลเดอร์สำคัญ

| Path | รายละเอียด |
|------|-------------|
| `controller/` | REST API Endpoint |
| `service/` | ส่งข้อความเข้า RabbitMQ |
| `consumer/` | รับและประมวลผลข้อความจาก queue |
| `config/` | ตั้งค่า queue, exchange, DLQ |
| `model/` | คลาส Payload ที่ใช้รับส่ง |
| `resources/` | ไฟล์ config (`application.yml`) |

---

## 🔍 Tracing + Logging

- Spring Sleuth + Zipkin: รวบรวม trace และ span
- Logstash JSON Logger: รองรับ logging format ที่ง่ายต่อการส่งออก

เปิด Zipkin UI ได้ที่: [http://localhost:9411](http://localhost:9411)

---

## 🐳 Docker Services

| Service | Port |
|---------|------|
| RabbitMQ | `5672`, `15672` (management UI) |
| Zipkin | `9411` |

---

## 🧪 เพิ่ม Test (แนะนำ)

### Unit Test
- RabbitMQProducer: ทดสอบการส่ง message

### Integration Test
- ใช้ Embedded RabbitMQ หรือ Testcontainers

---

## 📌 Dependency หลัก (ใน pom.xml) 
- `spring-boot-starter-amqp`
- `spring-boot-starter-web`
- `spring-cloud-starter-sleuth`
- `spring-cloud-starter-zipkin`
- `logstash-logback-encoder`
