# TravelLog - 여행 계획 관리 REST API

여행 일정을 관리하고, Google Places API로 주변 장소를 검색할 수 있는 백엔드 REST API 서버입니다.

## 기술 스택

| 분류 | 기술 |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.1.1 |
| ORM | Spring Data JPA + Hibernate |
| Database | MySQL 8.0 |
| Cache | Redis 7 |
| Auth | Spring Security + JWT |
| API Docs | Swagger (SpringDoc OpenAPI 3.1.1) |
| Infra | Docker, Docker Compose, Nginx |
| CI | GitHub Actions |
| Test | JUnit 5, Mockito |
| Performance | Apache JMeter |

## ERD

```
Member (1) ──── (N) Trip (N) ──── (1) City
                     |
                    (1)
                     |
                    (N)
                   Place
```

- **Member**: 회원 (email, password, nickname)
- **City**: 도시 (name, country)
- **Trip**: 여행 (title, startDate, endDate) - Member, City와 연관
- **Place**: 장소 (name, dayNumber, category, address, rating, lat, lng) - Trip과 연관

## API 명세

### Member API
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/members/signup | 회원가입 |
| POST | /api/members/login | 로그인 (JWT 토큰 발급) |

### City API
| Method | Endpoint | Description |
|---|---|---|
| GET | /api/cities | 도시 목록 조회 (페이지네이션) |
| GET | /api/cities/{id} | 도시 상세 조회 (Redis 캐시) |
| POST | /api/cities | 도시 등록 |
| DELETE | /api/cities/{id} | 도시 삭제 (캐시 무효화) |

### Trip API
| Method | Endpoint | Description |
|---|---|---|
| GET | /api/trips | 내 여행 목록 조회 (페이지네이션) |
| GET | /api/trips/{id} | 여행 상세 조회 |
| POST | /api/trips | 여행 등록 |
| PUT | /api/trips/{id} | 여행 수정 (본인만 가능) |
| DELETE | /api/trips/{id} | 여행 삭제 |

### Place API
| Method | Endpoint | Description |
|---|---|---|
| GET | /api/places/trip/{tripId} | 여행별 장소 목록 (페이지네이션) |
| GET | /api/places/trip/{tripId}/category/{category} | 카테고리별 장소 필터링 |
| POST | /api/places | 장소 등록 |
| DELETE | /api/places/{id} | 장소 삭제 |

### Google Places API
| Method | Endpoint | Description |
|---|---|---|
| GET | /api/google-places/search/places | Google Places 장소 검색 |

## 아키텍처

```
                    [Nginx - Load Balancer]
                     /                  \
            [App Server 1]        [App Server 2]
                |       \            |       \
            [MySQL]   [Redis]    [MySQL]   [Redis]
```

### 레이어 구조
```
Controller → Service → Repository → Database
    ↓           ↓
   DTO       Entity
```

## 주요 구현 사항

### 인증/인가
- JWT 기반 토큰 인증 (Spring Security)
- BCrypt 비밀번호 암호화
- API별 접근 권한 제어 (회원가입/로그인은 비인증 허용)
- 여행 수정/삭제 시 본인 확인 로직

### 성능 최적화
- **Redis 캐싱**: 도시 조회 시 캐시 적용 (TTL 30분), 삭제 시 캐시 무효화
- **N+1 문제 해결**: @EntityGraph를 활용한 연관 엔티티 즉시 로딩
- **페이지네이션**: Spring Data Pageable을 활용한 목록 조회

### API 품질
- 통일된 응답 포맷 (ApiResponse 래퍼)
- 입력값 검증 (@Valid + Bean Validation)
- 글로벌 예외 처리 (@RestControllerAdvice)
- Swagger UI 자동 문서화

### 인프라
- **Docker Compose**: MySQL + Redis + App 원커맨드 실행
- **Nginx 로드밸런서**: 라운드 로빈 방식으로 2대 서버 분산 처리
- **GitHub Actions CI**: PR 시 자동 빌드 검증

## 실행 방법

### Docker Compose (권장)
```bash
docker-compose up --build
```
- API: http://localhost
- Swagger UI: http://localhost/swagger-ui.html

### 로컬 실행
```bash
# 1. MySQL, Redis 실행 필요
# 2. application-local.properties 생성 (DB 접속 정보 설정)
# 3. 실행
./gradlew bootRun
```
- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html

## 프로젝트 구조

```
src/main/java/com/triple/travelLog/
├── config/          # Security, JWT, Redis, RestTemplate 설정
├── controller/      # REST API 엔드포인트
├── dto/             # 요청/응답 DTO
├── entity/          # JPA 엔티티
├── exception/       # 예외 처리
├── repository/      # JPA Repository
└── service/         # 비즈니스 로직
```
