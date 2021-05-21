# Kotlin + Security Google login + JWT

![](https://images.velog.io/images/42seouler/post/31a49a3f-4847-448d-9238-5446acdf834a/ezgif.com-gif-maker.gif)

Spring Security의 OAuth 로그인을 사용하면 Google로그인을 통해 타사 로그인을 수행 할 수 있습니다. OAuth의 로그인 흐름은 백엔드가 OAuth 서버에 인증코드를 요청을 보내는 것입니다. 그리고 OAuth서버가 프론트엔드 로그인화면으로 리디렉션합니다. 프론트엔드가 로그인 한 후 OAuth서버는 인증코드를 백엔드로 보냅니다. 마지막으로 백엔드는 OAuth 서버에 인증코드, 클라이언트 ID 및 클라이언트를 확인하는 요청을 보냅니다 OAuth서버가 성공적으로 인증되면 ID토큰과 엑세스 토큰을 반환합니다. 휴대폰 앱에서는 리디렉션은 적합하지 않다고 생각했습니다.
그래서 인증코드를 프론트엔드로 전송하는게 아니라 백엔드에서 전송 받아서 OAuth서버로 보내고
유저에게는 자체 발행한 JWT를 주도록 했습니다.

## 로그인을 위한 JSP 활용

![](https://images.velog.io/images/42seouler/post/9b7f1eff-bed4-4576-809f-b4df96192f83/image.png)

## 로그인 직후 JWT 토큰을 유저에게 발행

![](https://images.velog.io/images/42seouler/post/8f6e8321-409f-4e96-b0e4-0bba4181c867/image.png)

토큰 정보가 굉장히 긴 것을 알 수 있다.
이 토큰의 내용을 볼 수 있도록 별도로 버튼을 준비하였다.

## JWT 토큰에 포함 된 access, refresh 토큰

![](https://images.velog.io/images/42seouler/post/d8cbff61-60b5-4872-82a9-ca43f3d08326/image.png)

[OAuth access 토큰 만료 예시](https://cloud.google.com/apigee/docs/api-platform/antipatterns/oauth-long-expiration?hl=ko)
다음 OAuthV2 정책 예시는 200일이라는 긴 만료 시간이 지정된다고 한다.
그렇다면 내가 발행한 토큰도 이보다 짧은 만료시간을 두어 설정하게 된다면 access token
만료 이전에 나의 JWT토큰을 만료 시킬 수 있지 않을까란 생각으로 JWT로 랩핑하게 되었습니다.