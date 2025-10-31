cd ~/Documents/Projects/Raven/Crow || exit
# mvn package
# java -cp target/caw.jar Graveyard.App
mvn clean package
java -jar target/caw-jar-with-dependencies.jar

# cd ~/Documents/Projects/Raven/Raven || exit
# mvn package
# java -cp target/feather.jar Graveyard.App
