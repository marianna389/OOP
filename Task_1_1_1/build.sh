javac -d . src/main/java/ru/nsu/mkorovkina/task111/*.java
javadoc -d docs src/main/java/ru/nsu/mkorovkina/task111/*.java
jar cfe heapsort.jar ru.nsu.mkorovkina.task111.Main ru/nsu/mkorovkina/task111/*.class
java -jar heapsort.jar