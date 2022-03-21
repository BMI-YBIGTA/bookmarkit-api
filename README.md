# BookmarkIT-API
북마크 자동 분류 서비스 북마킷 백엔드 API 서버

## 환경
- OpenJDK 11
- Spring Boot 2.6.1
- MySQL 8.0.28
- RabbitMQ 3.9.13
- Docker

### MySQL
```shell
docker run --name mysql -e MYSQL_ROOT_PASSWORD={암호} -d -p 3306:3306 mysql
# m1 사용 시 --platform linux/x86_64 옵션 추가
```

### RabbitMQ
```shell
docker run --name rabbitmq -e RABBITMQ_DEFAULT_USER={유저이름} -e RABBITMQ_DEFAULT_PASS={암호} -d -p 5672:5672 -p 15672:15672 --restart=unless-stopped  rabbitmq:management
```

## 기능
### 회원
- 회원가입
- 로그인

### 북마크
- 북마크 등록 및 카테고리 자동 분류
- 북마크 검색
- 크롬 익스텐션 연동
- 유사 링크 추천
- 최근에 등록한 북마크 조회
- 카테고리별 북마크 조회
