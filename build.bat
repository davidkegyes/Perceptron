rmdir /Q/S build
mkdir build
javac -d build src/edu/sapi/mestint/*.java
cd build
jar cvfe ../Perceptron.jar edu.sapi.mestint.Perceptron edu/sapi/mestint/*.class
cd ..