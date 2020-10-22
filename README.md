##
# pls make sure your mac already install mac os


run all
```
./gradlew cucumber
```
run with tag
```$xslt
 env=test browser=chrome 
 ./gradlew -Dtag='@smoking' runTestWithTag cucumber
```
run in parallel
```
 env=test browser=chrome 
 ./gradlew -Dtag='@smoking' runInParallel cucumber
```
https://developer.apple.com/documentation/webkit/about_webdriver_for_safari

