name = guybrush
debianTemplate = ./bin
buildRoot = ./build/distributions
buildDir = ${buildRoot}/${name}
distFile = target/guybrush.jar

debian: ${distFile} buildDir
	mkdir -p ${buildDir}/opt/${name}
	cp ${distFile} ${buildDir}/opt/${name}
	mkdir -p ${buildDir}/opt/${name}/database
	cp -R ./src/main/postgresql/* ${buildDir}/opt/${name}/database
	dpkg-deb --build ${buildDir}
	rm -R ${buildDir}

buildDir:
	rm -Rf ${buildDir}
	mkdir -p ${buildDir}
	cp -R ${debianTemplate}/* ${buildDir}

${distFile}:
	mvn clean package
	
clean:
	mvn clean

