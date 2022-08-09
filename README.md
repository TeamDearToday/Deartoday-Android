<h1 align="center">Dear today</h1>

<p align="center">
    <img src="https://img.shields.io/badge/Kotlin-1.6.10-7F52FF?style=for-the-badge&logo=Kotlin&logoColor=white"/>
    <img src="https://img.shields.io/badge/all_contributors-3-orange.svg?style=for-the-badge"/>
</p>

<p align="center">
	<h3 align="center">
		시간 여행을 통해 지금의 행복에 대해 생각할 수 있도록 도와주는 스토리텔링 게임 서비스<br>Dear today
	</h3>	
	<img src="https://user-images.githubusercontent.com/81508084/178636838-310b9640-d0d7-41c1-9d37-b5e5a7349e6f.png" />
	<img src="https://user-images.githubusercontent.com/63235947/178440217-bac8e37b-fa27-4ddd-b448-0d346ed6881e.png" />
	<img src="https://user-images.githubusercontent.com/63235947/178440518-79ddc1ec-d7eb-42e4-b04d-52fbc399278e.png" />

</p>

<h2>Tech Stack</h2>

- Minumum SDK 26
- Kotlin
    - Coroutines
- Dagger-Hilt
- AndroidX Jetpack
    - LiveData
    - Navigation Component
    - Security
- Retrofit2 & OkHttp3
- Glide
- Material Components
- Timber
- Lottie

<h2>Architecture</h2>

Dear today는 Android 공식문서에 서술된 [Android App Architecture](https://developer.android.com/topic/architecture#recommended-app-arch)를 기반으로 작성하였습니다.

<p align="center">
  <img src="https://developer.android.com/topic/libraries/architecture/images/mad-arch-overview.png" width="50%"/>
</p>


## Contributors ✨

Thanks goes to these wonderful people

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="http://github.com/hansh0101"><img src="https://avatars.githubusercontent.com/u/81508084?v=4" width="100px;" alt=""/><br /><sub><b>Seunghyeon Han<br>A.K.A. Iceman</b></sub></a><br /><a href="https://github.com/TeamDearToday/Deartoday-Android/commits?author=hansh0101" title="Code">💻</a></td>
    <td align="center"><a href="https://github.com/dn7638"><img src="https://avatars.githubusercontent.com/u/48896148?v=4" width="100px;" alt=""/><br /><sub><b>Woohyeong Choi</b></sub></a><br /><a href="https://github.com/TeamDearToday/Deartoday-Android/commits?author=dn7638" title="Code">💻</a></td>
    <td align="center"><a href="https://github.com/uxri"><img src="https://avatars.githubusercontent.com/u/102457223?v=4" width="100px;" at=""/><br /><sub><b>Yuree Choi</b></sub></a><br /><a href="https://github.com/TeamDearToday/Deartoday-Android/commits?author=uxri" title="Code">💻</a></td>
  </tr>
    <tr>
    <td align="center">메인<br>시간여행</td>
    <td align="center">로그인<br>나의 시간여행 확인</td>
    <td align="center">온보딩<br>나의 메시지 확인</td>
  </tr>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->


## Packages 📁

```
Dear today Android
 ┣ 📂application
 ┣ 📂data
 ┃ ┣ 📂service
 ┃ ┣ 📂model
 ┃ ┃ ┣ 📂request
 ┃ ┃ ┗ 📂response
 ┃ ┗ 📂repository
 ┣ 📂di
 ┣ 📂domain
 ┃ ┣ 📂enitity
 ┃ ┗ 📂repository
 ┣ 📂presentaion
 ┃ ┣ 📂adapter
 ┃ ┣ 📂ui
 ┃ ┗ 📂viewmodel
 ┗ 📂util
```
