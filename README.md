### LABS 1 SI

#### In order to send a TCP message one would use the following command:
```
mvn exec:java -Dexec.mainClass=App -Dexec.args="-t localhost -s 8083 -m This is the message."
```

#### In order to scan the ports one would use the following command:
```
mvn exec:java -Dexec.mainClass=App -Dexec.args="-t agora.md -s verbose 1-100"
```


#### In order to do a verbose scan the ports one would use the following command:
```
mvn exec:java -Dexec.mainClass=App -Dexec.args="-t agora.md -s verbose 1-100"
```

#### In order to do a get request one would use the following command:
```
mvn exec:java -Dexec.mainClass=App -Dexec.args="-t agora.md -get"
```

