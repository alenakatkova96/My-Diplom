<h1> Дипломный проект по профессии «Тестировщик» </h1>
<h4> Aвтоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.</h4>

<h3> Подготовка к работе </h3>
<ol>
<li> Установить Браузер Google Chrome </li>
<li> Установить Git - систему контроля версий </li>
<li> Зарегистрироваться на сайте GitHUB </li>
<li> Установить IntelliJ IDEA </li>
<li> Установить программу Postman </li>
<li> Установить программу DBeaver </li>
<li> Установить программу Docker Desktop </li>
<li> Открыть программу IntelliJ IDEA </li>
<li> Склонировать публичный репозиторий  </li>

</ol>
<h3> Начало работы </h3>
<ol>
<li> Чтобы получить локальную копию проекта, необходимо склонировать репозиторий Pexini/Diplom : </li>
<li> c помощью команды git clone и вставить ссылку: </li>
     для HTTPS:  https://github.com/alenakatkova96/My-Diplom.git <br>
     для SSH: git@github.com:alenakatkova96/My-Diplom.git <br>
<li> Через терминал запустить Docker контейнер командой: docker-compose up --build </li>
</ol>

<h4> Для работы с БД MySQL </h4>
<ol>
<li> Через терминал открыть БД MySQL командой: <br> </li>
<u> java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar aqa-shop.jar </u>
<li> В браузере Сhrome открыть URL http://localhost/8080 </li>
<li> Запустить DBeaver, проверить соединения с MySQL</li>
<li> Запустить отчеты командой в терминале: <br>
./gradlew allureServe
<li> Остановить allureServe командой в терминале: CTRL + C</li>
</ol> 

<h4>Для работы с БД PostgresSQL</h4>
<ol>
<li> Через терминал открыть БД PostgresSQL из терминала командой: <br> </li>
<u>java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar aqa-shop.jar</u>
<li> В браузере Сhrome открыть URL http://localhost/8080 </li>
<li> Запустить DBeaver, проверить соединения с PostgresSQL</li>
<li> Запустить отчеты командой в терминале: <br>
./gradlew allureServe
<li> Остановить allureServe командой в терминале: CTRL + C</li>
</ol>

<h3> Завершение работы </h3>
<ol>
<li> Остановить работу приложение из терминала командой: Ctrl + C </li>
<li> Остановить контейнеры Docker из терминала : docker-compose down</li>
</ol>