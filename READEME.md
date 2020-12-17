## 概要
- [じゃんけんアドベントカレンダー2020](https://qiita.com/advent-calendar/2020/janken) の追走を行っています

## 実行手順

```bash

# build
./gradlew build
DATA_DIR="$(pwd)/data" java -jar app/build/libs/app.jar

# test
./gradlew test

# OWASP 脆弱性診断
./gradlew dependencyCheckAnalyze

```



![badge.svg](https://github.com/os1ma/JankenEnterpriseEdition/workflows/workflow/badge.svg)