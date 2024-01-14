
## Step 1. Install Java 11 (OpenJDK)
ThingsBoard service is running on Java 11. Follow this instructions to install OpenJDK 11:
```text
sudo apt update
```
```text
sudo apt install openjdk-11-jdk
```
Please don’t forget to configure your operating system to use OpenJDK 11 by default. You can configure which version is the default using the following command:

```text
sudo update-alternatives --config java
```
You can check the installation using the following command:
```text
java -version
```
Expected command output is:
```
openjdk version "11.0.xx"
OpenJDK Runtime Environment (...)
OpenJDK 64-Bit Server VM (build ...)
```

## Step 2. Build Source with Maven
ThingsBoard build requires Maven 3.1.0+.
```text
sudo apt-get install maven
```
You can clone source code of the project from the official github repo.
```
git clone https://github.com/d42y/thingsboard
```
```text
cd thingsboard
```
### Build
Run the following command from the thingsboard folder to build the project:
```text
mvn clean install -DskipTests
```

