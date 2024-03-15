### 스프링 심화 코드 개선 과제
-[x] Controller, Service 패키지 내 클래스 개선
-[x] CustomException 정의 및 SpringAOP 적용
-[x] QueryDSL 을 사용하여 검색 기능 만들기
-[x] Pageable 을 사용하여 페이징 및 정렬 기능 만들기
-[x] Service 테스트 코드 작성하기

### 기능명세서
 - [ ] 회원가입 기능
   - [ ] email, nickname, password, userinfo 를 입력받아 저장
   - [ ] password 암호화 저장
   - [ ] 중복된 email, nickname 일 경우 예외처리
 - [ ] 로그인 기능
   - [ ] email, password 를 입력받아 확인 후 로그인성공여부
   - [ ] Header에 JWT를 담아 반환
 - [ ] 프로필 조회 기능
   - [ ] Header 의 JWT를 통해 유저정보를 확인후, 유저의 정보를 반환
 - [ ] 프로필 수정 기능
   - [ ] password 확인 후 일치할 경우 수정가능
   - [ ] nickname, userinfo 를 입력받아 회원정보 수정
 - [ ] 로그아웃 기능
   - [ ] 전달받은 JWT를 blackList에 등록해 로그아웃 처리

 -[x] 관심상품 기능
   -[x] 유저와 상품을 저장
 - [x] 관심상품 조회
   - [x] productId, title, productInfo를 반환

- [x] 댓글 CRUD 기능
   - [x] 특정 상품의 댓글 조회 기능 
   - [x] 특정 유저가 작성한 댓글 조회 기능
   - [x] 특정 상품에 댓글 작성 기능
   - [x] 댓글 수정 기능
   - [x] 댓글 삭제 기능
