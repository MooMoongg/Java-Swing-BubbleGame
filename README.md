# Java Swing Game 프로젝트

##  프로젝트 개요
이 프로젝트는 유튜브 메타코딩 버블버블 게임만들기 참고하였으며
Java Swing을 활용하여 2D 게임을 개발한 프로젝트입니다.  
플레이어와 적(enemy) 캐릭터를 조작하여, 충돌 감지 및 물리적 상호작용을 구현했습니다.

---

##  개발 환경
- **Java Version:** Java 11
- **IDE:** Eclipse
- **사용한 플러그인:** WindowBuilder 1.9.5

---


---

##  강의 정리 문서
아래는 주요 학습 내용입니다.

###  Java Game 폴더 내 파일 목록:
1. **[AWT 개념 및 구현](JavaGame/01.AWT.md)**
2. **[JFrame 기본 설정](JavaGame/02.JFrame.md)**
3. **[캐릭터 좌우 이동 구현](JavaGame/03.캐릭터%20좌우%20이동.md)**
4. **[이벤트 리스너 개념 및 원리](JavaGame/04.이벤트%20리스너%20원리.md)**
5. **[스레드를 활용한 이동](JavaGame/05.캐릭터%20좌우%20이동%20스레드사용.md)**
6. **[점프 기능 구현](JavaGame/06.Jump%20메서드%20구현.md)**
7. **[배경 스레드를 이용한 충돌 감지](JavaGame/07.백그라운드%20스레드로%20충돌감지.md)**
8. **[배경 충돌 감지 개선](JavaGame/08.백그라운드%20스레드로%20충돌감지2.md)**
9. **[점프 후 착지 구현](JavaGame/09.점프%20후%20착지.md)**
10. **[발판 없는 공간에서의 하강 구현](JavaGame/10.발판없는%20공간에서의%20하강%20구현.md)**
11. **버블 공격 기능**
    - **[버블 생성](JavaGame/11.Bubble%20생성.md)**
    - **[버블 발사](JavaGame/12.Bubble%20발사하기.md), [Bubble 발사하기2](JavaGame/13.Bubble%20발사하기2.md)**
12. **[벽 충돌 감지](JavaGame/14.벽%20감지하기.md)**
13. **적(enemy) 관련 기능**
    - **[적(enemy) 3개 만들기](JavaGame/15.적(enemy)%203개%20만들기.md)**
    - **[적(enemy) 점프 및 이동](JavaGame/16.적(enemy)의%20점프%20및%20이동기능.md)**
    - **[적(enemy)에 닿으면 게임 종료](JavaGame/17.적(enemy)에%20닿으면%20종료.md)**



---

##  주요 기능
- 플레이어 이동 (좌우, 점프)
- 적(enemy) 이동 및 점프
- 충돌 감지 (벽, 지형, 적과의 충돌)
- 물방울(Bubble) 공격 기능
- 배경 스레드 활용 (충돌 감지 및 물리적 효과 적용)

---

##  실행 방법
1. Eclipse에서 `bubble` 프로젝트를 실행합니다.
2. `BubbleFrame.java`를 실행하여 게임을 시작합니다.
3. 방향키로 플레이어를 이동하고, **스페이스바**로 공격합니다.

---

##  게임 플레이 영상
게임 플레이 시연 영상입니다. 아래 링크를 클릭하여 확인할 수 있습니다.

 ▶ **[YouTube에서 보기](https://www.youtube.com/watch?v=RaUt8w3cQNY)**  

---

##  추가 설명
강의 내용을 바탕으로 프로젝트를 개선하며 진행했습니다.  
게임 개발 과정 및 학습 내용을 `.md` 파일로 정리하여 `Java Game` 폴더에 보관하고 있습니다.

---



