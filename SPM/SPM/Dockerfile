FROM pushkarnk/openjdk-22-crac
WORKDIR /usr/src/app
COPY target/SPM-0.0.1-SNAPSHOT.jar .
COPY . .
EXPOSE 8080
CMD ["java", "-jar", "SPM-0.0.1-SNAPSHOT.jar"]