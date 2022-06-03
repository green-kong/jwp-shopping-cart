# 장바구니
장바구니 미션 저장소

## ✏️ Code Review Process
[텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 페어 규칙

- 5분 간은 드라이버의 생각대로 작성한다.
    - 나머지 네비게이터는 자신의 생각을 정리한다.
- 투두 리스트를 작성한다. (리팩토링할 부분도)
- 물 마시다가 마려우면 쉰다. (캔트백처럼)
- 끝나는 시간은 되는데로~

---

## 기능 목록

### 회원
- [x] `nickname`(우측 상단에 회원명을 출력할 예정), 2자 이상 8자 이하(영문, 한글 숫자), 중복 0
- [x] `ID`(tonic@gmail.com) - 이메일 정규식에 맞아야 한다, 수정 x, 중복 x
- [x] `password`(12341234) - 8자 이상(영문, 숫자 2개 조합으로) 20자 이하

### 회원가입 /users

- [x] 회원정보 양식이 잘못됐을 때 400에러를 응답한다.
- [x] 정상 케이스일 때 204을 응답한다.
- [x] 이메일이 중복일 때 400에러를 응답한다.

### 로그인

- [x] 회원정보 양식이 잘못됐을 때 400에러를 응답한다.
- [x] 존재하지 않는 아이디 비밀번호로 요청이 왔을 때 400에러를 응답한다.
- [x] 정상 케이스일 때 토큰과 함께 200을 응답한다.


### 회원 정보 요청

- [x] 회원 토큰이 잘못됐을 때 401 에러를 응답한다.
- [x] 정상 케이스일 때 회원 정보와 함께 200을 응답한다.

### 회원 정보 수정

- [x] 회원정보 양식이 잘못됐을 때 400에러를 응답한다.
- [x] 회원 토큰이 잘못됐을 때 401 에러를 응답한다.
- [x] 정상 케이스일 때 204를 응답한다.

### 회원 삭제

- [x] 회원 토큰이 잘못됐을 때 401 에러를 응답한다.
- [x] 정상 케이스일 때 204를 응답한다.